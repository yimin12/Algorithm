/**
 * 
 */
package williamsNotebook.easy.sortingAlgorithm;

import java.util.Arrays;

import williamsNotebook.common.SWAP;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月9日 下午4:38:50
* Description:
* 	Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]…
* Example:
* 	Please complete the problem in-place.
* 	Given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
*/
public class WiggleSort {

	// Do it inplace
	// Time:(n) and SPace: O(n)
	public void WiggleSort(int[] nums) {
		for(int i = 1; i < nums.length; i++) {
			if((i%2 == 1 && nums[i] < nums[i-1]) || (i%2 == 0 && nums[i] > nums[i-1])) {
				SWAP.intSwap(nums, 1, i);
			}
		}
	}
	// Solve it by partition
	public void wiggleSortPartition(int[] nums) {
		int[] temp = new int[nums.length];
		temp = Arrays.copyOf(nums, nums.length);
		int mid = partition(temp, 0, nums.length - 1, nums.length/2);
		int[] res = new int[nums.length];
		for(int i = 0; i < nums.length; i++) {
			res[i] = mid;
		}
		int l, r;
		if(nums.length % 2 == 0) {
			l = nums.length - 2;
			r = 1;
			for(int i = 0; i < nums.length; i++) {
				if(nums[i] < mid) {
					res[i] = nums[i];
					l -= 2;
				} else if(nums[i] > mid){
					res[r] = nums[i];
					r += 2;
				}
			}
		} else {
			l = 0;
			r = nums.length - 2;
			for(int i = 0; i < nums.length; i++) {
				if(nums[i] < mid) {
					res[l] = nums[i];
					l += 2;
				} else if(nums[i] > mid) {
					res[r] = nums[i];
					r -= 2;
				}
			}
		}
		for(int i = 0; i < nums.length; i++) {
			nums[i] = res[i];
		}
	}
	public int partition(int[] nums, int l, int r, int rank) {
		int left = l, right = r;
		int now = nums[left];
		while(left < right) {
			while(left < right && nums[right] >= now) {
				right--;
			}
			nums[left] = nums[right];
			while(left < right && nums[left] <= now) {
				left++;
			}
			nums[right] = nums[left];
		}
		if(left - l == rank) {
			return now;
		} else if(left - l < rank) {
			return partition(nums, left+1, r, rank-(left-l+1));
		} else {
			return partition(nums, l, right-1, rank);
		}
	}
}
