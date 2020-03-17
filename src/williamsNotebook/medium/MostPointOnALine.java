/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author yimin Huang
 *
 *		Given n points on a 2D plane, find the maximum number of points that lie on the same straight line
 *	
 * Algorithm Class
 */
public class MostPointOnALine {

	static class Point{
		int x, y;
		Point(){x = 0; y = 0;}
		Point(int x, int y){this.x = x; this.y = y;}
	}
	public int maxPoints(Point[] points) {
		int result = 0;
		for(int i = 0; i < points.length; i++) {
			// any line can be represented by a point and a slope. we take point as seed and try to find all possible slopes
			Point seed = points[i];
			// record the points with same x,y
			int same = 1;
			// record the points with same x, for the vertical situations
			int sameX = 0;
			// record the maximum numbers of points on the same line crossing the seed point
			int most = 0;
			HashMap<Double, Integer> cnt = new HashMap<Double, Integer>();
			for(int j = 0; j < points.length; j++) {
				if(i == j) continue;
				Point tmp = points[j];
				if(tmp.x == seed.x && tmp.y == seed.y) same++;
				else if(tmp.x == seed.x) sameX++; // handle vertical situation
				else {
					double slope = ((tmp.y - seed.y) + 0.0) / (tmp.x - seed.x);
					int count = cnt.getOrDefault(slope, 0);
					cnt.put(slope, count+1);
					most = Math.max(most, count+1);
				}
			}
			// compare the vertical situation and the validated slope situation
			most = Math.max(most, sameX) + same;
			result = Math.max(result, most);
		}
		return result;
	}
	
	// Follow Up 2: Given an array of 2D coordinate of points (all the coordinates integers), find the largest number of points that can form a set such that 
	// any pair of points in the set can form a line with positive slope. Return the size of such a maximal set.
	public int largest(Point[] points) {
		// Assumptions: points is not null.
		// we need to sort the points first by y then by x.
		Arrays.sort(points, new MyComparator());
		// similar to longest ascending subsequence
		int result = 0;
		int[] longest = new int[points.length];
		for(int i = 0; i < longest.length; i++) {
			for(int j = 0; j < i; j++) {
				if(points[j].x < points[i].x && points[j].y < points[i].y) {
					longest[i] = Math.max(longest[i], longest[j]);
				}
			}
			longest[i]++;
			result = Math.max(result, longest[i]);
		}
		return result == 1 ? 0 : result;
	}
	
	static class MyComparator implements Comparator<Point>{
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.y < o2.y) {
				return -1;
			} else if(o1.y > o2.y) {
				return 1;
			} else if(o1.x < o2.x) {
				return -1;
			} else if(o1.x > o2.x) {
				return 1;
			}
			return 0;
		}
	}
}
