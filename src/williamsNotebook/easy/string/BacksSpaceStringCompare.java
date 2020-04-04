/**
 * 
 */
package williamsNotebook.easy.string;

import java.util.Deque;
import java.util.LinkedList;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 下午2:19:27
* Description:
* 	Given two strings S andT, return if they are equal when both are typed into empty text editors.#means a backspace character.
* Example:
* 	Input: S = "ab#c", T = "ad#c" Output: true
* 	Explanation: Both S and T become "ac".
* Assumption: 
* 	S and T only contain lowercase letters and '#' characters.
* 	1 <= S.length <= 200
* 	1 <= T.length <= 200
*/
public class BacksSpaceStringCompare {

	// Use extra space for storing the result
	// Method 1: Easy way to implement by using stringBuilder or stack
	// Time : O(n) Space: O(n) worst case;
	public boolean backSpaceCompare(String s, String t) {
		return build(s).equals(build(t));
	}
	private String build(String s) {
		Deque<Character> stack = new LinkedList<Character>();
		for(char c : s.toCharArray()) {
			if(c != '#') {
				stack.push(c);
			} else if(!stack.isEmpty()){
				stack.pop();
			}
		}
		return String.valueOf(stack);
	}
	// Method 2: Two Pointer to simulate a stack
	public boolean backSpaceCompareII(String s, String t) {
		int i = s.length() - 1, j = t.length() - 1;
		int skipS = 0, skipT = 0;
		while(i >= 0 || j >= 0) {
			// While there may be chars in build(S) or build (T)
			while(i >= 0) {
				// Find position of next possible char in build(S)
				if(s.charAt(i) == '#') {
					skipS++;i--;
				} else if(skipS > 0) {
					skipS--;i--;
				} else break;
			}
			while(j >= 0) {
				// Find position of next possible char in build(T)
				if(s.charAt(j) == '#') {
					skipT++;j--;
				} else if(skipT > 0) {
					skipT--;j--;
				} else break;
			}
			 // If two actual characters are different
			if(i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) {
				return false;
			}
			// If expecting to compare char vs nothing
			if((i >= 0) != (j >= 0)) {
				return false;
			}
			i--;j--;
		}
		return true;
	}
}
