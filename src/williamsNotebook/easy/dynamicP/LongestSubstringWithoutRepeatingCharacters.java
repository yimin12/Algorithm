/**
 * 
 */
package williamsNotebook.easy.dynamicP;

import java.util.HashSet;

/**
 * @author yimin Huang
 *	Given a string, find the length of the longest substring without repeating characters. For example, 
 *	the longest substring without repeating letters for "abcabcbb" is "abc", 
 *	which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 *
 * Algorithm Class
 */
public class LongestSubstringWithoutRepeatingCharacters {
	// Two pointers to simulate the sliding window and use HashTable to determine whether it has shown up already
	// Method 1: Two pointers + HashTable: Time : O(n), worst case Space : O(n) 
	public int lengthOfLongestSubstring(String s) {
		if(s == null || s.length() < 1) return 0;
		HashSet<Character> set = new HashSet<Character>();
		char[] array = s.toCharArray();
		int start = 0, maxLength = 1;
		set.add(array[0]);
		for(int i = 1; i < array.length; i++) {
			if(set.contains(array[i])) {
				maxLength = Math.max(i - start, maxLength);
				set.remove(array[start]);
				start++;
			}
			set.add(array[i]);
		}
		return Math.max(maxLength, array.length - start);
	}

	// Use Array to record whether the character has shown up already, and assume that all character can be represented by ASCII
	// Method 2: Optimization Two Pointers: Time : O(n), Space : O(256) 
	public int lengthOfLongestSubstringI(String s) {
		if(s == null || s.length() < 1) return 0;
		boolean[] shown = new boolean[256];
		int start = 0, maxLength = 1;
		for(int i = 0; i < s.length(); i++) {
			if(shown[s.charAt(i)] == true) {
				maxLength = Math.max(i - start, maxLength);
				shown[s.charAt(i)] = false;
				start++;
			}
			shown[s.charAt(i)] = true;
		}
		return Math.max(maxLength, s.length() - start);
	}
	
	// Key insight is similiar with Method2, but We can optimize the array further by using int array
	// Method 3: Better than Method2, Time : O(n), space: O(8)
	// we still need assume that all the char from given string can be represented by ASCII
	public int lengthOfLongestSubstringII(String s) {
		if(s == null || s.length() < 1) return 0;
		int[] shown = new int[8]; // 8 integer can represent 256 boolean[] array, because 8 int = 32 Bytes = 256 bits
		int start = 0, maxLength = 1, col = 0, row = 0; // initialize col, row and flag
		boolean flag = false;
		for(int i = 0; i < s.length(); i++){
			row = s.charAt(i) / 32;
			col = s.charAt(i) % 32;
			// test whether the corresponding position is 1
			flag = ((shown[row] >> col) & 1) == 1 ? true : false;
			if(flag) {
				maxLength = Math.max(i - start, maxLength);
				// set the corresponding bit to 0
				shown[row] &= ~(1 << col);
				start++;
			}
			shown[row] |= (1 << col);
		}
		return Math.max(maxLength, s.length() - start);
	}
	
	// Test Function
	public static void main(String[] args) {
		LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();
		System.out.println(solution.lengthOfLongestSubstringII("aaabcd"));
	}
}
