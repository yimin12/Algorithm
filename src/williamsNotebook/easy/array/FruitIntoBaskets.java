/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.HashMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月1日 下午11:42:17
* Description:
* 	In a row of trees, thei-th tree produces fruit with type tree[i].
* 	You start at any tree of your choice, then repeatedly perform the following steps:
* 		Add one piece of fruit from this tree to your baskets. If you cannot, stop.
* 		Move to the next tree to the right of the current tree. If there is no tree to the right, stop.
* 	Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, 
* 	then step 2, then back to step 1, then step 2, and so on until you stop	
* 	You have two baskets, and each basket can carry any quantity of fruit, but you 
* 	want each basket to only carry one type of fruit each.
* 	What is the total amount of fruit you can collect with this procedure?
* Example:
* 	Input:  [1,2,1]  Output: 3 Explanation: We can collect [1,2,1].
*/
public class FruitIntoBaskets {

	// This question says tons of shit, In other word, What is the length of longest subarray that contains up to two distinct integers?
	public int totalFruit(int[] tree) {
		HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
		int firstIdx = 0;
		int totalMax = 0;
		for(int i = 0; i < tree.length; i++) {
			count.put(tree[i], count.getOrDefault(tree[i], 0) + 1);
			while(count.size() > 2) {
				count.put(tree[firstIdx], count.get(tree[firstIdx]) - 1);
				if(count.get(firstIdx) == 0) {
					count.remove(tree[firstIdx]);
				}
				firstIdx++;
			}
			totalMax = Math.max(totalMax, i - firstIdx + 1);
		}
		return totalMax;
	}
}
