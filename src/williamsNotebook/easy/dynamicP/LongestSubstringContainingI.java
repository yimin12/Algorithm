package williamsNotebook.easy.dynamicP;

/**
 * @author yimin Huang
 *
 *	Given a string, find the length of the longest substring T that contains at most 2 distinct characters
 * 	For example, Given s = “eceba”,
 *	T is "ece" which its length is 3.
 *	
 * Algorithm Class
 */
public class LongestSubstringContainingI {
	
	// First Version : Only contain two distinct
	// Using three pointers : Time : O(n), Space: O(1)
	// start - start index of current substring
	// cur - current (end) index of substring
	// last - index of the latest distinct character
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		int n = s.length();
		int start = 0, last = -1, max = 0;
		for(int cur = 0; cur < n; cur++) {
			if(s.charAt(cur) != s.charAt(cur - 1)) {
				if(last >= 0 && s.charAt(cur) != s.charAt(last)) {
					max = Math.max(max, cur-start);
					start = last+1;
				}
				last = cur - 1;
			}
		}
		return Math.max(n - start, max);
	}
	
	// Second Version: Contain multiple (k) distinct character
	// Using two pointers and HashTable : Time: O(n), Space: O(k)
	// Use HashTable to record the occurrence of Each char happen
	// If the given string can completely represented by ASCII, Time: O(n), Space: O(256) or O(8) which is O(constant)
	public int lengthOfLongestSubStringKDistinct(String s, int k) {
		int[] count = new int[256]; // Assuming all the char within the input are in the ASCII
		int start = 0, numDistinct = 0, maxLength = 0;
		for(int i = 0; i < s.length(); i++) {
			if(count[s.charAt(i)] == 0) numDistinct++;
			count[s.charAt(i)]++;
			// if it hit the upper boundary, you need to eliminate at least one type of character
			while(numDistinct > k) {
				count[s.charAt(start)]--;
				if(count[s.charAt(start)] == 0) numDistinct--;
				start++;
			}
			maxLength = Math.max(i - start + 1, maxLength);
		}
		return maxLength;
	}
}
