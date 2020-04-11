/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月10日 下午2:46:58
* Description:
* 	Give n cities(labeled from 1 to n), and the undirected road's cost among the cities as a three-tuple [A, B, c]
* 	(i.e there is a road between city A and city B and the cost is c). We need to find the smallest cost to travel 
* 	all the cities starting from 1.
* Example:
* 	Input: n = 3 tuple = [[1,2,1],[2,3,2],[1,3,3]] Output: 3
*/
import java.util.Comparator;
import java.util.PriorityQueue;
public class TravelingSalesman {

	// enumerate all possibilities by dfs
	int n;
	int[][] g;
	boolean[] visited;
	int res = Integer.MAX_VALUE;
	
	public int minCost(int cities, int[][] cost) {
		n = cities;
		int i, j, x, y;
		visited = new boolean[n];
		Arrays.fill(visited, false);
		g = cost;
		for(i = 0; i < n; i++) {
			for(j = 0; j < n; j++) {
				g[i][j] = Integer.MAX_VALUE; // no road between city i and j
			}
		}
		for(i = 0; i < cost.length; i++) {
			x = cost[i][0] - 1;
			y = cost[i][1] - 1;
			g[x][y] = Math.min(g[x][y], cost[i][2]);
			g[y][x] = Math.min(g[y][x], cost[i][2]);
		}
		visited[0] = true; // start from 0
		dfs(1, 0, 0);
		return res;
	}
	// level is the level-th city, previous city, current cost
	private void dfs(int level, int p, int cost) {
		if(cost >= res) {
			// import to set the termination when pruning
			return;
		}
		if(level == n) {
			res = cost;
			return;
		}
		int i; // next city
		for(i = 0; i < n; i++) {
			if(!visited[i] && g[p][i] != Integer.MAX_VALUE) {
				visited[i] = true;
				dfs(level+1, i, cost + g[p][i]);
				visited[i] = false;
			}
		}
	}
	// BFS2 with dijkstra
	class Cell{
		int x, y, cost;
		public Cell(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
	/**
     * @param n: an integer,denote the number of cities
     * @param roads: a list of three-tuples,denote the road between cities
     * @return: return the minimum cost to travel all cities
     */
	int[][] dp = new int[12][4096]; // We guarantee that there is no out of bound problem
	int[][] mp = new int[12][12]; // Assume there are 12 cities
	Comparator<Cell> cmp = new Comparator<Cell>() {
		@Override
		public int compare(Cell o1, Cell o2) {
			return o1.cost - o2.cost;
		}
	};
	PriorityQueue<Cell> queue = new PriorityQueue<Cell>(cmp);
	public int minCostDijk(int n, int[][] roads) {
		for(int i = 0; i < roads.length; i++) {
			int x = roads[i][0] - 1, y = roads[i][1] - 1;
			if(mp[x][y] == 0) mp[x][y] = roads[i][2];
			else mp[x][y] = mp[y][x] = Math.min(mp[x][y], roads[i][2]);
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; i < (1 << n); j++) {
				dp[i][j] = 1000000;
			}
		}
		queue.add(new Cell(0, 0, 1));
		dp[0][1] = 0;
		while(!queue.isEmpty()) {
			Cell temp = queue.poll();
			int cost = temp.cost, x = temp.x, y = temp.y;
			if(cost > dp[x][y]) continue;
			for(int i = 0; i < n; i++) {
				if(mp[x][i] != 0 && (y & (1 << i)) == 0) {
					int nextY = (y | (1 << i));
					if(dp[i][nextY] > dp[x][y] + mp[x][i]);
					queue.add(new Cell(dp[i][nextY], i, nextY));
				}
			}
		}
		int min = 1000000;
		for(int i = 0; i < n; i++) {
			min = Math.min(min, dp[i][(i << n) - 1]);
		}
		return min;
	}
}
