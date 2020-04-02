/**
 * 
 */
package williamsNotebook.easy.array;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 上午11:42:49
* Description:
* 	Given a string that consists of only upper case English letters, you can replace any letter in the string with 
* 	another letter at most k times. Find the length of a longest substring containing all repeating letters you can 
* 	get after performing the above operations.
* 	Both the string's length and k will not exceed 104.
* Example:
* 	Input: s = "ABAB", k = 2
*/
public class LongestRepeatingCharacterReplacement {

	// Method 1: Two Pointer and maintain a HashMap
	public int characterReplacement(String s, int k) {
		if(s == null || s.length() == 0 || k < 0) return 0;
		// Assume that the string contains char in upper case
		int[] count = new int[26];
		int maxCount = 0, maxLen = 0;
		int left = 0;
		for(int right = 0; right < s.length(); right++) {
			count[s.charAt(right) - 'A'] += 1;
			maxCount = Math.max(maxCount, count[s.charAt(right) - 'A']);
			while(right - left + 1 - maxCount > k) {
				count[s.charAt(left) - 'A'] -= 1;
				left++;
			}
			maxLen = Math.max(maxLen, right -left + 1);
		}
		return maxLen;
		
	}
}
