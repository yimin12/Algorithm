/**
 * 
 */
package williamsNotebook.medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import williamsNotebook.common.nest.NestedInteger;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午6:42:05
* Description:
* 	Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
* 	Each element is either an integer, or a list -- whose elements may also be integers or other lists.
*	Different from theprevious questionwhere weight is increasing from root to leaf, now the weight is 
*	defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have
*   the largest weight.
*   Input:   			Output: 
*   	[[1,1],2,[1,1]]			8
*/
public class NestedListWeightSum {
	public int depthSumInverse(List<NestedInteger> nestList) {
		if(nestList == null) return 0;
		Deque<NestedInteger> queue = new LinkedList<NestedInteger>();
		int prev = 0, res = 0;
		for(NestedInteger next : nestList) {
			queue.offer(next);
		}
		while(!queue.isEmpty()) {
			int size = queue.size();
			int levelSum = 0;
			for(int i = 0; i < size; i++) {
				NestedInteger current = queue.poll();
				if(current.isInteger()) levelSum += current.getInteger();
				List<NestedInteger> nextList = current.getList();
				if(nextList != null) {
					for(NestedInteger next:nextList) {
						queue.offer(next);
					}
				}
			}
			// it will add multiple times of prev 
			prev += levelSum;
			res += prev;
		}
		return res;
	}
}
