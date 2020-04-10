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

	//  Method 1: Divide and Conquer
	// Time: O(n*n-1*n-2*...n-k) Space: O(kn)
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
	// Dynamic Programming
//	State:
//		• f[i][j][t]前i个数取j个数出来能否和为t
//		 Function:
//		• f[i][j][t] = f[i – 1][j – 1][t – a[i-1]] + f[i – 1][j][t]
//		划分出来的子问题是： 在前i-1个数中，选用a[i-1]最为答案之一，找j-1的和为t-a[i-1]的个数， 或者在前i-1个数中已经完成了jsum的操作的个数。
//		Intialization
//		• f[i][0][0] = 1
//		Answer
//		• f[n][k][target]
	public int kSum(int[] A, int k, int target) {
		// We assume that target is larger than 0, so that we can use the idea in back pack
		int n = A.length;
		int[][][] dp = new int[n+1][k+1][target+1];
		for(int i = 0; i <= n; i++) {
			dp[i][0][0] = 1;
		}
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= k; j++) {
				for(int t = 1; t<= target; t++) {
					dp[i][j][t] = 0;
					if(t >= A[i-1]) {
						dp[i][j][t] = dp[i-1][j-1][target - A[i-1]];
					}
					dp[i][j][t] += dp[i-1][j][t];
				}
			}
		}
		return dp[n][k][target];
	}
	// Follow Up 1: Print out all solution while running dynamic programming
	int[] res;
	int total; // target
	int[] A;
	int K; // k sum
	int [][][] f;
	// recursively print the answer
	void printAnswer(int i, int j, int k) {
		// base case
		if(j == 0) {
			for(int h = 0; h < K; h++) {
				System.out.println(res[h]);
				if(h == K - 1) System.out.println(" = " + total);
				else System.out.print(" + ");
			}
			return;
		}
		// Induction rule
		if(f[i-1][j][k] > 0) {
			printAnswer(i-1, j, k);
		}
		if(j > 0 && k >= A[i-1] && f[i-1][j-1][k-A[i-1]] > 0) {
			res[j-1] = A[i-1];
			printAnswer(i-1, j-1, k-A[i-1]);
		}
	}
	// filling the form by using dp
	public int kSumPrint(int[] nums, int kk, int target) {
		K = kk;
		A = nums;
		total = target;
		int n = A.length;
		res = new int[K];
		f = new int[n+1][K+1][total+1];
		int i, j, k;
		// base case
		for(j = 0; j <= K; j++) {
			for(k = 0; k <= total; k++) {
				f[0][j][k] = 0;
			}
		}
		f[0][0][0] = 1;
		for(i = 1; i <= n; i++) {
			for(j = 0; j <= K; j++) {
				for(k = 0; k <= total; k++) {
					// Not using A[i-1] as result
					f[i][j][k] = f[i-1][j][k];
					// Use A[i-1] as result
					if(j > 0 && k >= A[i-1]) {
						f[i][j][k] = f[i-1][j-1][k-A[i-1]];
					}
				}
			}
		}
		printAnswer(n, K, total);
		return f[n][K][total];
	}
}
