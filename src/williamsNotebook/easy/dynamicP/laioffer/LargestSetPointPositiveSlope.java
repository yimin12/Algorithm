/**
 * 
 */
package williamsNotebook.easy.dynamicP.laioffer;
import java.util.Arrays;
import java.util.Comparator;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��26�� ����11:30:09
* Description:
* 	Given an array of 2D coordinates of points (all the coordinates are integers), 
* 	find the largest number of points that can form a set such that any pair of points in 
* 	the set can form a line with positive slope. Return the size of such a maximal set.
* Assumption: 
* 	The given array is not null
* 	Node: if there does not even exist 2 points can form a line with positive slope, should return 0;
* Examples:
* 	<0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum set of points are 
* 	{<0, 0>, <1, 1>, <2, 3>}, the size is 3.
*/
public class LargestSetPointPositiveSlope {
	static class Point{
		int x;
		int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public int largest(Point[] points) {
//		we need to sort the points first by y then by x, points is not null
		Arrays.sort(points, new MyComparator());
//		similar to longest ascending subsequence.
		int result = 0;
		int[] longest = new int[points.length];
		for(int i = 0; i < longest.length; i++) {
			for(int j = 0; j < i; i++) {
				if(points[j].x < points[i].x && points[j].y < points[i].y) {
					longest[i] = Math.max(longest[i], longest[j]);
				}
			}
			longest[i]++;
			result = Math.max(result, longest[i]);
		}
		return result == 1 ? 0 :result;
	}
	
//	this comparator will sort the points by y first then by x
	static class MyComparator implements Comparator<Point>{
		@Override
		public int compare(Point p1, Point p2) {
			if(p1.y < p2.y) {
				return -1;
			} else if (p1.y > p2.y) {
				return 1;
			} else if (p1.x < p2.x) {
				return -1;
			} else if (p1.x > p2.x) {
				return 1;
			}
			return 0;
		}
	}
}
