/**
 * 
 */
package DynamicProgramming;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月26日 下午3:18:22
* Description:
* 	Find the longest common substring of two given strings.
* Assumptions:
* 	The two given strings are not null
* Examples:
* 	S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd”
*/

public class LongestCommonSubstring {
	public int longestCommon(String s, String t) {
		char[] sa = s.toCharArray();
		char[] ta = t.toCharArray();
//		record the longest common substring's start position in s
		int start = 0;
//		record the longest common substring's length
		int longest = 0;
		int[][] common = new int[sa.length][ta.length];
		for(int i = 0; i < sa.length; i++) {
			for(int j = 0; j < ta.length; j++) {
				if(sa[i] == ta[j]) {
					if(i==0 || j==0) {
						common[i][j] = 1;
					} else {
						common[i][j] = common[i-1][j-1] + 1;
					}
//					determine whether should update the largest
					if(common[i][j] > longest) {
						longest = common[i][j];
//						when you find the largest candidate, record the start index
						start = i - longest + 1;
					}
				}
			}
		}
		return longest;
	}
}
