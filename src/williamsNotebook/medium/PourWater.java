/**
 * 
 */
package williamsNotebook.medium;

import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月22日 下午11:47:11
* Description: LeetCode 755
* 	We are given an elevation map, heights[i] representing the height of the terrain at that index. The width at each index is 1. 
* 	After V units of water fall at index K, how much water is at each index?
* 	Water first drops at index K and rests on top of the highest terrain or water at that index. 
* 	Then, it flows according to the following rules:
* 		If the droplet would eventually fall by moving left, then move left.
* 		Otherwise, if the droplet would eventually fall by moving right, then move right.
* 		Otherwise, rise at it's current position
* 	Here, "eventually fall" means that the droplet will eventually be at a lower level if it moves in that direction. 
* 		Also, "level" means the height of the terrain plus any water in that column.
* 	We can assume there's infinitely high terrain on the two sides out of bounds of the array. 
* 	Also, there could not be partial water being spread out evenly on more than 1 grid block 
* 	- each unit of water has to be in exactly one block.

*/

public class PourWater {

	// Method 1: Model Simulation
	public int[] pourWater(int[] heights, int V, int K) {
		for(int i = 0; i < V; i++) {
			int cur = K;
			// Try to move left
			while(cur > 0 && heights[cur] >= heights[cur - 1]) {
				cur--;
			}
			// Try to move right
			while(cur < heights.length - 1 && heights[cur] >= heights[cur + 1]) {
				cur++;
			}
			// Move left to K
			while(cur > K && heights[cur] >= heights[cur - 1]) {
				cur--;
			}
			heights[cur]++;
		}
		return heights;
	}
	// Method 2: Using two PriorityQueue
	// Time : O(V) and Extra Space is O(v) in the worst case
	public int[] pourWaterII(int[] heights, int V, int K) {
		PriorityQueue<Integer> left = new PriorityQueue<Integer>((a, b) -> (heights[a] == heights[b] ? b-a:heights[a]-heights[b]));
		PriorityQueue<Integer> right = new PriorityQueue<Integer>((a, b) -> (heights[a] == heights[b]) ? a-b:heights[a] - heights[b]);
		int i = K - 1, j = K + 1;
		for(int d =0; d < V; d++) {
			while(i >= 0 && heights[i] <= heights[i+1]) left.offer(i--);
			while(j < heights.length && heights[j] < heights[j-1]) right.offer(j++);
			int l = left.isEmpty() ? K : left.peek();
			int r = right.isEmpty() ? K : right.peek();
			if(heights[l] < heights[K]) {
				heights[l]++;
				left.poll();
				left.offer(l);
			} else if(heights[r] < heights[K]) {
				heights[r]++;
				right.poll();
				right.offer(r);
			} else {
				heights[K]++;
			}
		}
		return heights;
	}
}
