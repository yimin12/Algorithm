/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;

/**
 * @author yimin Huang
 *	
 *		Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 *		Try to solve it in linear time/space.
 *		Return 0 if the array contains less than 2 elements.
 *		you may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 *		Input: [3,6,9,1]  Output: 3
 *		Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
 *		Input: [10]
 *		Output: 0	Explanation: The array contains less than 2 elements, therefore return 0.
 *
 * Algorithm Class
 */
public class MaximumGap {

	// Method 1: Bucket Sort
	// 1. Bucket Sort: Time ~ O(2N), Space ~ O(2N)
	public int maximumGapI(int[] array) {
		 // Assumption: the given array is not null
		int n = array.length;
		if(n <= 2) return 0;
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for(int i : array) {
			max = Math.max(max, i);
			min = Math.min(min, i);
		}
		if(max == min) return 0;
		
		// create n - 1 buckets with their own min and max values. there are must be at least one is empty
		int numBucket = n - 1;
		int sizeBucket = (int)Math.ceil((double)(max-min)/numBucket);
		int[] maxBucket = new int[numBucket];
		int[] minBucket = new int[numBucket];
		Arrays.fill(maxBucket, Integer.MIN_VALUE);
		Arrays.fill(minBucket, Integer.MAX_VALUE);
		for(int i = 0; i < n; i++) {
			if(array[i] != max && array[i] != min) {
				int index = (array[i] - min)/sizeBucket;
				maxBucket[index] = Math.max(maxBucket[index], array[i]);
				minBucket[index] = Math.min(minBucket[index], array[i]);
			}
		}
		System.out.println(Arrays.toString(maxBucket));
		System.out.println(Arrays.toString(minBucket));
		// find the max gap
		int result = Integer.MIN_VALUE, prev = min;
		for(int i = 0; i < numBucket; i++) {
			if(maxBucket[i] != Integer.MIN_VALUE && maxBucket[i] != Integer.MAX_VALUE) {
				result = Math.max(result, minBucket[i] - prev);
				prev = maxBucket[i];
			}
		}
		result = Math.max(result, max - prev);
		return result;
	}
	
	// 2. LSD Radix sort: Time ~ O(MN) where M << N (still linear), Space ~ O(N)
	public int maximumGapII(int[] num) {
	    int n = num.length;
	    if (n < 2)  return 0;
	    
	    // find the max
	    int max = Integer.MIN_VALUE;
	    for (int i : num)
	        max = Math.max(max, i);
	    int m = (int) (Math.log10(max) + 1); // number of digits
	    
	    int div = 1; // divider
	    int R = 10;
	    int[] aux = new int[n];
	    for (int d = m - 1; d >= 0; d--) {
	        int[] count = new int[R + 1];
	        // compute frequency of each digit using key as index
	        for (int i = 0; i < n; i++)
	            count[(num[i] / div) % 10 + 1]++;
	        // compute frequency cumulates which specifies the destinations
	        for (int r = 0; r < R; r++)
	            count[r + 1] += count[r];
	        // access cumulates using key as index to move items
	        for (int i = 0; i < n; i++)
	            aux[count[(num[i] / div) % 10]++] = num[i];
	        // copy back into original array
	        for (int i = 0; i < n; i++)
	            num[i] = aux[i];
	        // update divider to move to higher digit
	        div *= 10;
	    }
	    
	    // find the max gap
	    int maxGap = Integer.MIN_VALUE;
	    for (int i = 1; i < n; i++) {
	        maxGap = Math.max(maxGap, num[i] - num[i - 1]);
	    }
	    return maxGap;
	}
	public static void main(String[] args) {
		MaximumGap solution = new MaximumGap();
		int maxmumGap = solution.maximumGapI(new int[] {3,6,9,1});
		System.out.println(maxmumGap);
	}
}
