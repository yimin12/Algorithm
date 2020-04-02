/**
 * 
 */
package williamsNotebook.easy.string.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��17�� ����10:36:19
* Description:
* 	Word ��book�� can be abbreviated to 4, b3, b2k, etc. Given a string and an abbreviation, 
* 	return if the string matches the abbreviation.
* Assumptions:
	The original string only contains alphabetic characters.
	Both input and pattern are not null.
	Pattern would not contain invalid information like "a0a","0".
  Examples:
	pattern ��s11d�� matches input ��sophisticated�� since ��11�� matches eleven chars ��ophisticate��.
*/

public class AbbreviationMatch {
//	Method1: Recursive way
	public boolean match(String input,String pattern) {
//		Assumption: input, pattern are not null
		return match(input, pattern, 0, 0);
	}
	private boolean match(String s, String t, int si, int ti) {
//		only when we run out of s and t at the same time, there is a match, it is also the base case
		if(si == s.length() && ti == t.length()) {
			return true;
		}
//		if we ran out of one of s and t, but there is still some characters left for the other one, no match
		if(si >= s.length() || ti >= t.length()) {
			return false;
		}
//		case 1. if the current character in t is not a digit
		if(t.charAt(ti) < '0' || t.charAt(ti) > '9') {
			if(s.charAt(si) == t.charAt(ti)) {
				return match(s, t, si + 1, ti + 1);
			}
			return false;
		}
//		case 2. if the current character in t is a digit. we need to find in total what is the number. 
//		e.g. "123" means number 123. you need to confirm the value of ti
		int count = 0;
		while(ti < t.length() && t.charAt(ti) >= '0' && t.charAt(ti) < '9') {
			count = count * 10 + (t.charAt(ti) - '0');
			ti++;
		}
		return match(s, t, si + count, ti);
	}
//	Method 2: Iterative way
//	Notice that recursive solution is a TAIL recursion, it is very easy to convert it to an iterative one.
	public boolean matchII(String input, String pattern) {
		int si = 0, ti = 0;
		while(si < input.length() && ti < pattern.length()) {
			if(pattern.charAt(ti) < '0' || pattern.charAt(ti) > '9') {
				if(input.charAt(si) != pattern.charAt(ti)) {
					return false;
				}
				si++;
				ti++;
			} else {
				int count = 0;
				while(ti < pattern.length() && pattern.charAt(ti) >= 0 && pattern.charAt(ti) <= '9') {
					count = count * 10 + (pattern.charAt(ti) - '0');
					ti++;
				}
				si += count;
			}
		}
		return si == input.length() && ti == pattern.length();
	}
}
