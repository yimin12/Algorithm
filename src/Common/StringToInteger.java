/**
 * 
 */
package Common;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月28日 下午12:09:59
* Description:
* 	Implement atoi which converts a string to an integer. The function first discards as many whitespace
* 	characters as necessary until the first non-whitespace character is found. Then, starting from this character
* 	, takes an optional initial plus or minus sign followed by as many as numerical digits as possible, and 
* 	interprets them as a numerical value.
* 	The string can contain additional characters after those that form the integral number, which are ignored
* 	and have no effect on the behavior of this function. If the first sequence of non-whitespace characters
* 	in str is not a valid integral number, or if no such sequence exists because either str is empty or it
* 	contain only whitespace characters, no version is performed. If no valid conversion could be performed, 
* 	a zero value is returned.
* Assumption:
* 	Only the space ' ' is considered was whitespace character
* 	Assume we are dealing with an environment which could only store integers within 32-bit signed integer 
* 	range:[-2^31, 2^31-1]. If the numerical value is out of the range of representable values,  
* 	INT_MAX (2^31 − 1) or INT_MIN (−2^31) is returned.
* Example :
	Input: "42"
	Output: 42
	
	Input: "   -42"
	Output: -42
	Explanation: The first non-whitespace character is '-', which is the minus sign.
	             Then take as many numerical digits as possible, which gets 42.
	
	Input: "4193 with words"
	Output: 4193
	Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
	
	Input: "words and 987"
	Output: 0
	Explanation: The first non-whitespace character is 'w', which is not a numerical 
	             digit or a +/- sign. Therefore no valid conversion could be performed.
	
	Input: "-91283472332"
	Output: -2147483648
	Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
	             Thefore INT_MIN (−231) is returned.
*/

public class StringToInteger {
	public int myAtoi(String str) {
		int index = 0;
		int total = 0;
		int sign = 1;
		
//		check if emtpy string
		if(str.length() == 0) {
			return 0;
		}
//		remove white space from string
		while(index < str.length() && str.charAt(index) == ' ') {
			index++;
		}
		if(index==str.length()) return 0;
//		get the sign
		if(str.charAt(index) == '+' || str.charAt(index)== '-') {
			sign = str.charAt(index) == '+' ? 1:-1;
			index++;
		}
//		convert to the actual number and make sure it's not overflow
		while(index<str.length()) {
			int digit = str.charAt(index) - '0';
			if(digit < 0|| digit > 9) break;
//			check if overflow
			if(Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit) {
				return sign == 1 ? Integer.MAX_VALUE: Integer.MIN_VALUE;
			}
			total = total * 10 + digit;
			index++;
		}
		return total * sign;
	}
}
