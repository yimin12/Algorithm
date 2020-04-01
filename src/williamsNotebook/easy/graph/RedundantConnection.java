/**
 * 
 */
package williamsNotebook.easy.graph;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月27日 下午9:35:23
* Description:
* 	In this problem, a tree is an undirected graph that is connected and has no cycles.
* 	The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with 
* 	one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge 
* 	that already existed.
* 	The resulting graph is given as a 2D-array ofedges. Each element ofedgesis a pair[u, v]withu < v, that represents 
* 	anundirectededge connecting nodesuandv
* 	Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, 
* return the answer that occurs last in the given 2D-array. The answer edge[u, v]should be in the same format, withu < v.
*/

public class RedundantConnection {
	
	//Redundant Connection 题给的是无向图，只需要删掉组成环的最后一条边即可，归根到底就是检测环就行了。
	static class UnionFind{
		int[] parent;
		int[] rank;
		int size;
		public UnionFind(int size) {
			parent = new int[size];
			for(int i = 0; i < size; i++) {
				parent[i] = i;
			}
			rank = new int[size];
			this.size = size;
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
				return false;
			}
			if(rank[xp] < rank[yp]) {
				parent[xp] = yp;
			} else if(rank[xp] > rank[yp]) {
				parent[yp] = xp;
			} else {
				parent[yp] = xp;
				rank[xp]++;
			}
			return true;
		}
	}
	public int[] findRedundantConnection(int[][] edges) {
		int N = edges.length;
		UnionFind uf = new UnionFind(N);
		for(int[] edge:edges) {
			// because the label start from 1 and index start from 0
			if(!uf.union(edge[0] - 1, edge[1] - 1)) {
				return edge;
			}
		}
		return new int[0];
	}
}
