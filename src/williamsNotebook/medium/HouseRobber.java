/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import williamsNotebook.common.TreeNode;

/**
 * @author yimin Huang
 *	
 *		You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint 
 *		stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police 
 *		if two adjacent houses were broken into on the same night
 *		Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob
 *		tonight without alerting the police.
 *
 *		Input: [1,2,3,1]
 *		Output: 4
 *		Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).  Total amount you can rob = 1 + 3 = 4.
 * Algorithm Class
 */
public class HouseRobber {

	// Method 1: dynamic programming
	// dp[i] represent the maximum amount of rob from day 0 to i
	// base case: dp[0] = array[0], dp[1] = max(array[0], array[1])
	public int robI(int[] array) {
		// Assumption: 
		if(array == null || array.length == 0) return 0;
		if(array.length == 1) return array[0];
		int[] dp = new int[array.length];
		dp[0] = array[0];
		dp[1] = Math.max(array[0], array[1]);
		for(int i = 2; i < array.length; i++) {
			dp[i] = Math.max(dp[i-1] , dp[i-2] + array[i]);
		}
		return dp[array.length - 1];
	}
	// Method 2: Space Optimization 
	// insight: we must take the ith house's property, and we should notice the dp[i-2] and dp[i-3]
	public int robIOptimization(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
        // Because all the value in the nums are non-negative
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        if (nums.length == 3) return Math.max(nums[0] + nums[2], nums[1]);
        int[] dp = new int[4];
        dp[0] = nums[0];
        dp[1] = nums[1];
        // because we assume that all the given elements is positive
        dp[2] = nums[0] + nums[2];
        int res = Math.max(dp[1], dp[2]);
        for(int i = 3; i < nums.length; i++) {
        	dp[i%3] = Math.max(dp[(i-2)%3], dp[(i-3)%3]) + nums[i];
        	res = Math.max(res, dp[i%3]);
        }
        return res;
	}
	
	// Follow Up 2: Houses arranged in a Circle
	// After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not 
	// get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of 
	// the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.
	// 1-d DP: Time ~ O(2N), Space ~ O(N)
	// If s[0] is robbed, then s[1] and s[N - 1] cannot be robbed, so the max1 = s[0] + max of s[2 .. N - 2]
	// If s[0] is not robbed, then s[1] and s[N - 1] can be robbed, so the max2 = max of s[1 .. N - 1].
	public int robII(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		int n = nums.length;
		if(n == 1) return nums[0];
		if(n == 2) return Math.max(nums[0], nums[1]);
	    if(n == 3) return Math.max(nums[0], Math.max(nums[1], nums[2]));
		// if nums[0] is robbed
		int d1 = nums[0] + dp(nums, 2, n - 2);
		// if nums[0] is robbed
		int d2 = dp(nums, 1, n - 1);
		return Math.max(d1, d2);
	}
	private int dp(int[] array, int start, int end) {
		if(start > end) return 0;
		int[] d = new int[array.length];
		d[start] = array[start];
		d[start + 1] = Math.max(array[start], array[start+1]);
		for(int i = start + 2; i <= end; i++) {
			// base case
			d[i] = Math.max(d[i-1], d[i-2] + array[i]);
		}
		return d[end];
	}
	
	// Follow Up 3: Houses arranged in Tree
	// The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." 
	// Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in 
	// this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
	// Method 1: Naive Recursion
	public int rob(TreeNode root) {
		// base case
		if (root == null) return 0;
	    int val = 0;
	    // recursion rule
	    if (root.left != null) {
	        val += rob(root.left.left) + rob(root.left.right);
	    }
	    
	    if (root.right != null) {
	        val += rob(root.right.left) + rob(root.right.right);
	    }
	    return Math.max(val + root.val, rob(root.left) + rob(root.right));
    }
	// Method 2: Think one step further, by pruning the identical subproblems
	public int robOpt(TreeNode root) {
		return robOpt(root, new HashMap<>());
	}
	private int robOpt(TreeNode root, HashMap<TreeNode, Integer> map) {
		// base case :
		if(root == null) return 0;
		if(map.containsKey(root)) return map.get(root);
		int val = 0;
		if(root.left != null) {
			val += robOpt(root.left.left, map) + robOpt(root.left.right, map);
		}
		if(root.right != null) {
			val += robOpt(root.right.left, map) + robOpt(root.right.right, map);
		}
	    val = Math.max(val + root.val, robOpt(root.left, map) + robOpt(root.right, map));
	    map.put(root, val);
	    return val;
	}
	// Method 3: Recursion with Memorization
	// Let's relate rob(root) to rob(root.left) and rob(root.right)..., etc. For the 1st element of rob(root), we only need to sum up the larger
	// elements of rob(root.left) and rob(root.right), respectively, since root is not robbed and we are free to rob its left and right subtrees. 
	// For the 2nd element of rob(root), however, we only need to add up the 1st elements of rob(root.left) and rob(root.right), respectively, plus 
	// the value robbed from root itself, since in this case it's guaranteed that we cannot rob the nodes of root.left and root.right.
	public int robIII(TreeNode root) {
	    int[] res = robSub(root);
	    return Math.max(res[0], res[1]);
	}
	private int[] robSub(TreeNode root) {
	    if (root == null) return new int[2];
	    
	    int[] left = robSub(root.left);
	    int[] right = robSub(root.right);
	    int[] res = new int[2];

	    res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
	    res[1] = root.val + left[0] + right[0];
	    
	    return res;
	}
	
	public static void main(String[] args) {
		HouseRobber solution = new HouseRobber();
		int[] array = new int[] {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
		int robI = solution.robII(array);
		System.out.println(robI);
		
		TreeNode root = new TreeNode(3);
		TreeNode two = new TreeNode(1);
		TreeNode three = new TreeNode(2);
//		TreeNode four = new TreeNode(3);
//		TreeNode five = new TreeNode(1);
		root.left = two;
		two.right = three;
//		two.right = four;
//		three.right = five;
		int rob = solution.rob(root);
		System.out.println(rob);
	}
}
