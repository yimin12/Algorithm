/**
 * 
 */
package williamsNotebook.easy.binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 下午11:58:04
* Description:
* 	There is an integer matrix which has the following features:
* 	The numbers in adjacent positions are different. The matrix has n rows and m columns. 
* 		For all i < m, A[0][i] < A[1][i] && A[n – 2][i] > A[n – 1][i].
* 		For all j < n, A[j][0] < A[j][1] && A[j][m – 2] > A[j][m – 1].
* 	We define a position P is a peek if:
* 		A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]
* 		Find a peak element in this matrix. Return the index of the peak.
* Example:
* 	[
		[1 ,2 ,3 ,6 ,5],
		[16,41,23,22,6],
		[15,17,24,21,7],
		[14,18,19,20,10],
		[13,14,11,10,9]
	]
*/
public class FindPeakII {

	// Analysis:	因为21是第三行的最大值, 所以我们如果找个一个比21大的, 就是第二行第四列的22. 我们发现, 如果从21走到22, 
	//   	在从22找比它大的往高爬, 永远都不会爬回21所在的第三行, 所以我成功甩掉了第四五行…下面我们来确定在剩下的一半里面, 
	//		一定有我们要找的答案:
	public List<Integer> findPeak(int[][] A) {
		if(A == null || A.length == 0) {
			return new ArrayList<Integer>();
		}
		int m = A.length, n = A[0].length;
		return find(1, m-2, 1, n-2, A, true);
	}
	// 	Binary Search 的策略是先沿着x轴对半折，然后再沿着y轴对半折，往复循环找到峰值。
	private List<Integer> find(int x1, int x2, int y1, int y2, int[][] A, boolean flag){
		if(flag) { // binary search for x
			int mid = x1 + (x2-x1)/2;
			int index = y1; // record the index of maximum value in mid row
			for(int i = y1; i < y2; i++) {
				if(A[mid][i] > A[mid][index]) {
					index = i;
				}
			}
			if(A[mid-1][index] > A[mid][index]) {
				return find(x1, mid-1, y1, y2, A, !flag);
			} else if(A[mid+1][index] > A[mid][index]) {
				return find(mid+1, x2, y1, y2, A, !flag);
			} else {
				// A[mid-1][index] < A[mid][index] > A[mid+1][index]
				return new ArrayList<Integer>(Arrays.asList(mid, index));
			}
		} else { // binary search for y
			int mid = y1 + (y2-y1)/2;
			int index = x1; // record the index of maximum value int mid col
			for(int i = x1; i < x2; i++) {
				if(A[i][mid] > A[i][index]) {
					index = i;
				}
			}
			if(A[index][mid-1] > A[index][mid]) {
				return find(x1, x2, y1, mid-1, A, !flag);
			} else if(A[index][mid+1] > A[index][mid]) {
				return find(x1, x2, mid+1, y2, A, !flag);
			} else {
				return new ArrayList<Integer>(Arrays.asList(index, mid));
			}
		}
	}
	public static void main(String[] args) {
		FindPeakII solution = new FindPeakII();
		int[][] matrix = {{1 ,2 ,3 ,6 ,5},{16,41,23,22,6},{15,17,24,21,7},{14,18,19,20,10},{13,14,11,10,9}};
		List<Integer> findPeak = solution.findPeak(matrix);
		System.out.println(findPeak.toString());
	}
}
