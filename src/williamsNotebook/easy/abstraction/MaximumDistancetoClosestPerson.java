/**
 * 
 */
package williamsNotebook.easy.abstraction;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月22日 下午9:39:50
* Description:
* 	In a row ofseats,1represents a person sitting in that seat, and0represents that the seat is empty.
* 	There is at least one empty seat, and at least one person sitting.
* 	Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
* 	Return that maximum distance to closest person.
*/
public class MaximumDistancetoClosestPerson {
	
	
	// Method 1: Common way to iterate the whole seats, traverse by two pointers
	// Time: O(n) Space: O(1);
	public int maxDistToClosest(int[] seats) {
		if(seats == null || seats.length <= 1) return 0; 
		int left = -1;
		int maxDist = 0;
		for(int i = 0; i < seats.length; i++) {
			if(seats[i] == 0) {
				continue;
			}
			if(left < 0) {
				maxDist = Math.max(maxDist, i);
			} else {
				maxDist = Math.max(maxDist, (i-left)/2);
			}
			left = i;
		}
		// post processing: when the right end side is empty
		if(seats[seats.length - 1] == 0) {
			maxDist = Math.max(maxDist, seats.length - 1 - left);
		}
		return maxDist;
	}
	// Method 2: Another solution of two pointers
	// Time: O(n) Space: O(1);
	public int maxDistToClosestII(int[] seats) {
		int prev = -1, next = 0;
		int n = seats.length;
		int res = 0;
		for(int i = 0; i < n; i++) {
			if(seats[i] == 1) {
				prev = i;
			} else {
				while(next < n && seats[next] == 0 || next < i){
					next++;
				}
				// small trick, if the left is  -1 and right is n, just return seats.length for result
				int left = prev == -1 ? n : i - prev;
				int right = next == n ? n : next - i;
				res = Math.max(res, Math.min(left, right));
			}
		}
		return res;
	}
}
