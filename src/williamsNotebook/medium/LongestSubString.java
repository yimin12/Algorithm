/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;

/**
 * @author yimin Huang
 *
 *	Given a string, find the length of the longest substring T that contains at most 2 distinct characters
 *	For example, Given s = “eceba”,
 *	T is "ece" which its length is 3.
 *
 * Algorithm Class
 */
public class LongestSubString {

	// Sliding window by using three pointers
	public int lengthOfTwoDistinct(String s) {
		if(s == null || s.length() == 0) return 0;
		int n = s.length();
		int start = 0, last = -1, max = 0;
		for(int cur = 1; cur < n; cur++) {
			if(s.charAt(cur) != s.charAt(cur-1)) {
				if(last > 0 && s.charAt(cur) != s.charAt(last)) {
					max = Math.max(max, cur - start);
					start = last + 1;
				}
			}
		}
		return Math.max(max,  n - start);
	}
	
	// Follow Up 1, k distinct character.
	// Two Pointers and HashTable Time: O(2N) and Extra Space O:(1)
	public int lengthOfKDistinctSubString(String s, int k) {
		int[] count = new int[256];
		int start = 0, numDistinct = 0, maxLen = 0;
		for(int i = 0; i < s.length(); i++) {
			if(count[s.charAt(i)] == 0) numDistinct++;
			count[s.charAt(i)]++;
			while(numDistinct > k) {
				count[s.charAt(i)]--;
				if(count[s.charAt(start)] == 0) numDistinct--;
				start++;
			}
			maxLen = Math.max(i - start + 1, maxLen);
		}
		return maxLen;
	}
	
	// Follow Up 2: All Distinct without repeating
	// Method 1: Two pointer and HashTable , Time: O(2N) and Extra Space: O(1)
	public int lengthOfLongest(String s) {
		boolean[] exist= new boolean[256];
		int start = 0, maxLen = 0;
		for(int i = 0; i < s.length(); i++) {
			while(exist[s.charAt(i)]) {
				exist[s.charAt(start++)] = false;
			}
			exist[s.charAt(i)] = true;
			maxLen = Math.max(i-start+1, maxLen);
		}
		return maxLen;
	}
	// Method 2: 
	public int lengthOfLongestII(String s) {
		int[] charMap = new int[256];
		Arrays.fill(charMap, -1);
		int start = 0, maxLen = 0;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(charMap[c] >= start) start = charMap[c]+1;
			charMap[c] = i;
			maxLen = Math.max(maxLen, i - start + 1);
		}
		return maxLen;
	}
	
}
