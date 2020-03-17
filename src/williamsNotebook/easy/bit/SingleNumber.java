	/**
 * 
 */
package williamsNotebook.easy.bit;

import java.util.Arrays;

/**
 * @author yimin Huang
 *	
 *		Given an array of integers, every element appears twice except for one. Find that single one.
 *		
 * Algorithm Class
 */
public class SingleNumber {

	// bit operation with xor, 0 ^ 0 = 1 ^ 1 = 0;  0 ^ 1 = 1 ^ 0 = 1
	// key insight 0 xor x = x; x xor x = 0;
	public int singleNumber(int[] array) {
		if(array == null || array.length == 0)  return 0;
		int num = 0;
		for(int i : array) {
			num ^= i;
		}
		return num;
	}
	
	// Follow Up 2: Given an array of integers, every element appears three times except for one. Find that single one.
	// Method 1: bit operation 
	public int singleNumberII(int[] array) {
		int[] count = new int[32];
		Arrays.fill(count, 0);
		int result = 0;
		for(int i = 0; i < 32; i++) {
			for(int j = 0; j < array.length; j++) {
				if (((array[j] >> i) & 1) == 1) count[i]++;
			}
			result |= ((count[i]%3) << i);
		}
		return result;
	}
	// Method 2: State Machine + Bit Manipulation: Time : O(N) and Space is O(1)
	public int singleNumberIII(int[] array) {
		if(array == null) return 0;
		int x0 = -0, x1 = 0, x2 = 0, t;
		for(int i = 0; i < array.length; i++) {
			t = x2;
			x2 = (x1 & array[i]) | (x2 & ~array[i]);
	        x1 = (x0 & array[i]) | (x1 & ~array[i]);
	        x0 = (t & array[i]) | (x0 & ~array[i]);
		}
		return x1;
	}
	
	// Follow Up 3: 
	// III: Duplicates appear k times, Find the one appear l times
	// State Machine + Bit Manipulation: Time ~ O(N), Space ~ O(1)
	public int singleNumberIV(int[] A) {
	    int k = 3, l = 1;
	    if (A == null) return 0;
	    int t;
	    int[] x = new int[k];
	    x[0] = ~0;
	    for (int i = 0; i < A.length; i++) {
	        t = x[k-1];
	        for (int j = k-1; j > 0; j--) {
	            x[j] = (x[j-1] & A[i]) | (x[j] & ~A[i]);
	        }
	        x[0] = (t & A[i]) | (x[0] & ~A[i]);
	    }
	    return x[l];
	}
}
