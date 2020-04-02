/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月1日 下午2:48:51
* Description: LeetCode 675
* 	You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
* 	1. 0 represents the obstacle can't be reached.
* 	2. 1 represents the ground can be walked through.
* 	3. The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
* 	You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. 
* 	And after cutting, the original place has the tree will become a grass (value 1).
* 	You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. 
* 	If you can't cut off all the trees, output -1 in that situation.
* Assumption:
* 	You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
* Example 1:
* 	[
	 [1,2,3],
	 [0,0,4],
	 [7,6,5]
	]
	Output: 6
*/
public class CutOffTreesForGolf {

	// Key Insight: Find the shortest Path to the next tree you can cut
	private static int[][] DIRS = {{-1,0},{1,0},{0,-1},{0,1}};
	public int cutOffTree(List<List<Integer>> forest) {
		// sort all trees with height, r-row, c-column
		List<int[]> trees = new ArrayList<int[]>();
		int[][] map = new int[forest.size()][forest.get(0).size()];
		for(int r = 0; r < forest.size(); r++) {
			for(int c = 0; c < forest.get(0).size(); c++) {
				int h = forest.get(r).get(c);
				map[r][c] = h;
				if(h > 1) {
					trees.add(new int[] {h, r, c});
				}
			}
		}
		// Sort threes based on the height of tree
		Collections.sort(trees, (t1, t2) -> Integer.compare(t1[0], t2[0]));
		int res = 0, r0 = 0, c0 = 0;
		for(int[] tree:trees) {
			int d = dist(map, r0, c0, tree[1], tree[2]);
			if(d < 0) {
				return -1;
			}
			res += d;
			r0 = tree[1];
			c0 = tree[2];
		}
		return res;
	}
	private int dist(int[][] map, int sr, int sc, int tr, int tc) {
		int distance = 0;
		boolean[][] visited = new boolean[map.length][map[0].length];
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		while(!q.isEmpty()) {
			int levelSize = q.size();
			while(levelSize-- > 0) {
				int[] pos = q.poll();
				if(pos[0] == tr && pos[1] == tc) {
					return distance;
				}
				for(int[] dir:DIRS) {
					int nextR = pos[0] + dir[0];
					int nextC = pos[1] + dir[1];
					if(nextR >= 0 && nextC >= 0 && nextR < map.length && nextC < map[0].length) {
						if(map[nextR][nextC] != 0 && !visited[nextR][nextC]) {
							q.offer(new int[]{nextR, nextC});
							visited[nextR][nextC] = true;
						}
					}
				}
			}
			distance++;
		}
		return -1;
	}
	// Method 2: Dijkstra's Algorithem(Greedy and PriorityQueue)
	public int cutOffTreeII(List<List<Integer>> forest) {
		// sort all trees with height, r-row, c-column
		List<int[]> trees = new ArrayList<int[]>();
		int[][] map = new int[forest.size()][forest.get(0).size()];
		for(int r = 0; r < forest.size(); r++) {
			for(int c = 0; c < forest.get(0).size(); c++) {
				int h = forest.get(r).get(c);
				map[r][c] = h;
				if(h > 1) {
					trees.add(new int[] {h, r, c});
				}
			}
		}
		// Sort threes based on the height of tree
		Collections.sort(trees, (t1, t2) -> Integer.compare(t1[0], t2[0]));
		int res = 0, r0 = 0, c0 = 0;
		for(int[] tree:trees) {
			int d = dijkstra(map, r0, c0, tree[1], tree[1]);
			if(d < 0) return -1;
			res += d;
			r0 = tree[1];
			c0 = tree[2];
		}
		return res;
	}
	private int dijkstra(int[][] map, int sr, int sc, int tr, int tc) {
		// Init distance matrix
		int[][] distance = new int[map.length][map[0].length];
		for(int[] row : distance) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		distance[sr][sc] = 0;
		// Init priority queue to find shortest distance, the third value is the distance
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((a, b) -> a[2] - a[2]);
		q.offer(new int[] {sr, sc, 0});
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			for(int[] dir:DIRS) {
				int nextR = pos[0] + dir[0];
				int nextC = pos[0] + dir[1];
				if(nextR >= 0 && nextC >= 0 && nextR < map.length && nextC < map[0].length) {
					if(map[nextR][nextC] != 0 && pos[2] + 1 < distance[nextR][nextC]) {
						distance[nextR][nextC] = pos[2] + 1;
						q.offer(new int[] {nextR, nextC, pos[2] + 1});
					}
				}
			}
		}
		if(distance[tr][tc] == Integer.MAX_VALUE) {
			return -1;
		}
		return distance[tr][tc];
	}
}
