/**
 * 
 */
package williamsNotebook.easy.dynamicP;

import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 下午12:17:38
* Description:
* 	Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below
* For example, given the following triangle
* 	[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
	]
  The minimum path sum from top to bottom is11(i.e.,2+3+5+1= 11).
*/
public class Triangle {

	// minpath[k][i] = min( minpath[k+1][i], minpath[k+1][i+1]) + triangle[k][i];
	// minpath[k][i] represent minimum path sum walk to kth row
	// For the kth level: minpath[i] = min( minpath[i], minpath[i+1]) + triangle[k][i]; // Space Optimization
	//	其中minpath[k][i]表示第k行，第i列的元素到bottom的最小路径和, 即min path sum
	public int minimumTotal(List<List<Integer>> triangle) {
		int size = triangle.size();
		// 	这样就可以直接从Bottom那一行开始运用递推关系，而不用从先复制最后一行赋值给DP数组，再从倒数第二行开始用递推关系。
		int[] minPath = new int[size+1];
		for(int row = size - 1; row >= 0; row--) {
			for(int i = 0; i <= row; i++) { // there are row cols in the ith rows
				minPath[i] = Math.min(minPath[i], minPath[i+1]) + triangle.get(row).get(i);
			}
		}
		return minPath[0];
	}
}
