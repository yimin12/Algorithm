/**
 * 
 */
package williamsNotebook.easy.string;

import java.util.ArrayList;

/**
 * @author yimin Huang
 *
 *		Given an original input, and two strings S and T, replace all occurrences of S in input with T
 *
 * Algorithm Class
 */
public class StringReplace {

	// Method 1: Not using any StringBuilder/StringBuffer utility, and using char[] to do it "inplace"
	// Assumption: input, s, t, are not null, s is not empty
	public String replace(String input, String s, String t) {
		char[] array = input.toCharArray();
		if(s.length() > t.length()) {
			return replaceShorter(array, s, t);
		}
		return replaceLonger(array, s, t);
	}
	private String replaceShorter(char[] array, String s, String t) {
		// we reuse the input char since the number of characters needed is less
		int slow = 0, fast = 0;
		while(fast < array.length) {
			if(fast <= array.length - s.length() && equalSubString(array, fast, s)) {
				copySubString(array, slow, t);
				slow += t.length();
				fast += s.length();
			} else {
				array[slow++] = array[fast++];
			}
		}
		return new String(array, 0, slow);
	}	
	private String replaceLonger(char[] array, String s, String t) {
		ArrayList<Integer> matches = getAllMatches(array, s);
		char[] result = new char[array.length + matches.size() * (t.length() - s.length())];
		int lastIndex = matches.size() - 1;
		// we copy the char from the end to the start
		int fast = array.length - 1;
		int slow = result.length - 1;
		while(fast >= 0) {
			if(lastIndex >= 0 && fast == matches.get(lastIndex)) {
				copySubString(result, slow - t.length() + 1, t);
				slow -= t.length();
				fast -= s.length();
				lastIndex--;
			} else {
				result[slow--] = result[fast--];
			}
		}
		return new String(result);
		
	}
	private boolean equalSubString(char[] array, int offset, String s) {
		for(int i = 0; i < s.length(); i++) {
			if(array[offset + i] != s.charAt(i)) return false;
		}
		return true;
	}
	private void copySubString(char[] array, int offset, String t) {
		for(int i = 0; i < t.length(); i++) {
			array[offset + i] = t.charAt(i);
		}
	}
	private ArrayList<Integer> getAllMatches(char[] array, String s){
		ArrayList<Integer> matches = new ArrayList<Integer>();
		int i = 0;
		while(i <= array.length - s.length()) {
			if(equalSubString(array, i, s)) {
				// we record the match substring's end index rather than the start index for later convenience
				matches.add(i + s.length() - 1);
				i+=s.length();
			} else {
				i++;
			}
		}
		return matches;
	}
	public static void main(String[] args) {
		StringReplace solution = new StringReplace();
		String replace = solution.replace("appledogapple", "apple", "cat");
		System.out.println(replace);
	}
}
