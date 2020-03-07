package williamsNotebook.easy.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yimin Huang
 *	
 *	Given two sparse matrices A and B, return result of AB, you may assume that A's column number is equal to B's row number
 *	A = {{1,0,0},      B = {{7,0,0},      AB = {{7,0,0}  		rules: C[i][j] = sum(A[i][k] * B[k][j]) where k = Column of A or Row of B
 *		 {-1,0,3}}			{0,0,0},			{-7,0,3}}
 *							{0,0,1}};
 *	Algorithm Class
 */
public class SparseMatrixMultiplication {
	
	// direct way: naive solution, rules: C[i][j] = sum(A[i][k] * B[k][j]) where k = Column of A or Row of B
	// Solution 1: Time: O(m * n ^ n) and O(m * n) extra space
	public int[][] multiply(int[][] A, int[][] B){
		int rowA = A.length;
		int n = A[0].length;
		int colB = B[0].length;
		int[][] res = new int[rowA][colB];
		for(int i = 0; i < rowA; i++) {
			for(int j = 0; j < colB; j++) {
				for(int k = 0; k < n; k++) {
					res[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		return res;
	}
	// Solution 2: pruning when you do the while loop
	// Time: O(m * n ^ n) and O(m * n) extra space but optimized
	public int[][] multiplyPrun(int[][] A, int[][] B){
		int rowA = A.length;
		int n = A[0].length;
		int colB = B[0].length;
		int[][] res = new int[rowA][colB];
		for(int i = 0; i < rowA; i++) {
			for(int k = 0; k < n; k++) {
				if(A[i][k] != 0) {
					for(int j = 0; j < colB; j++) {
						if(B[k][j] != 0) {
							res[i][j] += A[i][k] * B[k][j];
						}
					}
				}
			}
		}
		return res;
	}
	// Solution 3: List representation, a sparse matrix can represent by List with pair of index and value
	public int[][] multiplyListRep(int[][] A, int[][] B){
		int rowA = A.length;
		int n = A[0].length;
		int colB = B[0].length;
		int[][] res = new int[rowA][colB];
		// convert sparse matrix to List
		List[] indexA = new List[rowA];
		// choose the sparse matrix to convert
		for(int i = 0; i < rowA; i++) {
			List<Integer> noneZeroA = new ArrayList<Integer>();
			for(int j = 0; j < n; j++) {
				if(A[i][j] != 0) {
					noneZeroA.add(j);
					noneZeroA.add(A[i][j]);
				}
			}
			indexA[i] = noneZeroA;
		}
		for(int i = 0; i < rowA; i++) {
			List<Integer> noneZeroA = indexA[i];
			for(int p = 0; p < noneZeroA.size() - 1; p += 2) {
				int colA = noneZeroA.get(p);
				int valA = noneZeroA.get(p+1);
				for(int j = 0; j < colB; j++) {
					int valB = B[colA][j];
					res[i][j] += valA * valB;
				}
			}
		}
		return res;
	}
	public static void main(String[] args) {
		SparseMatrixMultiplication solution = new SparseMatrixMultiplication();
		int[][] A = new int[][] {{1,0,0},{-1,0,3}};
		int[][] B = new int[][] {{7,0,0},{0,0,0},{0,0,1}};
		int[][] multiply = solution.multiplyListRep(A, B);
		System.out.println(Arrays.deepToString(multiply));
	}
}
