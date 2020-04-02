/**
 * 
 */
package williamsNotebook.easy.backTracking;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月1日 下午7:33:07
* Description:
* 	Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used 
* 	and each combination should be a unique set of numbers.
* Assumption:
* 	All numbers will be positive integers.
* 	The solution set must not contain duplicate combinations.
* Example:
* 	Input: k = 3, n = 7
* 	Output: [[1,2,4]]
* 	Input: k = 3, n = 9
* 	Output: [[1,2,6], [1,3,5], [2,3,4]]
*/
public class CombinationSumII {
	
	// Regular dfs
	public List<List<Integer>> combinationSumIII(int k, int n){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(k <= 0 || n < 0) return res;
		backTracking(1,k,n,new ArrayList<Integer>(), res);
		return res;
	}
	private void backTracking(int index, int k, int remain, List<Integer> list, List<List<Integer>> res) {
		if(remain == 0) {
			if(list.size() == k) {
				res.add(new ArrayList<Integer>(list));
			}
			return;
		}
		for(int i = index; i <= 9; i++) {
			list.add(i);
			backTracking(i+1, k, remain-i, list, res);
			list.remove(list.size()-1);
		}
	}
	
}
