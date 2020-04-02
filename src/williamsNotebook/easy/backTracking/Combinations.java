/**
 * 
 */
package williamsNotebook.easy.backTracking;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月1日 下午4:15:11
* Description:
* 	Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
* Input:  n = 4, k = 2               Compare with Permutation
		Output:							Output:	
		[									[	
		  [2,4],							  [2,4],[4,2]
		  [3,4],							  [3,4],[4,3]	
		  [2,3],							  [2,3],[3,2]	
		  [1,2],							......]
		  [1,3],
		  [1,4],
		]
*/

public class Combinations {
	
	// Vertical DFS
	public List<List<Integer>> combination(int n, int k){
		// Assumption: n > 0 and n > k
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(n < 0 || n < k) return res;
		verticalDFS(n, 1, k, new ArrayList<Integer>(), res);
		return res;
	}
	private void verticalDFS(int n, int index, int k, List<Integer> list, List<List<Integer>> res) {
		if(list.size() == k) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		if(index >= n) {
			return;
		}
		// decide to pick current index
		list.add(index);
		verticalDFS(n, index+1, k, list, res);
		list.remove(list.size() - 1);
		verticalDFS(n, index+1, k, list, res);
	}
	// Horizontal DFS
	public List<List<Integer>> combinationII(int n, int k){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(n < 0 || n < k) return res;
		horizontalDFS(n, 1, k, new ArrayList<Integer>(), res);
		return res;
	}
	private void horizontalDFS(int n, int index, int k, List<Integer> list, List<List<Integer>> res) {
		if(list.size() == k) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		for(int i = index; i <= n; i++) {
			list.add(index);
			horizontalDFS(n, i + 1, k, list, res);
			list.remove(list.size() -1);
		}
	}
}
