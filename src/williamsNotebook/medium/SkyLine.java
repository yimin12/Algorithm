/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author yimin Huang
 *	
 *		A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are 
 *		given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by 
 *		these buildings collectively (Figure B).
 *		The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the 
 *		left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. 
 *		You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *		For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *		The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the 
 *		left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, 
 *		and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 *		For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *	
 *			The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 *			The input list is already sorted in ascending order by the left x position Li.
 *			The output list must be sorted by the x position.
 *			There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 
 *					is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 *
 * Algorithm Class
 */
public class SkyLine {
	
	public List<int[]> getSkyline(int[][] buildings){
		List<int[]> skyline = new ArrayList<int[]>();
		int n = buildings.length;
		if(n == 0) return skyline;
		
		// Map: key - x coordinate, value - a list of EdgePoints (contain height and isStart)
		Map<Integer, List<EdgePoint>> map = new HashMap<Integer, List<EdgePoint>>();
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		for(int i = 0; i < n; i++) {
			int left = buildings[i][0];
			int right = buildings[i][1];
			int height = buildings[i][2];
			if(!map.containsKey(left)) { // start point
				map.put(left, new ArrayList<EdgePoint>());
				minHeap.add(left);
			}
			map.get(left).add(new EdgePoint(height, true));
			if(!map.containsKey(right)) { // end point
				map.put(right, new ArrayList<EdgePoint>());
				minHeap.add(right);
			}
			map.get(right).add(new EdgePoint(height, false));
		}
		// Binary Search Tree: key - height, value - number of building with same height
		TreeMap<Integer, Integer> tree = new TreeMap<Integer, Integer>();
		int prevHeight = 0, curHeight = 0;
		while(!minHeap.isEmpty()) {
			int x = minHeap.poll(); // get the x coordinate
			curHeight = 0;
			for(EdgePoint pt:map.get(x)) {
				int height = pt.height;
				if(pt.isStart) {
					// if it is start point, add the height to BST
					if(!tree.containsKey(height)) tree.put(height, 1);
					else tree.put(height, tree.get(height) + 1);
				} else {
					// end point, remove the point from treeMap
					if(tree.get(height) == 1) {
						tree.remove(height);
					} else {
						tree.put(height, tree.get(height) - 1);
					}
				}
			}
			// find the largest height in TreeMap, set height = 0 if three is empty
			curHeight = tree.isEmpty() ? 0 : tree.lastKey();
			if(curHeight != prevHeight) {
				skyline.add(new int[] {x, curHeight});
				prevHeight = curHeight;
			}
		}
		return skyline;
	}
	
	public static void main(String[] args) {
		SkyLine solution = new SkyLine();
		int[][] buildings = new int[][] {{2,9,10},{3,7,15},{5,12,12},{16,20,10},{19,24,8}};
		List<int[]> skyline = solution.getSkyline(buildings);
		for(int[] array:skyline) {
			System.out.println(Arrays.toString(array));
		}
	}
}
class EdgePoint{
	int height;
	boolean isStart;  // start or end point
	public EdgePoint(int height, boolean isStart) {
		this.height = height;
		this.isStart = isStart;
	}
}