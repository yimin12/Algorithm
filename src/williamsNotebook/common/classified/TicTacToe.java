/**
 * 
 */
package williamsNotebook.common.classified;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2020��3��26�� ����6:00:05
* Description:
* 	Design a Tic-tac-toe game that is played between two players on anxngrid.
* You may assume the following rules:
* 	A move is guaranteed to be valid and is placed on an empty block.
* 	Once a winning condition is reached, no more moves is allowed.
* Example:
* 	Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.	
*/
// Method 1: regular way Time: O(n^2) Space : O(n^2)
public class TicTacToe {
	private char[][] board;
	private static char X = 'X';
	private static char O = 'O';
	private int size;
	
	public TicTacToe(int n) {
		this.board = new char[n][n];
		this.size = n;
	}
	  /** Player {player} makes a move at ({row}, {col}).
    @param row The row of the board.
    @param col The column of the board.
    @param player The player, can be either 1 or 2.
    @return The current winning condition, can be either:
            0: No one wins.
            1: Player 1 wins.
            2: Player 2 wins. */
	public int move(int row, int col, int player) {
		char c;
		if(player == 1) {
			c = X;
		} else {
			c = O;
		}
		if(board[row][col] != 0) {
			return 0;
		}
		board[row][col] = c;
		if(hasWon(row, col, size, c)) {
			return player;
		}
		return 0;
	}
	// Time:(n^2) for validate the winning
	private boolean hasWon(int row, int col, int n, char c) {
		// check horizontal
		boolean rowLine = true;
		for(int i = 0; i < n; i++) {
			rowLine = rowLine && (board[i][col] == c);
		}
		// check vertical
		boolean colLine = true;
		for(int j = 0; j < n; j++) {
			colLine = colLine && (board[row][j] == c);
		}
		// check diagonal if necessary (for reverse diagonal Line and diagonal Line) 
		if(row + col == n - 1 || col == row) {
			boolean diagLine = true;
			boolean revDiagLine = true;
			for(int j = 0; j < n; j++) {
				diagLine = diagLine && (board[j][j] == c);
			}
			for(int j = 0; j < n; j++) {
				revDiagLine = revDiagLine && (board[n-1-j][j] == c);
			}
			return rowLine || colLine || diagLine || revDiagLine;
		} else {
			return rowLine || colLine;
		}
	}
	
	
	public static void main(String[] args) {
		char[][] board = new char[1][3];
		
	}
}

// Optimized for Time complexity and Space complexity
// O(1) Time, O(n) Space solution
class TicTacToeOptimize{
	private int[] rows, cols;
	private int diagonal, revDiagonal;
	public TicTacToeOptimize(int n) {
		this.rows = new int[n];
		this.cols = new int[n];
	}
	// Method 1:
	public int move(int row, int col, int player) {
		int add = player == 1 ? 1 : -1;
		rows[row] += add;
		cols[row] += add;
		if(row == col) {
			diagonal += add;
		}
		if(col + row == cols.length - 1) {
			revDiagonal += add;
		}
		int size = rows.length;
		if(Math.abs(rows[row]) == size ||
				Math.abs(cols[col]) == size ||
				Math.abs(diagonal) == size || 
				Math.abs(revDiagonal) == size) {
			return player;
		}
		return 0;
	}
	
	// Method 2: simply way
	int d1, d2, n;
	public int moveII(int row, int col, int player) {
		int val = (player == 1) ? 1 : -1;
		int target = (player == 1) ? n : -n;
		if(row == col) {
			d1 += val;
			if(d1 == target) return player;
		}
		if(row + col + 1 == n) {
			d2 += val;
			if(d2 == target) return player;
		}
		rows[row] += val;
		cols[col] += val;
		if(rows[row] == target || cols[col] == target) return player;
		return 0;
	}
}
