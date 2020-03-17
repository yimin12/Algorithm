/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;

/**
 * @author yimin Huang
 *		
 *		Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 *
 * Algorithm Class
 */
public class MaximumMatrix {
	
	// Situation 1: Given a matrix every element is binary (either '0' or '1', find the largest sub square surround by '1')
	// Time: O(n*m*min(n,m)) worst case and Extra space : O(m*n) where m is the rows and n is the cols
	public int largestSubSquare(int[][] matrix) {
		// Assumption:
		if(matrix.length == 0 || matrix[0].length == 0) return 0;
		int result = 0;
		int M = matrix.length;
		int N = matrix[0].length;
		int[][] left = new int[M+1][N+1];
		int[][] up = new int[M+1][N+1];
		for(int i = 0; i < M;i++) {
			for(int j = 0; j < N; j++) {
				if(matrix[i][j] == 1) {
					left[i+1][j+1] = left[i+1][j] + 1;
					up[i+1][j+1] = up[i][j+1] + 1;
					// the maximum length of a surround by 1 matrix with rightbottom position at matrix[i][j] is determined by the min value
					// of left[i+1][j+1] and up[i+1][j+1], and we can check one by one by all possible lengths if it can provide the actual matrix
					for(int length = Math.min(left[i+1][j+1], up[i+i][j+1]); length > 0; length--) {
						// check whether the corresponding point is valid
						if(left[i + 2 - length][j+1] >= length && up[i+1][j+2-length] >= length) {
							result = Math.max(result, length);
							break;
						}
					}
				}
			}
		}
		return result;
	}
	
	// Follow Up 2: Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area
	// Two pointers: Time ~ O(MN)
	public int maximalRectangle(int[][] matrix) {
		// Assumption:
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return -1;
		int M = matrix.length, N = matrix[0].length;
		int[] Histogram = new int[N];
		int[] Left = new int[N];
		int[] Right = new int[N];
		Arrays.fill(Right, N);
		int result = 0;
		for(int i = 0; i < M; i++) {
			int left = 0, right = N;
			for(int j = 0; j < N; j++) {
				if(matrix[i][j] == 1) {
					Histogram[j]++;
					Left[j] = Math.max(Left[j], left);
				} else {
					Histogram[j] = 0;
					left = j + 1;
					Left[j] = 0;
					Right[j] = N;
				}
			}
//			System.out.println(Arrays.toString(Histogram));
//			System.out.println("                Left Array: " + Arrays.toString(Left));
			for(int j = N - 1; j >= 0; j--) {
				if(matrix[i][j] == 1) {
					Right[j] = Math.min(Right[j], right);
					result = Math.max(result, Histogram[j] * (Right[j] - Left[j]));
				} else {
					right = j;
				}
			}
			System.out.println("Right Array: " + Arrays.toString(Right));
		}
		return result;
	}
	
	// Follow Up 3: Largest Square
	// Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
	// For example, given the following matrix
	// 1. 2-d DP: Time ~ O(MN), Space ~ O(MN)
	public int maximalSquare(int[][] board) {
		// Assumptions:
		if(board == null || board.length == 0 || board[0].length == 0) {
			return 0;
		}
		int m = board.length, n = board[0].length;
		int[][] dp = new int[m][n];
		int max = 0;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j] == 0) dp[i][j] = 0;
				else {
					if(i == 0 || j == 0) dp[i][j] = 1;
					else {
						dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
					}
				}
				max = Math.max(max, dp[i][j]);
			}
		}
		return max*max;
	}
	
	
	public static void main(String[] args) {
		MaximumMatrix solution = new MaximumMatrix();
		int[][] matrix = new int[][] {{0,1,1,1,1},{1,1,1,1,0},{1,1,1,1,1},{0,1,0,1,0},{1,1,1,1,1}};
		int maximalRectangle = solution.maximalRectangle(matrix);
		System.out.println(maximalRectangle);
	}
}
