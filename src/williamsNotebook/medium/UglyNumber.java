/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 下午2:45:40
* Description:
* 	Write a program to check whether a given number is an ugly number.
* 	Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly 
* 	while 14 is not ugly since it includes another prime factor 7.
* 	Note that 1 is typically treated as an ugly number.
* 	Example: 
* 		Given num = 8 return true
* 		Given num = 14 return false
*/

public class UglyNumber {

	// Follow Up 1: Determine whether it is ugly number
	public boolean isUgly(int num) {
		if(num == 0) return false;
		if(num == 1) return true;
		int[] primes = new int[] {2,3,5};
		for(int i = 0; i < primes.length; i++) {
			while(num % primes[i] == 0) {
				num = num / primes[i];
			}
		}
		return num == 1;
	}
	
	// Follow Up 2: Design an algorithm to find the nth ugly number. The first 10 ugly numbers 
	// Idea is similar with KthSmallestThreeFiveSeven
	// are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...
	// Time: O(n) and Space: O(n)
	public int nthUglyNumber(int n) {
		int[] ugly = new int[n];
		ugly[0] = 1;
		int index2 = 0, index3 = 0, index5 = 0;
		int factor2 = 2, factor3 = 3, factor5 = 5;
		for(int i = 1; i <= n;i++) {
			int min = Math.min(Math.min(factor2, factor3), factor5);
			ugly[i] = min;
			// key insight: Ugly Number = ugly number * uglyNumber
			if(factor2 == min) factor2 = 2 * ugly[++index2];
			if(factor3 == min) factor3 = 3 * ugly[++index3];
			if(factor5 == min) factor5 = 5 * ugly[++index5];
		}
		return ugly[n-1];
	}
	// Method 2:
	// Time: O(nlogn) Space:(n)
	public int nthUglyNumberII(int n) {
		if(n <= 0) {
			return 0;
		}
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		pq.offer(1L);
		for(int i = 0; i < n - 1; i++) {
			long temp = pq.poll();
			while(!pq.isEmpty() && pq.peek() == temp) {
				temp = pq.poll();
			}
			pq.offer(temp * 2);
			pq.offer(temp * 3);
			pq.offer(temp * 5);
		}
		return pq.poll().intValue();
	}
	// Method 3: DFS with memorization
	public int nthUglyNumberDFS(int n) {
		int[] dp = new int[n];
		dp[0] = 1;
		return dfs(dp,1,0,0,0,n);
	}
	private int dfs(int[] dp, int i, int p2, int p3, int p5, int n) {
		if(i == n) return dp[n-1];
		dp[i] = Math.min(dp[p2] * 2, Math.min(dp[p3] * 3, dp[p5] * 5));
		if(dp[i] == dp[p2] * 2) p2++;
		if(dp[i] == dp[p3] * 3) p3++;
		if(dp[i] == dp[p5] * 5) p5++;
		return dfs(dp, i+1, p2, p3, p5, n);
	}
	
	// Follow Up 3: Super Ugly Number with a list of k primes
	// Super ugly numbers are positive numbers whose all prime factors are in the given prime listprimesof sizek.
	// Input: n = 12, primes = [2,7,13,19] Output: 32 
	// Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 
	//  	super ugly numbers given primes = [2,7,13,19] of size 4.	
	// Method 1: Dynamic Programming, logical is exactly the same
	public int nthSuperUglyNumber(int n, int[] primes) {
		int[] uglyNumber = new int[n];
		int[] indices = new int[primes.length];
		int[] candidates = new int[primes.length];
		Arrays.fill(candidates, 1);
		int next = 1;
		for(int i = 0; i < n; i++) {
			uglyNumber[i] = next;
			next = Integer.MAX_VALUE;
			for(int j = 0; j < primes.length; j++) {
				if(candidates[j] == uglyNumber[i]) {
					candidates[j] = primes[j] * uglyNumber[indices[j]++];
				}
				next = Math.min(candidates[j], next);
			}
		}
		return uglyNumber[n-1];
	}
	// Method 2: PriorityQueue
	public int nthUglyNumber(int n, int[] primes) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.offer(1);
		for(int prime:primes) {
			pq.offer(prime);
		}
		for(int i = 1; i < n; i++) {
			int temp = pq.poll();
			// it might encounter a * b and b * a when you generating the node
			while(!pq.isEmpty() && pq.peek() == temp) {
				temp = pq.poll();
			}
			for(int prime:primes) {
				long multiply = (long) temp * prime;
				// if it is overflow, just discard
				if(multiply < Integer.MAX_VALUE) {
					pq.offer((int) multiply);
				}
			}
		}
		return pq.poll().intValue();
	}
}
