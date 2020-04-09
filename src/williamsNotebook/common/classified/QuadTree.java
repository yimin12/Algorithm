/**
 * 
 */
package williamsNotebook.common.classified;

import williamsNotebook.easy.binarySearch.KClosest;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月8日 上午11:34:48
* Description: LeetCode 427
* 	We want to use quad trees to store anN x N boolean grid. Each cell in the grid can only be true or false. The root 
* 	node represents the whole grid. For each node, it will be subdivided into four children nodes until the values in 
* 	the region it represents are all the same.
* 	Each node has another two boolean attributes :isLeafandval.isLeafis true if and only if the node is a leaf node. 
* 	The val attribute for a leaf node contains the value of the region it represents.
* 	Your task is to use a quad tree to represent a given grid. The following example may help you understand 
* 	the problem better:
*/

public class QuadTree {

	class Node{
		public boolean val, isLeaf;
		public Node topLeft, topRight, bottomLeft, bottomRight;
		public Node() {}
		public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
			super();
			this.val = val;
			this.isLeaf = isLeaf;
			this.topLeft = topLeft;
			this.topRight = topRight;
			this.bottomLeft = bottomLeft;
			this.bottomRight = bottomRight;
		}
		
	}
	public Node construct(int[][] grid) {
		return build(grid, 0, 0, grid.length - 1, grid[0].length - 1);
	}
	private Node build(int[][] grid, int r1, int c1, int r2, int c2) {
		if(r1 > r2 || c1 > c2) {
			return null;
		}
		if(isLeafNode(grid, r1, c1, r2, c2)) {
			return new Node(grid[r1][c1] == 1, true, null, null, null, null);
		}
		int rmid = r1 + (r2 - r1)/2;
		int cmid = c1 + (c2 - c1)/2;
		// false represent "*"
		return new Node(false, false, build(grid, r1, c1, rmid, cmid), build(grid, r1, cmid+1, rmid, c2)
				, build(grid, rmid+1, c1, r2, cmid),build(grid, rmid+1, cmid +1, r2, c2));
	}
	private boolean isLeafNode(int[][] grid, int r1, int c1, int r2, int c2) {
		int val = grid[r1][c1];
		for(int i = r1; i <= r2; i++) {
			for(int j = c1; j <= c2; j++) {
				if(val != grid[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
