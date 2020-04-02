/**
 * 
 */
package williamsNotebook.easy.backTracking;

import java.util.Arrays;

/**
 * @author yimin Huang
 *		
 *	Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 *	Rules:	
 *		Each row must have the numbers 1-9 occuring just once.
 *		Each column must have the numbers 1-9 occuring just once.
 *		And the numbers 1-9 must occur just once in each of the 9 sub-boxes of the grid
 *		The Sudoku board could be partially filled, where empty cells are filled with the character '.'
 *	Notes:
 *		A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated	
 * Algorithm Class
 */
public class Sudoku {

	// Solution 1: scan three times, rows, cols and sub boxes, pure check not any dfs or dp
	// Time ~ O(3N^2), Space ~ O(N)
	public boolean isValidSudoku(char[][] board) {
		// use a boolean array to record the encountered number
		boolean[] visited = new boolean[9];	
		// check 9 rows
		for(int i = 0; i < 9; i++) {
			Arrays.fill(visited, false);
			for(int j = 0; j < 9; j++) {
				if(!isValid(i, j, board, visited)) return false;
			}
		}
		// check 9 cols
		for(int i = 0; i < 9; i++) {
			Arrays.fill(visited, false);
			for(int j = 0; j < 9; j++) {
				if(!isValid(i, j, board, visited)) return false;
			}
		}
		// check 9 sub-boxes
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				Arrays.fill(visited, false);
				for(int k = 0; k < 9; k++) {
					// boxes[i * 3 + k/3,j * 3 + k % 3] is a good way to check sub box
					if(!isValid(i * 3 + k/3, j * 3 + k % 3, board, visited)) return false;
				}
			}
		}
		return true;
	}
	private boolean isValid(int x, int y, char[][] board, boolean[] visited) {
		if(board[x][y] != '.') {
			if(visited[board[x][y] - '1']) return false;
			else visited[board[x][y] - '1'] = true;
		}
		return true;
	}
	// Solution 2: use extra space to save time complexity, scan one time
	// Time ~ O(N^2), Space ~ O(3N^2)
	public boolean isValidSudokuII(char[][] board) {
		boolean[][] rows = new boolean[9][9];
		boolean[][] cols = new boolean[9][9];
		boolean[][] boxes = new boolean[9][9];
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				char cell = board[i][j];
				if(cell != '.') {
					cell -= '1';
					// case 1. check rows
					if(rows[i][cell]) return false;
					else rows[i][cell] = true;
					// case 2. check cols
					if(cols[cell][j]) return false;
					else cols[cell][j] = true;
					// case 3. check boxes
					if(boxes[i/3 * 3 + j/3][cell]) return false;
					else boxes[i/3 * 3 + j/3][cell] = true;
				}
			}
		}
		return true;
	}
	
	// Follow Up 2: Write a program to solve Sudoku puzzle by filling the empty cells. Empty cells are indicate by the character '.'
	// 		you may assume that there will be only one unique solution, assume that m cells need to be filled
	// DFS : Time ~ O(9^(m+2)), Space ~ O(1)
	// Fill the '.' with all choice from '1' to '9'
	public void solveSudoku(char[][] board) {
		if(board == null || board.length == 0 || board[0].length == 0) throw new IllegalArgumentException("input is invalid!");
		if(!isSolvable(board)) {
			throw new IllegalArgumentException("Not Solvable");
		}
	}
	// DFS without any pruning
	private boolean isSolvable(char[][] board) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(board[i][j] == '.') {
					// when you find a cell to fill, try all the possible values
					for(int k = 0; k < 9; k++) {
						board[i][j] = (char)(k + '1');
						if(isValidHelper(i, j, board) && isSolvable(board)) return true;
						// dealing the back tracking problem
						board[i][j] = '.';
					}
					// after trying the 9 number and still have can not solve this cell, it is not solvable.
					return false;
				}
			}
		}
		// after fill all the cells, it is solvable.
		return true;
	}
	private boolean isValidHelper(int i, int j, char[][] board) {
		// check ith rows
		for(int c = 0; c < 9; c++) {
			if(c != j && board[i][c] == board[i][j]) return false;
		}
		// check jth cols
		for(int c = 0; c < 9; c++) {
			if(c != i && board[c][j] == board[i][j]) return false;
		}
		// check (i,j)'s sub-boxes
		for(int k = 0; k < 9; k++) {
			// i / 3 * 3 dealing the precision lost of integer
			int row = i / 3 * 3 + k / 3, col = j / 3 * 3 + k % 3;
			if(row != i && col != j && board[row][col] == board[i][j]) return false;
		}
		// after checking all the situation
		return true;
	}
	
}
