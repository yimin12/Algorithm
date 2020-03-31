/**
 * 
 */
package williamsNotebook.easy.random;

import java.util.Random;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月31日 上午12:10:01
* Description:
* 	Given an arraywof positive integers, wherew[i]describes the weight of indexi, write a functionpickIndex which randomly picks an index in proportion to its weight.
* 	Input: ["Solution","pickIndex"] [[[1]],[]]
* 	Output: [null,0]
* 	The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the arrayw.pickIndexhas 
* 	no arguments. Arguments are always wrapped with a list, even if there aren't any.
*/
public class RandomPickWithWeight {
	
	private int[] prefixSum;
	private int total;
	private Random rand;
	public RandomPickWithWeight(int[] weighted) {
		prefixSum = new int[weighted.length];
		rand = new Random();
		total = 0;
		for(int i = 0; i < weighted.length; i++) {
			total += weighted[i];
			prefixSum[i] = total;
		}
	}
	// Time: O(n)to init, O(logn)for one pick
	// Space: O(n)
	public int pickIndex() {
		// choose [1,total] with equal possibility
		int r = rand.nextInt(total) + 1;
		int start = 0, end = prefixSum.length - 1;
		while(start <= end) {
			int mid = start + (end - start)/2;
			if(prefixSum[mid] == r) {
				return mid;
			} else if(prefixSum[mid] > r) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
}
