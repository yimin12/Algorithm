/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.ArrayList;
import java.util.List;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��16�� ����7:17:37
* Description: 
* 	Compare to the SpiralTraverseI, the matrix is not square. 
* 	It has size of M * N, where M and N >= 0
*/

public class SpiralTraverseII {
	public List<Integer> spiral(int[][] matrix){
//		Assumption: matrix is not null, has size of N * M, where M, N >= 0;
		List<Integer> list = new ArrayList<Integer>();
		int m = matrix.length;
//		need to handle this case since if m==0, matrix[0].length will throw ArrayIndexOutofBoundException
		if(m == 0) {
			return list;
		}
		int n = matrix[0].length;
		int left = 0;
		int right = n - 1;
		int up = 0;
		int down = m - 1;
		while(left < right && up < down) {
			for(int i = left; i < right; i++) {
				list.add(matrix[up][i]);
			}
			for(int i = up; i < down; i++) {
				list.add(matrix[i][right]);
			}
			for(int i = right; i > left; i--) {
				list.add(matrix[down][i]);
			}
			for(int i = down; i > up; i--) {
				list.add(matrix[i][left]);
			}
			left++;
			up++;
			right--;
			down--;
		}
//		1.if there is nothing left.
		if(left > right || up > down) {
			return list;
		}
//		2. if there is one column left.
		if(left == right) {
			for(int i=up;i<=down;i++) {
				list.add(matrix[i][left]);
			}
		} else {
//			3. if there is one row here
			for(int i = left; i <= right; i++) {
				list.add(matrix[up][i]);
			}
		}
		return list;
	}
	public static void main(String[] args) {
		int[][] test = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
		for(int[] r:test) {
			for(int c:r) {
				System.out.print(c+" ");
			}
		}
		SpiralTraverseII s = new SpiralTraverseII();
		List<Integer> list = s.spiral(test);
		System.out.print(list.toString());
	}
}
