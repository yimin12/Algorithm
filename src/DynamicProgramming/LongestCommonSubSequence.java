/**
 * 
 */
package DynamicProgramming;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月26日 下午4:31:07
* Description:
* 	Find the length of longest common subsequence of two given strings.
* Assumptions:
* 	The two given strings are not null
* Examples:
* 	S = “abcde”, T = “cbabdfe”, the longest common subsequence of s 
* 	and t is {‘a’, ‘b’, ‘d’, ‘e’}, the length is 4.
*/

public class LongestCommonSubSequence {
	public int longest(String s, String t) {
//		Assumption: s, t are not null, using the trick of "add 1" index, will make the base case easier to handle
//		e.g. the 0th row and the 0th column will be all zero
		int[][] longest = new int[s.length()][t.length()];
		for(int i = 1; i < s.length();i++) {
			for (int j = 1; j < t.length(); j++) {
				if(s.charAt(i - 1) == t.charAt(j - 1)) {
					longest[i][j] = longest[i-1][j-1] + 1;
				} else {
					longest[i][j] = Math.max(longest[i-1][j], longest[i][j-1]);
				}
			}
		}
		return longest[s.length()][t.length()];
	}
}
