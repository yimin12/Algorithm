/**
 * 
 */
package williamsNotebook.easy.array;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月23日 下午1:35:24
* Description:
* 	Given a matrix of M x N elements (M rows, N columns), return all elements of 
* 	the matrix in diagonal order as shown in the below image.
* Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
  Output:  [1,2,4,7,5,3,6,8,9]
*/

public class DiagonalTraverse {

	// Method 1: Intuitive Approach
	public int[] diagonalTraversal(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return new int[0];
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int[] res = new int[m*n];
		int[] di = new int[] {-1,1};
		int[] dj = new int[] {1,-1};
		int dir = 0;
		int i = 0, j = 0;
		for(int k = 0; k < m*n; k++) {
			res[k] = matrix[i][j];
			i += di[dir];
			j += dj[dir];
			if(i >= m) {
				i = m - 1;
				j = j + 2;
				dir = 1 - dir;
			}
			if(j >= n) {
				 j = n - 1; 
				 i = i + 2;
				 dir = 1 - dir;
			}
			if(i < 0) {
				i = 0;
				dir = 1 - dir;
			}
			if(j < 0) {
				j = 0; 
				dir = 1 - dir;
			}
		}
		return res;
	}
	// Method 2: 
	public int[] findDiagonalOrder(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return new int[0];
		}
		int m = matrix.length;
        int n = matrix[0].length;
        int[] ans = new int[m * n];
        int UP = 0, DOWN = 1;
        int dir = UP;
        int i = 0, j = 0;
        for (int k = 0; k < m * n; k++) {
            ans[k] = matrix[i][j];
            if (dir == UP) {
                // Reaching upper bound
                if (i == 0) {
                    if (j + 1 == n) {
                        i++;
                    } else {
                        j++;
                    }
                    dir = DOWN;
                } else if (j + 1 == n) {
                    i++;
                    dir = DOWN;
                } else {
                    i--;
                    j++;
                }
            } else {
                if (j == 0) {
                    if (i + 1 == m) {
                        j++;
                    } else {
                        i++;
                    }
                    dir = UP;
                } else if (i + 1 == m) {
                    j++;
                    dir = UP;
                } else {
                    i++;
                    j--;
                }
            }
        }
        return ans;
    }
}
