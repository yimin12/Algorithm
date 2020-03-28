/**
 * 
 */
package williamsNotebook.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yimin Huang
 *		
 *		Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'. A region is captured by 
 *		flipping all 'O's into 'X's in that surrounded region.
 *		For example,		After running your function, the board should be:
			X X X X																X X X X			
			X O O X																X X X X			
			X X O X																X X X X			
			X O X X																X O X X
 *
 * Algorithm Class
 */
public class SurroundedRegions {

	
	// Method 1: BFS to traverse four direction, we should solve the problem in-place
	// Assumption: the given board is valid
	// 1 represent the "X" and 0 represent "O" 
	// if it can be touched from the boundary, means that it is valid, and all the elements that is still unchanged O will be turn to 0 
	// BFS: Time ~ O(N^2), Space ~ O(N^2)
	public void solveBFS(int[][] board) {
		if(board == null || board.length == 0 || board[0].length == 0) return;
		int m = board.length, n = board[0].length;
		if(m <= 2 || n <= 2) return;
		// run flood fill algorithm (BFS) on every boundary point
		for(int i = 0; i < m; i++) {
			bfs(board, i, 0); // first column
			bfs(board, i, n - 1); // last column
		}
		for(int j = 1; j < n - 1; j++) {
			bfs(board, 0, j); // first row,
			bfs(board, m - 1, j); // last row
		}
		// flip 0 to 1
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j] == 0) board[i][j] = 1;
				else if(board[i][j] == -1) board[i][j] = 0;
			}
		}
	}
	private void bfs(int[][] board, int i, int j) {
		Queue<Integer> q = new LinkedList<Integer>();
		visited(board, i, j, q);
		while(!q.isEmpty()) {
			int pos = q.poll();
			int x = pos / board[0].length;
			int y = pos % board[0].length;
			visited(board, x-1, y, q);
			visited(board, x+1, y, q);
			visited(board, x, y+1, q);
			visited(board, x, y-1, q);
		}
	}
	private void visited(int[][] board, int i, int j, Queue<Integer> q) {
		if(i < 0 || i > board.length - 1 || j < 0 || j > board[0].length || board[i][j] != 0) {
			return;
		}
		board[i][j] = -1;
		q.add(i * board[0].length + j);
	}
	
	// Method 2: Traversing while using DFS 
	public void solveDFS(int[][] board) {
		if(board == null || board.length == 0 || board[0].length == 0) return;
		int m = board.length, n = board[0].length;
		if(m <= 2 || n <= 2) return;
		for(int i = 0; i < m; i++) {
			dfs(board, i, 0);
			dfs(board, i, n - 1);
		}
		for(int j = 0; j < n; j++) {
			dfs(board, 0, j);
			dfs(board, m-1,j);
		}
		// flip 0 to 1
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j] == 0) board[i][j] = 1;
				else if(board[i][j] == -1) board[i][j] = 0;
			}
		}
	}
	private void dfs(int[][] board, int i, int j) {
		if(board[i][j] == 0) {
			board[i][j] = -1;
			int m = board.length, n = board[0].length;
			// flooding by dfs
			if(i + 1 < m) dfs(board, i+1, j); 
			if(i - 1 >= 0) dfs(board, i-1, j);
			if(j - 1 >= 0) dfs(board, i, j-1);
			if(j + 1 < 0) dfs(board, i, j+1);
		}
	}
	// Method3: Union Find
	static class UnionFind{
		private int[] id;
		private int[] size;
		public UnionFind(int n) {
			id = new int[n];
			size = new int[n];
		}
		public int find(int i) {
			while(i != id[i]) {
				id[i] = id[id[i]];
				i = id[i];
			}
			return i;
		}
		public void union(int x, int y) {
			int i = find(x);
			int j = find(y);
			if(i == j) return;
			if(size[i] < size[j]) {
				id[i] = j;
				size[j] += size[i];
			} else {
				id[j] = i;
				size[i] += size[j];
			}
		}
		public boolean connected(int x, int y) {
			return find(x) == find(y);
		}
	}
	private boolean isEdge(int M, int N, int i, int j) {
		return (i == 0 || i == M - 1 || j == 0 || j == N - 1);
	}
	private boolean insideBoard(int M, int N, int i, int j) {
		return (i < M && i >= 0 && j < N && j >= 0);
	}
	public void surroundedRegions(char[][] board) {
		if(board == null || board.length == 0 || board[0].length == 0) {
			return;
		}
		int M = board.length;
		int N = board[0].length;
		int[] dirX = {0,0,-1,1};
		int[] dirY = {-1,1,0,0};
		UnionFind uf = new UnionFind(M*N + 1);
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(board[i][j] == 'O') {
					if(isEdge(M,N,i,j)) {
						uf.union(i * N + j, M * N);
					} else {
						for(int k = 0; k < 4; k++) {
							int x = i + dirX[k];
							int y = j + dirY[k];
							if(insideBoard(M, N, x, y) && board[x][y] == 'O') {
								uf.union(i * N + j, x * N + y);
							}
						}
					}
				}
			}
		}
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(!uf.connected(i * N + j, M * N)) {
					board[i][j] = 'X';
				}
			}
		}
	}
}
