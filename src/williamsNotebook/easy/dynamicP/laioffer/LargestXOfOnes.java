/**
 * 
 */
package williamsNotebook.easy.dynamicP.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��20�� ����6:09:47
* Description:
* 	Given a matrix that contains only 1s and 0s, find the largest X shape 
* 	which contains only 1s, with the same arm lengths and the four arms joining at 
* 	the central point.
* 	Return the arm length of the largest X shape
* Assumption: 
* 	The given matrix is not null, has size of N * M, N >= 0 and M >= 0;
* Examples;
*	 { {0, 0, 0, 0}, the largest X of 1s has arm length 2
	  {1, 1, 1, 1},
	  {0, 1, 1, 1},
	  {1, 0, 1, 1} }
*/
public class LargestXOfOnes {
	public int largest(int[][] matrix) {
		int N = matrix.length;
		if(N == 0) return 0;
		int M = matrix[0].length;
		if(M == 0) return 0;
		int[][] leftUp = leftUp(matrix, N, M);
		int[][] rightDown = rightDown(matrix, N, M);
		return merge(leftUp, rightDown, N, M);
	}
	private int merge(int[][] leftUp, int[][] rightDown, int N, int M) {
		int result = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				leftUp[i][j] = Math.min(leftUp[i][j], rightDown[i][j]);
				result = Math.min(result, leftUp[i][j]);
			}
		}
		return result;
	}
	private int[][] leftUp(int[][] matrix, int N, int M){
		int[][] left = new int[N][M];
		int[][] up = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] == 1) {
					left[i][j] = getNumber(left, i - 1, j - 1, N, M) +1;
					up[i][j] = getNumber(up, i - 1, j - 1, N, M) + 1;
				}
			}
		}
		merge(left, up, N, M);
		return left;
	}
	private int[][] rightDown(int[][] matrix, int N, int M){
		int[][] right = new int[N][M];
		int[][] down = new int[N][M];
		for(int i = N - 1; i >= 0; i--) {
			for(int j = M - 1; j >= 0; j--) {
				if(matrix[i][j] == 1) {
					right[i][j] = getNumber(right, i + 1, j, N, M);
					down[i][j] = getNumber(down, i, j + 1, N, M);
				}
			}
		}
		merge(right, down, N, M);
		return right;
	}
	private int getNumber(int[][] number, int x, int y, int N, int M) {
		if(x < 0 || x >= N || y < 0 || y >= M) {
			return 0;
		}
		return number[x][y];
	}
}
