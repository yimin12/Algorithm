/**
 * 
 */
package williamsNotebook.easy.unionFind;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月27日 下午7:56:04
* Description:
* 	On a 2D plane, we place stones at some integer coordinate points. Each coordinate point may have at most one stone.
* 	Now, a_move_consists of removing a stone that shares a column or row with another stone on the grid
* 	What is the largest possible number of moves we can make?
* Example:
* 	Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]] Output: 5
* 	Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]  Output: 3
*/
public class MostStoneRemovedSameRowColumn {

	// Key insight: How to convert this problem to number of Island
//	Number ofNumber stones to be removed = total number of stones - number of island
	// Time Complexity: O(NlogN), where N is the length of stones. (If we used union-by-rank, this can be O(N∗α(N)), where α is the Inverse-Ackermann function.)
	// Space Complexity: O(N).
	public static int numberOfIslands;
	// param1: key, param2: father
	public static HashMap<String, String> parent;
	private String buildKey(int[] coordinate) {
		return coordinate[0] + "" + coordinate[1];
	}
	public int removeStones(int[][] stones) {
		numberOfIslands = stones.length;
		parent = new HashMap<String, String>();
		for(int[] stone:stones) {
			String s = buildKey(stone);
			// step 1: pointing to itself
			parent.put(s, s);
		}
		for(int[] stone:stones) {
			String key = buildKey(stone);
			for(int[] stone2:stones) {
				String key2 = buildKey(stone2);
				if(stone[1] == stone2[1] || stone[0] == stone2[0]) {
					union(key, key2);
				}
			}
		}
		return stones.length - numberOfIslands;
	}
	private String find(String key) {
		if(!parent.get(key).equals(key)) {
			parent.put(key, find(parent.get(key)));
		}
		return parent.get(key);
	}
	private void union(String one, String two) {
		String parentOne = find(one);
		String parentTwo = find(two);
		if(parentOne != parentTwo) {
			parent.put(parentOne, parentTwo);
			numberOfIslands--;
		}
	}
	// Method 2: Recursive implementation
	public int removeStonesII(int[][] stones) {
		int islands = 0;
		Set<Integer> visited = new HashSet<Integer>();
		for(int i = 0; i < stones.length; i++) {
			if(visited.contains(i)) continue;
			islands++;
			dfs(i, stones, visited);
		}
		return stones.length - islands;
	}
	private void dfs(int i, int[][] stones, Set<Integer> visited) {
		visited.add(i);
		for(int next = 0; next < stones.length; next++) {
			if(visited.contains(next)) continue;
			if(stones[i][0] == stones[next][0] || stones[i][1] == stones[next][1]) {
				dfs(next, stones, visited);
			}
		}
	}
	// Method 3: Use stack to simulate dfs
	public int removeStonesStack(int[][] stones) {
		int N = stones.length;
		int[][] graph = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
					graph[i][++graph[i][0]] = j;
					graph[j][++graph[j][0]] = i;
				}
			}
		}
		int ans = 0;
		boolean[] seen = new boolean[N];
		for(int i = 0; i < N; i++) if(!seen[i]){
			Deque<Integer> stack = new LinkedList<Integer>();
			stack.push(i);
			seen[i] = true;
			ans--;
			while(!stack.isEmpty()) {
				int node = stack.pop();
				ans++;
				for(int k = 1; k <= graph[node][0]; k++) {
					int nei = graph[node][k];
					if(!seen[nei]) {
						stack.push(nei);
						seen[nei] = true;
					}
				}
			}
		}
		return ans;
	}
}
