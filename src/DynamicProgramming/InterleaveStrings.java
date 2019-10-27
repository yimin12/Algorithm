/**
 * 
 */
package DynamicProgramming;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月27日 下午12:16:29
* Description:
* 	Given three strings A, B and C. Determine if C can be created by merging 
* 	A and B in a way that maintains the relative order of the characters in A and B.
* Assumption:
* 	none of A,B,C is null
* Examples:
* 	C = "abcde", A = "acd", B = "be", return true
* 	C = "abcde", A = "adc", B = "be", return false
*/

public class InterleaveStrings {
	public boolean canMerge(String a, String b, String c) {
//		Assumption: a, b, c are not null
		int alen = a.length();
		int blen = b.length();
		int clen = c.length();
		if(alen + blen != clen) {
			return false;
		}
//		a common trick is to use i to represent the ith character in a, the 1st character is actually 
//		a.charAt(0). The benefit is we can eliminate some base case(empty case)
		boolean[][] canMerge = new boolean[alen+1][blen+1];
		for(int i = 0; i <= alen; i++) {
			for(int j = 0; j <= blen; j++) {
//				this is the only base case we need to take care
				if(i == 0 && j == 0) {
					canMerge[i][j] = true;
				}
//				two possible ways of matching the character in c
				if(i > 0 && b.charAt(i - 1) == c.charAt(i+j-1)) {
					canMerge[i][j] |= canMerge[i-1][j];
				}
				if(j > 0 && b.charAt(j - 1) == c.charAt(i+j-1)) {
					canMerge[i][j] |= canMerge[i][j-1];
				}
			}
		}
		return canMerge[alen][blen];
				
	}
}
