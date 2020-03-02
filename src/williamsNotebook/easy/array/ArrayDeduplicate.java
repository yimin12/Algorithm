/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;

/**
 * @author yimin Huang
 *
 *		Remove Elements: all instance of a given value, Given an array and a value, remove all instances of that 
 *			value in place and return the new length
 *		
 * Algorithm Class
 */
public class ArrayDeduplicate {

	// Situation 1: The order of elements can be changed, remove specific value, return the array
	// Assumption: the array is not sorted and the searching target has no duplicate
	// Time ~ O(N), Space ~ O(1)
	public int[] removeElement(int[] array, int target) {
		if(array.length == 0) return array;
		// values before start is what we want to keep(not including start), values between start and traversing index is value 
		// that we want to discard, values after traversing index need to be traversed
		int slow = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == target) {
				continue;
			}
			array[slow++] = array[i];
		}
		return Arrays.copyOf(array, array.length - 1); // ignore the extra space usage
	}
	
	// FollowUp 2: Remove Duplicates from Sorted Array I: keep one duplicate, Given a sorted array, remove the duplicates
	// in place such that each element appear only once and return the new length. Do not allocate extra space for another 
	// array, you must do this in place with constant memory.
	// Time ~ O(N), Space ~ O(1)
	public int[] removeDuplicate(int[] array) {
		// Assumption: this is a sorted array
		if(array.length == 0) return array;
		int start = 0;
		for(int end = 1; end < array.length; end++) {
			if(array[start] != array[end]) {
				// the value before start (including index start)
				array[++start] = array[end];
			}
		}
		return Arrays.copyOf(array, start+1);
	}
	
	// FollowUp3: Remove Duplicates from Sorted Array II: keep two duplicates
		/*
		 * For example, Given sorted array A = [1,1,1,2,2,3], Your function should
		 * return length = 5, and A is now [1,1,2,2,3].
		 */
	// 1. Time ~ O(N), Space ~ O(1)
	// Time ~ O(N), Space ~ O(1)
	public int[] removeDuplicateII(int[] array) {
		if(array == null || array.length <= 2) return array;
		int start = 1;
		for(int end = 2; end < array.length; end++) {
			if(array[start-1] != array[end]) {
				// you can fill two position advanced the confirmed value
				array[++start] = array[end];
			}
		}
		return Arrays.copyOf(array, start+1);
	}
	
	// FollowUp 4: sorted arrary, duplicate element not retain any
	// {1, 2, 2, 3, 3, 3} → {1}
	// Time ~ O(N), Space ~ O(1)
	public int[] removeDuplicateIII(int[] array) {
		// The array is sorted 
		if(array == null || array.length < 2) return array;
		int end = 0;
		// use flag to see if there is any duplicates of array[end].
		boolean flag = false;
		for(int i = 1; i < array.length; i++) {
			if(array[i] == array[end]) {
				// if there is duplicate, set the flag and do nothing
				flag = true;
			} else if(flag) {
				// array[i] != array[end], and flag is set, array[end] should not be included in valid subarray,
				// and we can just replace it with array[i] since next we are going to check if there is any duplicate
				// of array[i]
				array[end] = array[i];
				// reset the flag to false since we are processing another elements now
				flag = false;
			} else {
				// if array[i] != array[end] and flag is not set, it means there is no duplicate of array[end] and it should 
				// included in the valid subarray.
				array[++end] = array[i];
			}
		}
		// do not forget that we need to check if there is any duplicates for the last array[end]
		return Arrays.copyOf(array, flag ? end : end + 1);
	}
	
	// FollowUp 5: unsorted array, repeatedly de-duplication)
	// {1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
	// Time ~ O(N), Space ~ O(1)
	public int[] removeDuplicateIV(int[] array) {
		int end = -1;
		for(int i = 0; i < array.length; i++) {
			if(end == - 1 || array[end] != array[i]) {
				// use the left part of the original array as a stack. and the top element's index is end. If the stack
				// is empty (end == -1), we just  push the element into the stack, of if the element is not the same as 
				// the top element of the stack, we can push the element into the stack as well.
				array[++end] = array[i];
			} else {
				while(i + 1 < array.length && array[i + 1] == array[end]) {
					// ignore all consecutive duplicates and remove the top element of the stack
					i++;
				}
				end--;
			}
		}
		return Arrays.copyOf(array, end + 1);
	}
	
	
	public static void main(String[] args) {
		ArrayDeduplicate solution = new ArrayDeduplicate();
		int[] removeElement = solution.removeDuplicateIV(new int[] {1,2,2,2,3,3,3,8,8});
		for(int i : removeElement) {
			System.out.print(i + " ");
		}
	}
	
	
	
}
