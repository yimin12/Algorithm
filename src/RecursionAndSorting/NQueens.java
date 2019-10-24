/**
 * 
 */
package RecursionAndSorting;

import java.util.ArrayList;
import java.util.List;



/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月16日 上午10:21:23
* Description:
* 	Get all valid ways of putting N Queens on an N * N chessboard so that 
* 	no two Queens threaten each other.
* Assumption: n > 0;
* Return: 
* 	A list of ways of putting the N Queens
* 	Each way is represent by a list of the Queens y index for x indices of 0 to (N-1)
* Example
	N = 4, there are two ways of putting 4 queens:
	[1, 3, 0, 2] --> the Queen on the first row is at y index 1, 
	the Queen on the second row is at y index 3, the Queen on the third row 
	is at y index 0 and the Queen on the fourth row is at y index 2.

	[2, 0, 3, 1] --> the Queen on the first row is at y index 2, 
	the Queen on the second row is at y index 0, the Queen on the third row 
	is at y index 3 and the Queen on the fourth row is at y index 1.
*/



public class NQueens {

	//Method 1: Validate the queen position in O(n) each time
	public List<List<Integer>> nqueens(int n){
//		Assumption: n > 0
		List<List<Integer>> result = new ArrayList<List<Integer>>();
//		cur will be a list of size n, and cur[i] is the column number where the queen on row i positioned
		List<Integer> cur = new ArrayList<Integer>();
		helper(n, cur, result);
		return result;
	}
	private void helper(int n, List<Integer> cur, List<List<Integer>> result) {
//		base case: when for all rows we know where the queen is positioned
		if(cur.size() == n) {
			result.add(new ArrayList<Integer>(cur));
			return;
		}
		for(int i = 0; i < n; i++) {
//			check if putting a queen at column i at current row is valid
			if(valid(cur, i)) {
				cur.add(i);
				helper(n, cur, result);
				cur.remove(cur.size()-1);
			}
		}
	}
	private boolean valid(List<Integer> cur, int column) {
		int row = cur.size();
		for(int i = 0; i < row; i++) {
			if(cur.get(i) == column || Math.abs(cur.get(i - column))== row - i) {
				return false;
			}
		}
		return true;
	}
	
//	Method 2: validate the queen position O(1) each time
	public List<List<Integer>> nqueensII(int n){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
//		cur will be list of size n, and cur[i] is the column number where the queen on row i positioned
		int[] cur = new int[n];
//		record on which columns there is already a queen;
		boolean[] usedColumns = new boolean[n];
//		record on which diagonal lines there is already a queen
		boolean[] usedDiagonals = new boolean[2*n-1];
//		record on which reverse diagonal lines there is already a queue
		boolean[] usedRevDiagonals = new boolean[2*n-1];
		helper(n, 0,cur, result, usedColumns, usedDiagonals, usedRevDiagonals);
		return result;
	}
	private void helper(int n, int row, int[] cur, List<List<Integer>> result, boolean[] usedColumns,
			boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
//		base case: when for all the rows we know where the queue is positioned
		if(row == n) {
			result.add(toList(cur));
			return;
		}
		for(int i = 0; i < n; i++) {
			if(validI(n, row, i, usedColumns, usedDiagonals, usedRevDiagonals)) {
				mark(n, row, i, usedColumns, usedDiagonals, usedRevDiagonals);
				cur[row] = i;
				helper(n, row+1, cur, result, usedColumns, usedDiagonals,usedRevDiagonals);
				unmark(n, row, i, usedColumns, usedDiagonals, usedRevDiagonals);
			}
		}
	}
	
	private boolean validI(int n, int row, int column, boolean[] usedColumns
			, boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
//		for the reverse diagonal line, the actual range of column - row is actually [-(n-1), +(n+1)]
//		we add n-1 to make sure it can fit into the index
		return !usedColumns[column]&& !usedDiagonals[column+row] && !usedRevDiagonals[column - row + n - 1];
	}
	
	private void mark(int n, int row, int column, boolean[] usedColumns
			, boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
		usedColumns[column] = true;
		usedDiagonals[column + row] = true;
		usedRevDiagonals[column - row + n - 1] = true;
	}
	
	private void unmark(int n, int row, int column, boolean[] usedColumns
			, boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
		usedColumns[column] = false;
		usedDiagonals[column + row] = false;
		usedRevDiagonals[column - row + n - 1] = false;
	}
	private List<Integer> toList(int[] array){
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		return list;
	}
}
