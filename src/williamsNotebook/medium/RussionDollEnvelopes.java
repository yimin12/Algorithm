/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月11日 上午12:27:28
* Description:
* 	Give a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and 
* 	only if both the width and height of one envelope is greater than the width and height of the other envelope.
* 	Find the maximum number of nested layers of envelopes.
* Input：[[5,4],[6,4],[6,7],[2,3]]
* 	Output：3
* 	Explanation： the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
*/

public class RussionDollEnvelopes {

	int ans = 0;
	public int maxEnvelopes(int[][] envelopes) {
		if(envelopes == null || envelopes.length == 0) return 0;
		Arrays.sort(envelopes, Comparator.comparing(a -> a[0]));
		dfs(envelopes, 0, 0);
		return ans;
	}
	private void dfs(int[][] envelopes, int x, int num) {
		ans = Math.max(ans,  num);
		for(int i = x; i < envelopes.length; i++) {
			if(x == 0 || (envelopes[i][0] > envelopes[x-1][0] && envelopes[i][1] > envelopes[x-1][1])) {
				dfs(envelopes, i + 1, num + 1);
			}
		}
	}
	// 信封存在两个维度，首先贪心按照其中一个维度将信封排序，一个维然后在另度上面寻找最长上升子序列。
	//	此处使用二分优化最长上升子序列，在dp数组中二分查找envelope[1]的位置，当envelope[1]超出范围时会返回-（maxlength+1）
	//  需要特殊处理，dp[index] = envelope[1]，当返回的位置为最后为len时，说明插入位置为len，则长度可以+1。
	// Method 2: Dynamic Programming
	public int maxEnvelopesII(int[][] envelopes) {
		if(envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length != 2) {
			return 0;
		}
		Arrays.sort(envelopes, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					return o2[1] - o1[1];
				} else {
					return o1[0] - o2[0];
				}
			}
		});
		int dp[] = new int[envelopes.length];
		int len = 0;
		for(int[] envelope:envelopes) {
			// similar idea can be found at longest subsequence
			int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
			// the return index = -(low + 1)
			if(index < 0) index = - index - 1; // - index - 1 = low
			dp[index] = envelope[1];
			if(index == len) len++;
		}
		return len;
	}
	public static void main(String[] args) {
		RussionDollEnvelopes solution = new RussionDollEnvelopes();
		int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
		int maxEnvelopesII = solution.maxEnvelopesII(envelopes);
		System.out.println(maxEnvelopesII);
	}
}
