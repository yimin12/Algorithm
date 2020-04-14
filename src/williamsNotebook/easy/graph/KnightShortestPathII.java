/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月11日 下午12:43:27
* Description:
* 	Given a knight in a chessboard n * m (a binary matrix with 0 as empty and 1 as barrier). the knight initialze 
* 	position is (0, 0) and he wants to reach position (n - 1, m - 1), Knight can only be from left to right. Find 
* 	the shortest path to the destination position, return the length of the route. Return -1 if knight can not reached.
* 	If the knight is at (x, y), he can get to the following positions in one step:
* 	(x + 1, y + 2)
	(x - 1, y + 2)
	(x + 2, y + 1)
	(x - 2, y + 1)
  Example 1:
	[[0,0,0,0],[0,0,0,0],[0,0,0,0]]      Return: 3
  Explanation:	 [0,0]->[2,1]->[0,2]->[2,3]
*/
public class KnightShortestPathII {

	// 日字型走法
	// 单向BFS走法
	// Method 1: Dynamic programming
	static final int[][] DIRS = {{-1,-2},{1,-2},{-2,-1},{2,1}}; // coming from
	public int shortestPathDP(boolean[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0 || grid[0][0]) {
			return -1;
		}
		int m = grid.length, n = grid[0].length;
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; i++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		dp[0][0] = 0;
		for(int j = 0; j < n; j++) {
			for(int i = 0; i < m; i++) {
				if(!grid[i][j]) {
					for(int k = 0; k < 4; k++) {
						int x = i + DIRS[k][0], y = j + DIRS[k][1];
						if(x >= 0 && x < m && y >= 0 && y < n && dp[x][y] != Integer.MAX_VALUE) {
							dp[i][j] = Math.min(dp[i][j], dp[x][y] + 1);
						}
					}
				}
			}
		}
		return dp[m-1][n-1] == Integer.MAX_VALUE ? -1 : dp[m-1][n-1];
	}
	// Version 2:
	public int shortestPath2(boolean[][] grid) {
        // Write your code here
        int n = grid.length;
        if (n == 0)
            return -1;
        int m = grid[0].length;
        if (m == 0)
            return -1;

        int[][] f = new int[n][m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                f[i][j] = Integer.MAX_VALUE;

        f[0][0] = 0;
        for (int j = 1; j < m; ++j)
          for (int i = 0; i < n; ++i)
            if (!grid[i][j]) {
                if (i >= 1 && j >= 2 && f[i - 1][j - 2] != Integer.MAX_VALUE)
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 2] + 1);
                if (i + 1 < n && j >= 2 && f[i + 1][j - 2] != Integer.MAX_VALUE)
                    f[i][j] = Math.min(f[i][j], f[i + 1][j - 2] + 1);
                if (i >= 2 && j >= 1 && f[i - 2][j - 1] != Integer.MAX_VALUE)
                    f[i][j] = Math.min(f[i][j], f[i - 2][j - 1] + 1);
                if (i + 2 < n && j >= 1 && f[i + 2][j - 1] != Integer.MAX_VALUE)
                    f[i][j] = Math.min(f[i][j], f[i + 2][j - 1] + 1);
            }

        if (f[n - 1][m - 1] == Integer.MAX_VALUE)
            return -1;

        return f[n - 1][m - 1];
    }
	// Method 3: memory Search 本质是通过dfs或者bfs进行记忆化搜索的一个过程
	// 双向bfs
	static final int[][] dirs = {{1,2},{-1,2},{2,1},{-2,1}}; // Coming to 
	public int shortestPathBiDirection(boolean[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0 || grid[0][0]) {
			return -1;
		}
		// following corner case is necessary for biDirection DFS
		int m = grid.length, n = grid[0].length;
		if(grid[0][0] || grid[m-1][n-1]) return -1; // barrier at start and end point
		if(m == 1 && n == 1) return 0; // there only one point
		
		int res = 0;
		Queue<Integer> queA = new LinkedList<Integer>();
		boolean[][] vA = new boolean[m][n];
		queA.offer(0); // add the start point
		vA[0][0] = true;
		
		Queue<Integer> queB = new LinkedList<Integer>();
		boolean[][] vB = new boolean[m][n];
		queB.offer(m * n - 1); // add the end point
		vB[m-1][n-1] = true;
		int sign = 1;
		
		Queue<Integer> queCur = null;
		boolean[][] vCur = null, vOp = null; // These are used for shuffle
		
		while(!queA.isEmpty() && !queB.isEmpty()) {
			if(queA.size() <= queB.size()) {
				queCur = queA;
				vCur = vA;
				vOp = vB;
				sign = 1; // from start to the end
			} else {
				queCur = queB;
				vCur = vB;
				vOp = vA;
				sign = -1; // from the end to the start
			}
			// level Order traverse
			int l = queCur.size();
			res++;
			while(l-- != 0) {
				int cur = queCur.poll();
				int x = cur / n, y = cur % n;
				for(int i = 0; i < 4; i++) {
					int xx = x + sign*dirs[i][1], yy = y + sign*dirs[i][2];
					if(xx >= 0 && xx < m && yy >= 0 && yy < n && !grid[xx][yy]) {
						if(vOp[xx][yy]) {
							return res;
						}
						if(!vCur[xx][yy]) {
							queCur.offer(xx * + yy);
							vCur[xx][yy] = true;
						}
					}
				}
			}
		}
		return -1;
	}
}
