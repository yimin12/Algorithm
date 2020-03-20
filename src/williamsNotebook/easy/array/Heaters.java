/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月19日 下午11:13:35
* Description:
* 	Now, you are given positions of houses and heaters on a horizontal line,
* 	find out minimum radius of heaters so that all houses could be covered by those heaters.
* 	So, your input will be the positions of houses and heaters seperately, and your 
* 	expected output will be the minimum radius standard of heaters.
* Input: [1,2,3],[2]
* Output: 1
* Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
*/


public class Heaters {

	// Insight, we can iterate the house to find the closest heaters by using binary search
	// Time(n*logm) and Extra Space: O(1) where n is the length of houses and m is the length of heaters
	public int findRadius(int[] houses, int[] heaters) {
		// Assumptions:
		if(houses == null || heaters == null || houses.length * heaters.length == 0) return Integer.MAX_VALUE;
		Arrays.sort(heaters);
		int radius = Integer.MIN_VALUE;
		for(int house:houses) {
			// find the largest smaller value
			int index = findlargestSmaller(heaters, house);
			System.out.println(index);
			if(index == 0) {
				radius = Math.max(radius, heaters[index] - house);
				continue;
			} else if(index == -1) {
				radius = Math.max(radius, house - heaters[heaters.length - 1]);
				continue;
			}
			radius = Math.max(radius, Math.min(heaters[index] - house, house - heaters[index-1]));
		}
		return radius;
	}
	// binary search
	private int findlargestSmaller(int[] heaters, int target) {
		int left = 0, right = heaters.length - 1;
		while(left < right - 1) {
			int mid = left + (right - left) / 2;
			if(heaters[mid] >= target) {
				right = mid;
			} else {
				left = mid;
			}
		}
		if(heaters[left] >= target) {
			return left;
		} else if(heaters[right] >= target){
			return right;
		}
		return -1;
	}
	public static void main(String[] args) {
		Heaters solution = new Heaters();
		int[] heaters = {585640194,937186357}, houses = {617819336,399125485,156091745,356425228};
		int findRadius = solution.findRadius(houses, heaters);
		System.out.println(findRadius);
	}
}
