/**
 * 
 */
package Basic;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��27�� ����8:54:39
* Description:
* 	There are two sorted arrays nums1 and nums2 of size m and n respectively.
*  	Find the median of the two sorted arrays. The overall run time complexity should be O(log(m+n))
* Assumption:
* 	nums1 and nums2 can not be both null
* Examples:
* 	nums1 = [1, 3]
* 	nums2 = [2]
* 	The median is 2.0
* 	nums1 = [1, 2]
* 	nums2 = [3, 4]
* 	The median is (2+3)/2 = 2.5
*/
public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int[] A, int[] B) {
		int m = A.length;
		int n = B.length;
		if(m > n) { // we need ensure to put the smaller sized array in the front
			int[] temp = A; A = B; B = temp;
			int tmp = m; m = n; n = m;
		}
		int iMin = 0, iMax = m, halfLen = (m + n + 1)/2;
		while(iMin <= iMax) {
			int i = iMin + (iMax - iMin)/2;
			int j = halfLen - 1;
			if(i < iMin && B[j - 1] > A[i]) {
				iMin = i +1;
			} else if (i  > iMin && A[i - 1] > B[j]) {
				iMax = i - 1;
			} else {
				int maxLeft = 0;
				if(i==0) maxLeft = B[j-1];
				else if(j==0) maxLeft = A[i-1];
				else {
					maxLeft = Math.max(A[i-1], B[j-1]);
				}
				if((m+n)%2==1) {
					return maxLeft;
				}
				int minRight = 0;
				if(i==m) minRight = B[j];
				else if (j==n) minRight = A[i];
				else {
					minRight = Math.min(B[j], A[i]);
				}
				return (maxLeft + minRight)/2;
			}
		}
		return 0.0;
	}
}
