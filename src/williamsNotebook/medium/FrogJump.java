/**
 * 
 */
package williamsNotebook.medium;

import java.util.HashMap;
import java.util.HashSet;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月10日 下午8:19:38
* Description:
* 	A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. 
* 	The frog can jump on a stone, but it must not jump into the water.
* 	Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the
*   river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
*   If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can 
*   only jump in the forward direction.	
* Assumption:
* 	The number of stones is ≥ 2 and is < 1100.
* 	Each stone's position will be a non-negative integer < 2^31.
* 	The first stone's position is always 0.
* Example 1:
	Given stones = [0,1,3,5,6,8,12,17]
	Input:
	[0,1,3,5,6,8,12,17]
	Output:
	true
	
	Explanation:
	There are a total of 8 stones.
	The first stone at the 0th unit, second stone at the 1st unit,third stone at the 3rd unit, and so on...The last stone at the 17th unit.
	Return true. The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, the
*/
public class FrogJump {

	// DFS to enumerate all possibilities
	// naive solution
	boolean res = false;
	public boolean canPassDFS(int[] stones) {
		if(stones == null ||stones.length == 0) {
			return false;
		}
		dfs(stones, 0, 1, 1);
		return res;
	}
	// record the range of distance you can jump
	private void dfs(int[] stones, int x, int minJump, int maxJump) {
		if(res == true || x == stones.length - 1 ) {
			res = true;
			return;
		}
		// there are only three possible length to jump
		for(int i = x + 1; i < stones.length; i++) {
			int needLen = stones[i] - stones[x];
			if(needLen > maxJump) {
				// Case 1. can not reach to next stones
				break; 
			}
			if(needLen < minJump) {
				// Case 2: can not specific reach to next stone, but it can reach to the next stones, so use continue
				continue;
			}
			dfs(stones, i, needLen-1, needLen+1);
		}
	}
	// Version 2:
	public boolean canPassDFSII(int[] stones) {
		if(stones == null || stones.length == 0) return false;
		for(int i = stones.length - 1; i > 2; i--) {
			if(stones[i] > 2 * stones[i-1]) return false;
		}
		findNext(stones, 0, 0);
		return res;
	}
	private void findNext(int[] stones, int lastJump, int cur) {
		if(cur == stones.length - 1) {
			res = true;
			return;
		}
		if(res) {
			return;
		}
		for(int k = cur + 1; k < stones.length; k++) {
			int gap = stones[k] - stones[cur];
			if(gap > lastJump + 1) {
				break;
			}
			if(gap == lastJump - 1 || gap == lastJump || gap == lastJump + 1) {
				findNext(stones, gap, k);
			}
		}
		return;
	}
	
	
	// Method 3:
	public boolean canCross(int[] stones) {
		HashMap<Integer, HashSet<Integer>> dp = new HashMap<Integer, HashSet<Integer>>(stones.length);
		for(int i = 0; i < stones.length; i++) {
			dp.put(stones[i], new HashSet<Integer>());
		}
		dp.get(0).add(0);
		for(int i = 0; i < stones.length - 1; i++) {
			int stone = stones[i];
			for(int k : dp.get(stone)) {
				if(k - 1 > 0 && dp.containsKey(stone + k - 1)) {
					dp.get(stone + k - 1).add(k - 1);
				}
				if(dp.containsKey(stone + k)) {
					dp.get(stone + k).add(k);
				}
				if(dp.containsKey(stone + k + 1)) {
					dp.get(stone + k + 1).add(k+1);
				}
			}
		}
		return !dp.get(stones[stones.length - 1]).isEmpty();
	}
	// Method 4: dynamic programming
	public boolean canCrossIII(int[] stones) {
		if(stones == null || stones.length == 0) {
			return false;
		}
		if(stones.length == 1) return true;
		int n = stones.length;
		boolean[] dp = new boolean[n];
		dp[0] = true;
		dp[1] = stones[1] == 1;
		for(int i = 1; i < n; i++) {
			if(!dp[i]) {
				continue;
			} 
			for(int j = i + i; j < n; j++) {
				if(stones[j] - stones[i] > stones[i] + 1) {
					break;
				}
				int span = stones[j] - stones[i];
				if(span == i - 1 || span == i || span == i + 1) {
					dp[j] = true;
				}
			}
		}
		return dp[n-1];
	}
	public static void main(String[] args) {
		FrogJump solution = new FrogJump();
		int[] stones = {0,1,3,5,6,8,12,17};
		boolean canPassDFS = solution.canPassDFSII(stones);
		System.out.println(canPassDFS);
	}
}
