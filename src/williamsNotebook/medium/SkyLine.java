/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
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
 *		left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 鈮� Li, Ri 鈮� INT_MAX, 0 < Hi 鈮� INT_MAX, and Ri - Li > 0. 
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
	
	// Method 1: Regular method for using priority queue
	// In java's implementation, maxHeap.remove() consume O(n) time;
	// Time: O(n^2) and Extra space for O(n) 
	public List<int[]> getSkylineI(int[][] buildings){
		List<int[]> result = new ArrayList<int[]>();
		List<int[]> heights = new ArrayList<int[]>();
		// put all the candidate height in the heights list
		for(int[] building : buildings) {
			heights.add(new int[] {building[0], -building[2]});
			heights.add(new int[] {building[1], building[2]});
		}
		// parameter type: param1: container you want to sort, param2: lambda expression for new comparator
		// sorting rules: first by its x coordinates then by its height in ascending order;
		Collections.sort(heights, (a, b)->{
			if(a[0] == b[0]) {
				// if in the same vertical line, return the higher value
				return a[1] - b[1];
			}
			return a[0]-b[0];
		});
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11,(a, b)-> b - a);
		maxHeap.offer(0);
		int prev = 0;
		for(int[] h:heights) {
			if(h[1] < 0) {
				// if it comes from starting point, add it to maxHeap
				maxHeap.offer(-h[1]);
			} else {
				// if it comes from ending point, remove it from maxHeap
				maxHeap.remove(h[1]);
			}
			int cur = maxHeap.peek();
			if(cur != prev) {
				result.add(new int[]{h[0], cur});
				prev = cur;
			}
		}
		return result;
	}
	// Method 2: Sweep Line and TreeMap
	// Time: O(nlogn) and Extra space for O(n) 
	public List<int[]> getSkylineII(int[][] buildings){
		List<int[]> result = new ArrayList<int[]>();
		List<int[]> heights = new ArrayList<int[]>();
		// put all the candidate height in the heights list
		for(int[] building : buildings) {
			heights.add(new int[] {building[0], -building[2]});
			heights.add(new int[] {building[1], building[2]});
		}
		Collections.sort(heights, (a, b)->(a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
		TreeMap<Integer, Integer> heightMap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		heightMap.put(0, 1);
		int prevHeight = 0;
		List<int[]> skyLine = new LinkedList<int[]>();
		for(int[] h : heights) {
			if(h[1] < 0) {
				heightMap.put(-h[1], heightMap.getOrDefault(-h[1], 0) + 1);
			} else {
				Integer cnt = heightMap.get(h[1]);
				if(cnt == 1) {
					heightMap.remove(h[1]);
				} else {
					heightMap.put(h[1], cnt-1);
				}
			}
			// get the highest value
			int currHeight = heightMap.firstKey();
			if(prevHeight != currHeight) {
				skyLine.add(new int[]{h[0], currHeight});
				prevHeight = currHeight;
			}
		}
		return skyLine;
		
	}
	// Version 2;
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
	// Method 3: Using self defined LinkedList
	static class KeyPoint{
		int key, height;
		public KeyPoint next;
		public KeyPoint(int key, int height) {
			this.key = key;
			this.height = height;
		}
	}
	public List<int[]> getSkyLineIII(int[][] buildings){
		List<int[]> res = new ArrayList<int[]>();
		KeyPoint dummy = new KeyPoint(-1, 0);
		KeyPoint prev = dummy;
		for(int[] building: buildings) {
			int left = building[0];
			int right = building[1];
			int height = building[2];
			
			while(prev.next != null && prev.next.key <= left) {
				prev = prev.next;
			}
			int prevHeight = prev.height;
			if(prev.key == left) {
				prev.height = Math.max(prev.height, height);
			} else if(prev.height < height) {
				KeyPoint next = prev.next;
				prev.next = new KeyPoint(left, height);
				prev = prev.next;
				prev.next = next;
			}
			
			KeyPoint preIter = prev;
			KeyPoint curIter = prev.next;
			while(curIter != null && curIter.key < right) {
				prevHeight = curIter.height;
				curIter.height = Math.max(curIter.height, height);
				if(curIter.height == preIter.height) {
					preIter.next = curIter.next;
				} else {
					preIter = curIter;
				}
				curIter = curIter.next;
			}
			if(preIter.height != prevHeight && preIter.key != right && (curIter == null || curIter.key != right)) {
				KeyPoint next = preIter.next;
				preIter.next = new KeyPoint(right, prevHeight);
				preIter.next.next = next;
			}
		}
		KeyPoint first = dummy;
		KeyPoint second = dummy.next;
		while(second != null) {
			if(second.height != first.height) {
				res.add(new int[] {second.key, second.height});
			}
			first = first.next;
			second = second.next;
		}
		return res;
	}
	public static void main(String[] args) {
		SkyLine solution = new SkyLine();
		int[][] buildings = new int[][] {{2,9,10},{3,7,15},{5,12,12},{16,20,10},{19,24,8}};
		List<int[]> skyline = solution.getSkylineI(buildings);
		for(int[] array:skyline) {
			System.out.print(Arrays.toString(array) + " ");
		}
	
	}
	// Method 4: Binary Reduction and Divide and Conquer
	// Time: O(nlogn) Space: O(logn)
	public List<int[]> getSkyLineIV(int[][] buildings){
		if(buildings == null || buildings.length == 0 || buildings[0].length == 0) {
			return new LinkedList<int[]>();
		}
		return divideSkyLine(buildings, 0, buildings.length -1);
	}
	private LinkedList<int[]> divideSkyLine(int[][] buildings, int p, int q){
		if(p < q) {
			int mid = p + (p-q)/2;
			return merge(divideSkyLine(buildings, p, mid), divideSkyLine(buildings, mid+1, q));
		} else {
			LinkedList<int[]> rs = new LinkedList<int[]>();
			rs.add(new int[] {buildings[p][0], buildings[p][2]});
			rs.add(new int[] {buildings[p][1], 0});
			return rs;
		}
	}
	private LinkedList<int[]> merge(LinkedList<int[]> one, LinkedList<int[]> two){
		LinkedList<int[]> rs = new LinkedList<int[]>();
		int h1 = 0, h2 = 0;
		while(one.size() > 0 && two.size() > 0) {
			int x, h;
			x = h = 0;
			if(one.getFirst()[0] < two.getFirst()[0]) {
				x = one.getFirst()[0];
				h1 = one.getFirst()[1];
				h = Math.max(h1, h2);
				one.removeFirst();
			} else if(one.getFirst()[0] > two.getFirst()[0]) {
				x = one.getFirst()[0];
				h2 = two.getFirst()[1];
				h = Math.max(h1, h2);
				one.removeFirst();
				two.removeFirst();
			}
			if(rs.size() == 0 || h != rs.getLast()[1]) {
				rs.add(new int[] {x, h});
			}
		}
		rs.addAll(one);
		rs.addAll(two);
		return rs;
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