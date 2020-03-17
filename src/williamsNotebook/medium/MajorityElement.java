/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yimin Huang
 *
 *	Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *	You may assume that the array is non-empty and the majority element always exist in the array
 *
 * Algorithm Class
 */
public class MajorityElement {

	// Moore's voting algorithm: Time ~ O(N), Space ~ O(1)
	// More than ⌊ n/2 ⌋ times
	public int majorityElement(int[] array) {
		int major = array[0], count = 1;
		for(int i = 1; i < array.length; i++) {
			if(count == 0) {
				major = array[i];
				count = 1;
			} else if(major == array[i]) {
				count++;
			} else {
				count--;
			}
		}
		return major;
	}		
	
	// Follow Up II. More than ⌊ n/3 ⌋ times
	// Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
	public List<Integer> majorityElementII(int[] array){
		// Assumption : the given array is not null
		List<Integer> list = new ArrayList<Integer>();
		int n = array.length;
		if(n == 0) return list;
		int num1 = 0, num2 = 0;
		int count1 = 0, count2 = 0;
		for(int i = 0; i < n; i++) {
			int val = array[i];
			if(count1 == 0 && num2 != val) {
				num1 = val;
				count1 = 1;
			} else if(count2 == 0) {
				num2 = val;
				count2 = 1;
			} else if(num1 == val) {
				count1++;
			} else if(num2 == val) {
				count2++;
			} else {
				count1--;
				count2--;
			}
		}
		if(isMajority(num1,array)) list.add(num1);
		if(num1 != num2 && isMajority(num2, array))	list.add(num2);
		return list;
	}
	private boolean isMajority(int num, int[] array) {
		int n = array.length;
		int count = 0;
		for(int i = 0; i < n; i++) {
			if(array[i] == num) count++;
			
		}
		if(count > n / 3) return true;
		else return false;
	}
}
