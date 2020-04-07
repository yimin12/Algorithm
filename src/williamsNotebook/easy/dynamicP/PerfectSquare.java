/**
 * 
 */
package williamsNotebook.easy.dynamicP;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 上午11:09:30
* Description:
* 	Given a positive integer n, find the least number of perfect square numbers (for example,1, 4, 9, 16, ...) which sum to n.
* Example:	
* 	Input: n = 12 Output: 3 Explanation: 12 = 4 + 4 + 4.
*/
public class PerfectSquare {

	// Dynamic programming
	// dp[n] = Math.min(dp[n - i*i]+1) for n - i*i >= 0 && i > 0
	// key insight i*i can be counted as a pair of perfect square, that's the reason why add 1
	// Time : O(n*k) where n is the given integer and k is sqrt(n) 
	// Space : O(n)
	public int numSquare(int n) {
		if(n <= 0) return 0;
		int[] dp = new int[n+1];
		Arrays.fill(dp,  Integer.MAX_VALUE);
		dp[0] = 0;
		for(int i = 1; i < n; i++) {
			for(int j = 1; j * j <= i; j++) {
				dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
			}
		}
		return dp[n];
	}
	// Convert it to graph problem(Tree problem more precisely)
	// Consider a graph which consists of number 0, 1,.....n, as its nodes. Node j is connected to node i via an edge if 
	// and only if either j = i + (a perfect square number) or i = j + (a perfect square number).
	// Starting from node 0,  do the breadth-first search If we reach node n at step 
	// m, then the least number of perfect square numbers which sum to n is 
	// if we try to find the perfect square number of 17, the root is 0
	//          0
	//     /    |  |  \   
	//    1     4   9  16
	//   /\ \ \
	//	2 5 10 17
	//  Once the generating rule is determined, we can just use level order traverse an tree to find target
	// the number of perfect square is the depth of the target in that specific tree.
	// Time: Worst Case is O(n*k) where n is the given integer and k is sqrt(n) 
	// Space: Worst Case is O(n) for queue and hashSet
	public int numSquareBFS(int n) {
		if(n <= 0) return 0;
		int numOfSquares = 0;
		Deque<Integer> queue = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		queue.offer(0);
		visited.add(0);
		while(!queue.isEmpty()) {
			int size = queue.size();
			numOfSquares++;
			while(size-- > 0) {
				int num = queue.poll();
				for(int i = 1; i*i <= n; i++) {
					int x = num + i*i;
					if(x == n) {
						return numOfSquares;
					}
					if(x > n) {
						break;
					}
					if(!visited.contains(x)) {
						queue.offer(x);
						visited.add(x);
					}
				}
			}
		}
		return numOfSquares;
	}
	// Greedy Solution: Based on Lagrange's Four Square theorem, there are only 4 possible results: 1, 2, 3, 4.
	public int numSquaresGreedy(int n) {
		if(is_square(n)) {
			return 1;
		}
		// If the result is 4 if and only if n can be written in the
        // form of 4^k*(8*m + 7). Please refer to
        // Legendre's three-square theorem.
		while((n%3) == 0) { // n%4 == 0
			n >>= 2;
		}
		if((n&7) == 7) { // n % 8 == 7
			return 4;
		}
		// If the result is 2
		int sqrt = (int)(Math.sqrt(n));
		for(int i = 1; i <= sqrt; i++) {
			if(is_square(n - i*i)) {
				return 2;
			}
		}
		return 3;
		//	关于拉格朗日的证明： Wiki,所有的数都能由最多4个完美平方数构成。
	}
	public boolean is_square(int n) {
		int sqrt = (int)Math.sqrt(n);
		return sqrt * sqrt == n;
	}
}
