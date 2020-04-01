/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yimin Huang
 * 		
 *		The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other
 *		Given an integer n, return all distinct solutions to the n-queens puzzle.
 *		Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an 
 *		empty space respectively
 *		
 * Algorithm Class
 */
public class NQueens {
		
	// Method 1: valid the queen position in O(n) time.
	// Time: O(n*n!) Space: O(n) for call stack and O(1) for checking the validation, So it is O(n)
	public List<List<Integer>> nqueens(int n){
		// Assumption: n > 0
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		// cur will be a list of size n, and cur[i] is the column number where the queen on row i positioned.
		List<Integer> cur = new ArrayList<Integer>();
		dfsHelper(n, cur, result);
		return result;
	}
	private void dfsHelper(int n, List<Integer> cur, List<List<Integer>> result) {
		// base case: when for all the rows we know where queen is positioned
		if(cur.size() == n) {
			result.add(new ArrayList<Integer>(cur));
			return;
		}
		// horizontal recursive rule
		for(int i = 0; i < n; i++) {
			// check if putting a queen column i at current row is valid
			if(valid(cur, i)) {
				cur.add(i);
				dfsHelper(n, cur, result);
				cur.remove(cur.size() - 1);
			}
		}
	}
	private boolean valid(List<Integer> cur, int column) {
		// pick the current row
		int row = cur.size();
		for(int i = 0; i < row; i++) {
			// satisfied condition: not in the same column and not in the diagonal column.
			if(cur.get(i) == column || Math.abs(cur.get(i) - column) == row - i) {
				return false;
			}
		}
		return true;
	}
	// Method 2: valid the queen position in O(1) each time
	public List<List<Integer>> nqueensII(int n){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		// cur will be a list of size n, and cur[i] is the column number where the queen on row i positioned.
		// use array rather than list
		int[] cur = new int[n];
		// record on which columns, diagonal line, and reverse diagonal line there is already a queen
		boolean[] usedColumns = new boolean[n];
		boolean[] usedDiagonals = new boolean[2 * n - 1];
		boolean[] usedRevDiagonals = new boolean[2 * n - 1];
		helper(n, 0, cur, result, usedColumns, usedDiagonals, usedRevDiagonals);
		return result;
	}
	private void helper(int n, int row, int[] cur, List<List<Integer>> result, boolean[] usedColumns, boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
		// base case: when for all the rows we know where the queen is positioned
		if(row == n) {
			result.add(toList(cur));
			return;
		}
		for(int i = 0; i < n; i++) {
			if(valid(n, row, i, usedColumns, usedDiagonals, usedRevDiagonals)) {
				mark(n, row, i, usedColumns, usedDiagonals, usedRevDiagonals);
				cur[row] = i;
				helper(n, row + 1, cur, result, usedColumns, usedDiagonals, usedRevDiagonals);
				unmark(n, row, i, usedColumns, usedDiagonals,usedRevDiagonals);
			}
		}
	}
	private boolean valid(int n, int row, int column, boolean[] usedColumns, boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
		return !usedColumns[column] && !usedDiagonals[column + row] && !usedDiagonals[column - row + n - 1];
	}
	private void mark(int n, int row, int column, boolean[] usedColumns, boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
		usedColumns[column] = true;
		usedDiagonals[column + row] = true;
		usedRevDiagonals[column - row + n - 1] = true;
	}
	private void unmark(int n, int row, int column, boolean[] usedColumns, boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
		usedColumns[column] = false;
		usedDiagonals[column + row] = false;
		usedRevDiagonals[column - row + n - 1] = false;
	}
	private List<Integer> toList(int[] array){
		List<Integer> cur = new ArrayList<Integer>();
		for(int num : array) {
			cur.add(num);
		}
		return cur;
	}
	
	// Follow Up 1. Return the number of solution, instead outputting board configurations, return the total number of distinct solutions.
	// DFS: Time ~ O(N!), Space ~ O(N)
	public int totalNQueens(int n) {
		// Assumption: the n > 0
		return dfs(n, 0, 0, 0, 0);
	}
	private int dfs(int n, int cur, long row, long diag, long revDiag) {
		int count = 0;
		long validate = (1 << n) - 1;
		if(row == validate) count = 1;
		else {
			long cand = -(row | diag | revDiag) & validate;
			while(cand > 0) {
				long pos = cand & -cand;
				cand -= pos;
				count += dfs(count, cur + 1, row | pos, (diag | pos) << 1, (revDiag | pos) >> 1);
			}
		}
		return count;
	}
	
	// Follow Up 2: Consider a N X N chessboard with a Queen and K obstacles. The Queen cannot pass through obstacles. Given the position (x, y) of Queen, 
	//		the task is to find the number of cells the queen can move.
	// DFS: Time ~ O(N!), Space ~ O(N)
	public int nqueensWithObstacle(int n, int k, int x, int y, int obstPosx[], int obstPosy[]){
		// d11, d12, d21, d22 are for diagnoal distances. 
        // r1, r2 are for vertical distance. 
        // c1, c2 are for horizontal distance. 
		int d11, d12, d21, d22, r1, r2, c1, c2;
		d11 = Math.min( x-1, y-1 ); 
	    d12 = Math.min( n-x, n-y ); 
	    d21 = Math.min( n-x, y-1 ); 
	    d22 = Math.min( x-1, n-y ); 
	    
	    r1 = y-1; 
        r2 = n-y; 
        c1 = x-1; 
        c2 = n-x;
        // For each obstacle find the minimum distance. 
        // If obstacle is present in any direction, 
        // distance will be updated.
        for (int i = 0; i < k; i++) 
        { 
            if ( x > obstPosx[i] && y > obstPosy[i] && 
                    x-obstPosx[i] == y-obstPosy[i] ) 
                d11 = Math.min(d11, x-obstPosx[i]-1); 
      
            if ( obstPosx[i] > x && obstPosy[i] > y && 
                    obstPosx[i]-x == obstPosy[i]-y ) 
                d12 = Math.min( d12, obstPosx[i]-x-1); 
      
            if ( obstPosx[i] > x && y > obstPosy[i] && 
                    obstPosx[i]-x == y-obstPosy[i] ) 
                d21 = Math.min(d21, obstPosx[i]-x-1); 
      
            if ( x > obstPosx[i] && obstPosy[i] > y && 
                        x-obstPosx[i] == obstPosy[i]-y ) 
                d22 = Math.min(d22, x-obstPosx[i]-1); 
      
            if ( x == obstPosx[i] && obstPosy[i] < y ) 
                r1 = Math.min(r1, y-obstPosy[i]-1); 
      
            if ( x == obstPosx[i] && obstPosy[i] > y ) 
                r2 = Math.min(r2, obstPosy[i]-y-1); 
      
            if ( y == obstPosy[i] && obstPosx[i] < x ) 
                c1 = Math.min(c1, x-obstPosx[i]-1); 
      
            if ( y == obstPosy[i] && obstPosx[i] > x ) 
                c2 = Math.min(c2, obstPosx[i]-x-1); 
        } 
        return d11 + d12 + d21 + d22 + r1 + r2 + c1 + c2; 
	}
}
