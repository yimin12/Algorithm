/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月8日 下午8:00:04
* Description:
* 	The sum of K elements in the given array equal to target 
* 	All K Sum problem can be divided into two problems:
* 		2 Sum Problem
* 		Reduce K Sum problem to K – 1 Sum Problem
* 	从4Sum和3Sum，我们可以看出对于KSum的通用套路：将KSum转化为K-1 Sum，最后用2Sum的Two Pointer求解。
*/

public class KSum {

	public List<List<Integer>> fourSum(int[] nums, int target){
		if(nums == null || nums.length == 0) return new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		return kSum(nums, 0, 4, target);
	}
	private List<List<Integer>> kSum(int[] nums, int start, int k, int target){
		int len = nums.length;
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(k == 2) {
			// when there are two candidates left, treatsame as two sum
			int left = start, right = len - 1;
			while(left < right) {
				int sum = nums[left] + nums[right];
				if(sum == target) {
					List<Integer> path = new ArrayList<Integer>();
					path.add(nums[left]);
					path.add(nums[right]);
					res.add(path);
					left++;
					right--;
					// handle the duplicate problem
					while(left < right && nums[left] == nums[left - 1]) left++;
					while(left < right && nums[right] == nums[right + 1]) right--;
				} else if(sum < target) {
					left++;
				} else {
					right--;
				}
			}
		} else {
			for(int i = start; i < len - (k - 1); i++) {
				if(i > start && nums[i] == nums[i-1]) continue;
				// divide and conquer
				List<List<Integer>> temp = kSum(nums, i+1, k-1, target - nums[i]);
				for(List<Integer> t : temp) {
					// for every sub answer, all the element you just traversed.
					t.add(0, nums[i]);
				}
				res.addAll(temp);
			}
		}
		return res;
	}
}
