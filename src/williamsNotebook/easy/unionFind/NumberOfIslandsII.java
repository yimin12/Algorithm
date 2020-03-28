/**
 * 
 */
package williamsNotebook.easy.unionFind;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月27日 下午2:35:18
* Description:
* 	A 2d grid map ofmrows andncolumns is initially filled with water. We may perform anaddLandoperation which turns 
* 	the water at position (row, col) into a land. Given a list of positions to operate,count the number of islands after 
* 	each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or 
* 	vertically. You may assume all four edges of the grid are all surrounded by water.
* 	Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
* 	Output: [1,1,2,3]
* 	Can you do it in time complexity O(k log mn), where k is the length of the positions?
*/

public class NumberOfIslandsII {
	
	// Use rank to do path compression
	class UnionFind{
		int count; // # of isolated connected component. In this problem is, how many ones
		int[] parent;
		int[] rank;
		public UnionFind(char[][] grid) {
			count = 0;
			int m = grid.length;
			int n = grid[0].length;
			parent = new int[m*n];
			rank = new int[m*n];
			for(int i = 0; i < m; i++) {
				for(int j = 0; j < n; j++) {
					if(grid[i][j] == '1') {
						parent[i * n + j] = i * n + j;
						count++;
					}
					rank[i * n + j] = 0;
				}
			}
		}
		public UnionFind(int N) {
			count = 0;
			parent = new int[N];
			rank = new int[N];
			for(int i = 0; i < N; i++) {
				parent[i] = -1;
				rank[i] = 0;
			}
		}
		public boolean isValid(int i) {
			return parent[i] >= 0;
		}
		public void setParent(int i) {
			parent[i] = i;
			count++;
		}
		public int find(int i) {
			if(parent[i] != i) parent[i] = find(parent[i]);
			return parent[i];
		}
		public void union(int x, int y) {
			int parent_x = find(x);
			int parent_y = find(y);
			if(parent_x != parent_y) {
				if(rank[parent_x] > rank[parent_y]) {
					parent[parent_y] = parent_x;
				} else if(rank[parent_x] < rank[parent_y]) {
					parent[parent_x] = parent_y;
				} else {
					parent[parent_x] = parent_y;
					rank[parent_y]++;
				}
			}
			count--;
		}
		public int getCount() {
			return count;
		}
	}
	public List<Integer> numIsland(int m, int n, int[][] positions){
		List<Integer> res = new ArrayList<Integer>();
		UnionFind uf = new UnionFind(m * n);
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};
		for(int[] pos : positions) {
			int x = pos[0], y = pos[1];
			int id = x * n + y;
			uf.setParent(id);
			for(int k = 0; k < 4; k++) {
				int nextX = x + dx[k];
				int nextY = y + dy[k];
				int nextId = nextX * n + nextY;
				if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
					if(uf.isValid(nextId)) {
						uf.union(nextId, id);
					}
				}
			}
			res.add(uf.getCount());
		}
		return res;
	}
}
