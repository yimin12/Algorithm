/**
 * 
 */
package String;

import java.util.ArrayList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月15日 上午12:39:46
* Description:
* 	Given an original string input, and two strings S and T, 
* 	replace all occurrences of S in input with T.
* Assumptions:
	input, S and T are not null, S is not empty string 
  Examples:
	input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
	input = "dodododo", S = "dod", T = "a", input becomes "aoao"
*/
public class StringReplace {
//	Method 1: Not using any String/StringBuilder utility and using char[] to do it "in-place"
	public String replaceI(String input, String s,String t) {
//		Assumption: input, s, t, are not null, s is not empty
		char[] array = input.toCharArray();
		if(s.length() >= t.length()) {
			return replaceShort(array, s, t);
		}
		return replaceLonger(array, s, t);
	}
	
	public String replaceShort(char[] input, String s, String t) {
//		We reuse the input char array since the number of characters needed is less.
//		fast and slow pointers both from right direction.
		int slow = 0;
		int fast = 0;
		while(fast < input.length) {
//			when we find a match of s on the substring starting from the fast pointer
			if(fast <= input.length - s.length() && equalsSubstring(input, fast, s)) {
				copySubstring(input, slow, t);
				slow += t.length();
				fast += s.length();
			} else {
				input[slow++] = input[fast++];
			}
		}
		return new String(input, 0, slow);
	}
	
	public String replaceLonger(char[] input, String s, String t) {
//		Notice: we will need a longer array in the case, and if the requirement is "in-place", usually
//		you can assume you are given a long enough char array already, and the original input string resides
//		on part of the char array starting from index 0.
//		In this solution, we actually allocate a larger array on demand, and the purpose of the solution is
//		is to demonstrate how to do it "in-place"/
//		Get all the matches end positions in the input char array of string s
		ArrayList<Integer> matches = getAllMatches(input, s);
//		calculate the new length need
		char[] result = new char[input.length + matches.size() * (t.length() - s.length())];
//		fast and slow pointers both from right to left direction. 
//		low: the position when traversing the new length
//		fast: the position when traversing the old length
//		lastIndex: the rightmost matching end position's index.
		int lastIndex = matches.size()-1;
		int fast = input.length;
		int slow = result.length;
		while(fast >= 0) {
//			only if we still have some match and slow is in the position of rightmost matching end position
//			we should copy t.
			if(lastIndex >= 0 && fast == matches.get(lastIndex)) {
				copySubstring(result, slow-t.length()+1, t);
				slow-=t.length();
				fast-=s.length();
				lastIndex--;
			} else {
				result[slow--] = input[fast--];
			}
		}
		return new String(result);
	}
	
	private boolean equalsSubstring(char[] input, int fromIndex, String s) {
		for(int i = 0; i < s.length(); i++) {
			if(input[fromIndex + 1] != s.charAt(i)) {
				return false;
			}
		}
		return true;
	}
//	copy the string to to result at fromIndex
	private void copySubstring(char[] result, int fromIndex, String t) {
		for(int i = 0; i < t.length(); i++) {
			result[fromIndex+1] = t.charAt(i);
		}
	}
//	get all the matches of s end positions in input
	private ArrayList<Integer> getAllMatches(char[] input, String s){
		ArrayList<Integer> matches = new ArrayList<Integer>();
		int i = 0;
		while(i <= input.length-s.length()) {
			if(equalsSubstring(input, i, s)) {
//				we record the match substring's end index instead of start index for later convenience
				matches.add(i+s.length() - 1);
				i += s.length();
			} else {
				i++;
			}
		}
		return matches;
	}
//	Method 2: Using Java's StringBuilder utility and String's indexof(), rather than String's replacement
	public String replaceII(String input, String s, String t) {
//		Assume: input, s, t are not null, and s is not empty
		StringBuilder sb = new StringBuilder();
//		we check if there exists a substring same as s in the substring of input starting at fromIndex
		int fromIndex = 0;
		int matchIndex = input.indexOf(s, fromIndex);
		while(matchIndex != -1) {
			sb.append(input, fromIndex, matchIndex).append(t);
//			Next time we need start from matchIndex + s.length() to find if we have later matches
			fromIndex = matchIndex + s.length();
			matchIndex = input.indexOf(s, fromIndex);
		}
		sb.append(input, fromIndex, input.length());
		return sb.toString();
	}
}
