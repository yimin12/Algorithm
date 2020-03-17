/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;

/**
 * @author yimin Huang
 *
 *		Rotate an array of n elements to the right by k steps.
 *		For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 *		Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 *	
 * Algorithm Class
 */
public class RotateArray {

	// Juggling algorithm: Time: O(n), Space: O(1)
	public void rotateJuggling(int[] nums, int k) {
		int n = nums.length;
		k %= n;
		if(k < n - k) rotateRight(nums, k);
		else rotateLeft(nums, n - k);
	}
	private void rotateRight(int[] nums, int k) {
		int n = nums.length;
		for(int i = 0; i < gcd(n, k); i++) {
			int last = nums[n - 1 - i];
			int j = n - 1 - i;
			while(true) {
				int l = j - k;
				if(l < 0) l += n;
				if(l == n - 1 - i) break;
				nums[j] = nums[l];
				j = l;
			}
			nums[j] = last;
		}
	}
	public void rotateLeft(int[] nums, int k) {
	    int n = nums.length;
	    for (int i = 0; i < gcd(n, k); i++) {
	        int first = nums[i];
	        int j = i;
	        while (true) {
	            int l = j + k;
	            if (l >= n) l -= n;
	            if (l == i) break;
	            nums[j] = nums[l];
	            j = l;
	        }
	        nums[j] = first;
	    }
	}
	// greatest common divisor
	private int gcd(int n, int k) {
		if(k == 0) return n;
		else return gcd(k, n%k);
	}
	// Method2 : Block-Swap algorithm
	// AB -> BA, assume A is shorter, divide the B into Bl Br (Br.len = A.len);
	// A Bl Br -> divide B and swap(A, Br): Br Bl A -> divide Br and swap... -> Bl Br A
	// Time ~ O(N), Space ~ O(1)
	public void rotateBlockSwap(int[] nums, int k) {
		k = nums.length - k % nums.length;
	    if (k == 0 || k == nums.length)   return;
	    
	    // move to the Left
	    int i = k, j = nums.length - k; // A (len = i), B (len = j) 
	    while (i != j) {
	        if (i < j) {    // A is shorter
	            swap(nums, k - i, k - i + j, i);    // A,Bl,Br -> Br,Bl,A (A=[k-i..k-1], Br=[k-i+j...k-1+j])
	            j -= i;
	            System.out.println(Arrays.toString(nums));
	        } else {        // B is shorter
	            swap(nums, k - i, k, j);            // Al,Ar,B -> B,Ar,Al (Al=[k-i..k-i+j-1], B=[k..k+j-1])
	            i -= j;
	            System.out.println(Arrays.toString(nums));
	        }
	    }
	    // i == j: A and B are with equal length
	    swap(nums, k - i, k, i);    // A,B -> B,A (A=[k-i..k-1], B=[k..k+i-1])
        System.out.println(Arrays.toString(nums));
	}
	private void swap(int[] nums, int left, int right, int d) {
		for(int i = 0; i < d; i++) {
			int temp = nums[left + i];
			nums[left + i] = nums[right + i];
			nums[right + i] = temp;
		}
	}
	// Method 3: most common way, reversal algorithm
	// Time ~ O(N), Space ~ O(1)
	public void rotate(int[] nums, int k) {
	    k = k % nums.length;
	    // A (len = n-k), B (len = k)
	    // rev(rev(A)rev(B)) = BA
	    reversal(nums, 0, nums.length - k - 1);
	    reversal(nums, nums.length - k, nums.length - 1);
	    reversal(nums, 0, nums.length - 1);
	}

	private void reversal(int[] nums, int start, int end) {
	    while (start < end) {
	        int temp = nums[start];
	        nums[start] = nums[end];
	        nums[end] = temp;
	        start++;
	        end--;
	    }
	}
	public static void main(String[] args) {
		RotateArray solution = new RotateArray();
		int[] array = new int[] {1,2,3,4,5,6,7};
		solution.rotateBlockSwap(array, 2);
	}
}
