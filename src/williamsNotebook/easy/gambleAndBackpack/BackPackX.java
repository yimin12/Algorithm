/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月3日 下午10:00:48
* Description:	
* 	You have a total of n yuan. Merchant has three merchandises and their prices are 150 yuan, 250 yuan and 350 yuan. 
* 	And the number of these merchandises can be considered as infinite. After the purchase of goods need to be the 
* 	remaining money to the businessman as a tip, finding the minimum tip for the merchant.
* 	Input:  n = 900
	Output:  0
*/
public class BackPackX {

	// Complete BackPack, every item is infinite
	// This question equal to find the maximum value we can get by not exceeding n yuan
	public int backPack(int[] prices, int n) {
		if(prices == null || prices.length == 0) {
			return 0;
		}
		int[] dp = new int[n + 1];;
		for(int i = 0; i < 3; i++) {
			for(int j = prices[i]; j <= n; j++) {
				dp[j] = Math.max(dp[j], dp[j - prices[i]] + prices[i]);
			}
		}
		int res = n - dp[n];
		return res;
	}
}
