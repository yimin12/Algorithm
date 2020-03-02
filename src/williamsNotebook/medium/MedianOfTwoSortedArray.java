/**
 * 
 */
package williamsNotebook.medium;

/**
 * @author yimin Huang
 *		here are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. 
 *		The overall run time complexity should be O(log (m+n)). m, n respect to length of each array
 *		
 * Algorithm Class
 */
public class MedianOfTwoSortedArray {
	// if (m+n) is even, the median might contain x.5 in the result
	// Method 1: Binary Search with two sorted array
	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length, n = B.length;
		int mid = (n + m) / 2;
		int left = Math.max(0, mid - n), right = Math.min(m - 1, mid);
		boolean isEven = ((m+n) & 1) == 0;
		while(left <= right) {
			int i = (left + right) / 2, j = mid - 1 - i;
			if(j >= 0 && A[i] < B[j]) left = i + 1;
			else if(j < n - 1 && A[i] > B[j+1]) right = i - 1;
			else {
				if(!isEven) return A[i]; // m+n is odd, return A[i]
				else if(j < 0 || j >= n) return (A[i] + A[i - 1])/2.0;
				else if(i == 0) return (A[i] + B[j])/2.0;
				else return (A[i] + Math.max(B[j], A[i - 1]))/2.0;
			}
		}
		return findMedianSortedArrays(B, A);
	}
	
	// Method 2: Binary Reduction with recursion and assume array A and array B is not null
	// It can be used in find the kth max of min in two sorted array. The Time : O(log(m+n)), Space: O(1);
	public double findMedianRecursion(int[] A, int[] B) {
		int m = A.length, n = B.length;
		int mid = (m + n)/2;
		boolean isEven = ((m+n)&1) == 0;
		if(isEven) {
			return (kth(A, 0, B, 0, mid) + kth(A, 0, B, 0, mid + 1))/2.0;
		} else {
			return kth(A, 0, B, 0, mid);
		}
	}
	
	private int kth(int[] A, int aleft, int[] B, int bleft, int k) {
		// base case
		if(aleft >= A.length) {
			return A[bleft + k - 1];
		}
		if(bleft >= B.length) {
			return B[aleft + k - 1];
		}
		if(k == 1) return Math.min(A[aleft], B[bleft]);
		// recursion rule
		int amid = aleft + k/2 - 1;
		int bmid = bleft + k/2 - 1;
		// if A.size too small, then remove elements from b first
		int aval = amid >= A.length ? Integer.MAX_VALUE : A[amid];
		int bval = bmid >= B.length ? Integer.MAX_VALUE : B[bmid];
		if(aval < bval) {
			return kth(A, amid + 1, B, bleft, k - k/2);
		} else {
			return kth(A, aleft, B, bmid + 1, k - k/2);
		}
	}
	
	public static void main(String[] args) {
		MedianOfTwoSortedArray solution = new MedianOfTwoSortedArray();
		int[] A = new int[] {1,3,5,7,9};
		int[] B = new int[] {2,4,6,8,10};
		double findMedianSortedArrays = solution.findMedianRecursion(A, B);
		System.out.println(findMedianSortedArrays);
	}
}
