/**
 * 
 */
package williamsNotebook.easy.backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月1日 下午5:37:36
* Description:
* 	Given a set of candidate numbers (candidates)(without duplicates)and a target number (target), 
* 	find all unique combinations in candidates where the candidate numbers sums to target
* Example:
* 	Input: candidates = [2,3,6,7], target = 7, A solution set is: [[7],[2,2,3]]
* 	Input: Input: candidates = [2,3,5], target = 8, A solution set is: [[2,2,2,2],[2,3,3],[3,5]]
*/
public class CombinationSum {

	// Follow Up 1: it can be reused for each distinct number
	public List<List<Integer>> combinationSum(int[] candidates, int target){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		backTracking(target, 0, 0, candidates, new ArrayList<Integer>(), res);
		return res;
	}
	private void backTracking(int target, int index, int sum, int[] candidates, List<Integer> list, List<List<Integer>> res) {
		// base case or termination condition
		if(sum >= target) {
			if(sum == target) {
				res.add(new ArrayList<Integer>(list));
			}
			return;
		}
		for(int i = index; i < candidates.length; i++) {
			list.add(candidates[i]);
			// each candidates can be used multiple times
			backTracking(target, i, sum + candidates[i], candidates, list, res);
			list.remove(list.size() - 1);
		}
	}
	
	// Follow Up 2: What if there is duplicate elements in the given and we want each distinct solution
	// In addition: Each candidates can only be used once
	public List<List<Integer>> combinationSumDuplicate(int[] candidates, int target){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		backTrackingII(candidates, target, 0, 0, new ArrayList<Integer>(), res);
		return res;
	}
	private void backTrackingII(int[] candidates, int target, int sum, int index, List<Integer> list, List<List<Integer>> res) {
		if(sum >= target) {
			if(sum == target) res.add(new ArrayList<Integer>(list));
			return;
		}
		// handle the duplicate problem
		for(int i = index; i < candidates.length; i++) {
			list.add(candidates[i]);
			// Each candidate can only be used once
			backTrackingII(candidates, target, sum + candidates[i], index + 1, list, res);
			list.remove(list.size() - 1);
			// handle the duplicate problem
			while(i + 1 < candidates.length && candidates[i] == candidates[i+1]) {
				i++;
			}
		}
	}
	
	// Follow Up 3: Given an integer array with all positive numbers and no duplicates, find the number of 
	// possible combinations that add up to a positive integer target. (permutation of combination)
	// The possible combination ways are: (Note that different sequences are counted as different combinations.)
	// (1, 1, 1, 1)
	// (1, 1, 2)
	// (1, 2, 1)
	// (1, 3)
	// (2, 1, 1)
	// (2, 2)
	// (3, 1)
	// You can see the difference between Follow Up 1
	public List<List<Integer>> combinationSumIII(int[] candidates, int target){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		backTrackingIII(target, 0, 0, candidates, new ArrayList<Integer>(), res);
		return res;
	}
	private void backTrackingIII(int target, int index, int sum, int[] candidates, List<Integer> list, List<List<Integer>> res) {
		if(sum >= target) {
			if(sum == target) {
				res.add(new ArrayList<Integer>(list));
			}
			return;
		}
		// Notice. index i start from 0
		for(int i = 0; i < candidates.length; i++) {
			list.add(candidates[i]);
			// each candidates can be used multiple times
			backTrackingIII(target, i, sum + candidates[i], candidates, list, res);
			list.remove(list.size() - 1);
		}
	}
	
	// Follow Up 5: Return the number of the solution in Follow Up 4 
	private int[] dp;
	public int combinationSumIV(int[] nums, int target) {
		dp = new int[target + 1];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		return helper(nums, target);
	}
	private int helper(int[] nums, int target) {
		if(dp[target] != -1) {
			return dp[target];
		}
		int res = 0;
		for(int i = 0; i < nums.length; i++) {
			if(target >= nums[i]) {
				res += helper(nums, target-nums[i]);
			}
		}
		dp[target] = res;
		return res;
	}
	public static void main(String[] args) {
		CombinationSum solution = new CombinationSum();
		int[] candidates = {1, 2, 3};
		List<List<Integer>> combinationSum = solution.combinationSumIII(candidates, 4);
		for(List<Integer> list:combinationSum) {
			System.out.println(list.toString());
		}
	}
}
