package williamsNotebook.easy.conversion;

/**
 * @author yimin Huang
 * 
 *	The function first discards as many whitespace characters as necessary until the first non-whitespace character 
 *	is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many 
 *	numerical digits as possible, and interprets them as a numerical value.
 *	The string can contain additional characters after those that form the integral number, which are ignored 
 *	and have no effect on the behavior of this function.
 *	If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence 
 *	exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 *	If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of 
 *	representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 *	
 * Algorithm Class
 */
public class AToI {
	
	// You can test whether it is a Valid Number
	/*
	 * 	Validate if a given string is numeric. Some examples: 
	 * 		"0" => true " 0.1 " => true "abc" => false "1 a" => false "2e10" => true
	 * 	Format: space +/- XXX . XXX e +/- XXX space
	 * 	1. space and sign
	 * 	2. digits before '.'
	 * 	3. digits after '.' and before 'e'
	 * 	4. digits after 'e', sign and digits
	 * 	5. space
	 * 	
	 * 	sign '+/-' is allowed both before and after 'e';
	 * 	digit '.' is not allowed after 'e';
	 * 	before digit '.', it may not have any digit;
	 * 	Need an boolean indicator isNumeric to exclude the string containing no numbers.
	 */
	public boolean isNumberI(String s) {
		// the valid order of format is space +/- XXX . XXX e +/- XXX space
		int i = 0, N = s.length();
		while(i < N && Character.isWhitespace(s.charAt(i))) i++;
		if(i < N && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
		boolean isNumeric =false;
		while(i < N && Character.isDigit(s.charAt(i))) {
			i++;
			isNumeric = true;
		}
		// digit from '.' to 'e'
		if(i < N && s.charAt(i) == '.') {
			i++;
			while(i < N && Character.isDigit(s.charAt(i))) {
				i++;
				isNumeric = true;
			}
		}
		// digit after e
		if(isNumeric && i < N && s.charAt(i) == 'e') {
			i++;
			if(i < N && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
			isNumeric = false;
			while(i < N && Character.isDigit(s.charAt(i))) {
				i++;
				isNumeric = true;
			}
		}
		// white space
		while(i < N && Character.isWhitespace(s.charAt(i))) i++;
		return isNumeric && i == N;
	}
	
	// more robust to determine whether given string is numeric number
	public boolean isNumber(String s) {
		String str = s.trim();
		boolean seenNumber = false, seenSignBeforeE = false, seenE = false, seenPoint = false, 
				seenNumberAfterE = false, seenSignAfterE = false;
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(c == '+' || c == '-') {
				if(((seenPoint || seenNumber) && !seenE) || seenNumberAfterE) {
					return false;
				}
				if(seenSignAfterE || (!seenE && seenSignBeforeE)) {
					return false;
				}
				if(seenE) {
					seenSignAfterE = true;
				} else {
					seenSignBeforeE = true;
				}
			} else if(c >= '0' && c <= '9'){
				seenNumber = true;
				if(seenE) seenNumberAfterE = true;
			} else if(c == 'e' || c == 'E') {
				if(seenE) return false;
				if(!seenNumber) return false;
				seenE = true;
			} else if(c == '.') {
				if(seenE || seenPoint) return false;
				seenPoint = true;
			} else {
				return false;
			}
		}
		if(seenE && !seenNumberAfterE) {
			return false;
		}
		return seenNumber;
	}
	
	// in case of not getting overflow by overflow detector val > INT_MAX / 10
	// Time: O(n), Space: O(1)
	private static final int MAXDiv10 = Integer.MAX_VALUE / 10;
	
	public int atoi(String s) {
		if(!isNumber(s)) {
			return 0;
		}
		int i = 0, N = s.length(), sign = +1;
		// step 1: skip the whitespace
		while(i < N && Character.isWhitespace(s.charAt(i))) i++;
		// step 2: Normally, encounter 'sign'
		if(i < N && s.charAt(i) == '+') {
			sign = +1;
			i++;
		} else if(i < N && s.charAt(i) == '-') {
			sign = -1;
			i++;
		}
		// step 3: convert the string to numerical value
		int carry = 0;
		while(i < N && Character.isDigit(s.charAt(i))) {
			int digit = s.charAt(i) - '0';
			// handle the overflow problem
			if(carry > MAXDiv10 || carry == MAXDiv10 && digit > 7) {
				return sign == +1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			carry = carry * 10 + digit;
			i++;
		}
		return carry * sign;
	}
	
	public static void main(String[] args) {
		AToI solution = new AToI();
		int atoi = solution.atoi("       -301");
		System.out.println(atoi);
	}
}
