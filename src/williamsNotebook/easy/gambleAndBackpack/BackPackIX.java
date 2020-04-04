/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月3日 下午9:36:32
* Description:
* 	You have a total of n thousand yuan, hoping to apply for a university abroad. The application is required to
* 	pay a certain fee. Give the cost of each university application and the probability 
* 	of getting the University's offer, and the number of university is m. If the economy allows, you can apply for multiple universities. 
* 	Find the highest probability of receiving at least one offer.
* Example 1:
	Input:  
		n = 10
		prices = [4,4,5]
		probability = [0.1,0.2,0.3]
	Output:  0.440
	
	Explanation：
	select the second and the third school. 
*/
public class BackPackIX {
	
	// Small trick: find the smallest possibility to get 0 offer, its opposite situation is the highest probability of receiving at least one offer
	public double backPack(int n, int[] prices, double[] probability) {
		if(prices == null || probability == null || prices.length != probability.length) {
			return -1;
		}
		double[] dp = new double[10010];
		for(int i = 0; i <= n; i++) {
			dp[i] = 1.0;
		}
		// inverse and calculate the possibility can not get offer
		for(int i= 0; i < probability.length; i++) {
			probability[i] = 1 - probability[i];			
		}
		dp[0] = 0;
		for(int i = 0; i < probability.length; i++) {
			for(int j = n; j >= prices[i]; j--) {
				// case 1: not submit the application, case 2: submit but not get offer
				dp[j] = Math.min(dp[j], dp[j-prices[i]] * probability[i]);
			}
		}
		// reverse back
		return 1 - dp[n];
	}
}
