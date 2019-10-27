/**
 * 
 */
package BinarySearch;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月25日 上午9:16:23
* Description:
* 	Given two sorted arrays of integers, find the Kth smallest number.
* 	Theoretically, it could be used in case of find the kth largest(smallest) in k sorted arrays
* Assumption:
* 	The two given arrays are not null and at least one of them is not empty
* 	K >= 1, K <= total lengths of the two sorted arrays
* Examples:
* 	A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.
* 	A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2.
*/

public class KthSmallestTwoSortedArray {
	public int kth(int[] a, int[] b, int k) {
//		Assumption: k >=1, a, b is not null and it have at least one of them is not empty
		return kth(a, 0, b, 0, k);
	}
//	in the sub-array of a starting from index aleft, and sub-array of b starting from index bleft, fint the kth smallest
//	element among these two sub-arrays
	private int kth(int[] a, int aleft, int[] b, int bleft, int k) {
//		Three base case:
//		1.we already eliminate all the elements in a 
//		2.we already eliminate all the elements in b
//		3.when k is reduce to 1, do not miss this base case, the reason why we have this base case is in the following
//		logic we need k >= 2 to make it work
		if(aleft >= a.length) {
			return b[bleft+k-1];
		}
		if(bleft >= b.length) {
			return a[aleft+k-1];
		}
		if(k==1) {
			return Math.min(a[aleft], b[bleft]);
		}
//		we compare the k/2 th element in a's sub-array and the k/2 th in b's sub-array
//		to determine which k/2 partition can be surely included in smallest k elements
		int amid = aleft + k/2 - 1;
		int bmid = bleft + k/2 - 1;
		int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
		int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
		if(aval <= bval) {
			return kth(a, amid+1, b, bleft, k - k/2);
		} else {
			return kth(a, aleft, b, bmid+1, k - k/2);
		}
	}
}
