/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月31日 下午2:53:47
* Description:
* 	In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
* 	The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, 
* 	and was not an edge that already existed
* 	The resulting graph is given as a 2D-array ofedges. Each element ofedgesis a pair[u, v]that represents adirectededge connecting nodesuandv, whereuis a parent of childv
* 	Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.
* 	Input: [[1,2], [1,3], [2,3]] Output: [2,3]
* 	Explanation: The given directed graph will be like this:
* 	  1
	 / \
	v   v
	2-->3
*/
public class RedundantConnectionII {

	/*  Case 1:
		 *      每个节点都只有 一个 父亲节点，但是形成了环。
		 *      如：[1, 2], [2, 3], [3, 1]
		 *      这种情况下，题目退化成 Redundant Connection，只需要使用 Union Find 寻找
		 *      是因为哪条边形成了环，然后返回即可。
		 *  Case 2.1:
		 *      有某个节点存在 两个 父亲节点，但并没有形成环。(注意这里指的是有向图的环)
		 *      如：[1, 2], [1, 3], [2, 3]
		 *      这种情况下，我们需要删除最后一次遇到的形成环的边，即 [2, 3]
		 *  Case 2.2：
		 *      有某个节点存在 两个 父亲节点，并且形成了环。
		 *      如：[2, 1], [3, 1], [4, 2], [1, 4]
		 *      这种情况下，如果按找 Redundant Connection 中的做法就会出现错误，
		 *      因为形成环的边会被判断成 [1, 4], 而就算删除了这条边，1 仍然两个父亲节点，这是错误的。
		 */
	
	// Key insight: When we encounter a node that has two parents, we need to think one more step
	// 1. if exist cyclic loop, delete the parent that exist in the loop 
	// 2. if do not exist cyclic loop, delete the last encounter parent node
	
	// Method 1: Union Find
	class UnionFind{
		int[] parent;
		int[] rank;
		int size;
		public UnionFind(int size) {
			this.size = size;
			parent = new int[size];
			rank = new int[size];
			for(int i = 0; i < size; i++) {
				parent[i] = i;
			}
		}
		public int find(int x) {
			if(parent[x] != x) {
				parent[x] = find(parent[x]);
			}
			return parent[x];
		}
		public boolean union(int x, int y) {
			int xp = find(x);
			int yp = find(y);
			if(xp == yp) {
				return false; // do not need to union
			}
			if(rank[xp] > rank[yp]) {
				rank[yp] = xp;
			} else if(rank[xp] < rank[yp]) {
				rank[xp] = yp;
			} else{
				parent[yp] = xp;
				rank[xp]++;
			}
			return true; // operation success
		}
	}
	public int[] findRedundantDirectedConnection(int[][] edges) {
		HashSet<Integer> points = new HashSet<Integer>();
		HashMap<Integer, Integer> parent = new HashMap<Integer, Integer>();
		List<int[]> candidates = new ArrayList<int[]>();
		
		for(int[] edge:edges) {
			int src = edge[0];
			int dst = edge[1];
			points.add(src);
			points.add(dst);
			if(!parent.containsKey(dst)) {
				parent.put(dst, src);
				continue;
			}
			// if dst has been detected, there are two parents of dst
			candidates.add(new int[] {parent.get(dst),dst}); // case 1
			candidates.add(new int[] {src, dst}); // case 2; 
			// invalidate the second edge
			edge[1] = -1;
		}
		UnionFind uf = new UnionFind(points.size());
		for(int[] edge:edges) {
			// skip the invalidated edge
			if(edge[1] == -1) {
				continue;
			}
			int src = edge[0] - 1;
			int dst = edge[1] - 1;
			if(!uf.union(src, dst)) {
				// if we have invalidated the second edge
                // yet still have a cycle
                // we either just formed a cycle with this edge
                // or there still exists a node with two parents
                // which is the first edge stored in candidates.get(0)
				if(candidates.isEmpty()) {
					return edge;
				}
				return candidates.get(0);
			}
		}
		// no cycle found, meaning the redundant edge 
        // is the second edge in the candidates 
		return candidates.get(1);
	}
}
