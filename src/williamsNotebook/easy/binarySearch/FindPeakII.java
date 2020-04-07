/**
 * 
 */
package williamsNotebook.easy.binarySearch;

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
}
