/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月3日 下午9:24:56
* Description:
* 	Given coins with different values and quantities, find out how many ways of total values in the range of [1, n] can be formed?
* 	Example 1:
* 		Input:  
		n = 5
		value = [1,4]
		amount = [2,1]
	Output:  4	
	Explanation: They can combine 4 numbers which are 1,2,4,5.
*/
public class BackPackVIII {

	/**
     * @param n: the value from 1 - n
     * @param value: the value of coins
     * @param amount: the number of coins
     * @return: how many different value
     */
	public int boundedBackPack(int n, int[] value, int[] amount) {
		if(value == null || amount == null || value.length * amount.length == 0) {
			return 0;
		}
		int m = value.length;
		boolean[] dp = new boolean[m+1];
		dp[0] = true;
		int res = 0;
		for(int i = 0; i < m; i++) {
			int[] cnt = new int[n+1]; // record how many coins we have already used
			for(int j = value[i]; j <= n; j++) {
				if(dp[j-value[i]] && !dp[j] && cnt[j-value[i]] < amount[i]) {
					cnt[j] += cnt[j - value[i]] + 1;
					res++;
					dp[j] = true;
				}
			}
		}
		return res;
	}
}
