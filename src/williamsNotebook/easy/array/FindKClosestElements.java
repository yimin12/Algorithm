/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月19日 下午6:01:00
* Description:
* 	Given a sorted array, two integerskandx, find thekclosest elements toxin the array. 
* 	The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
*/

public class FindKClosestElements {

	// Input: [1,2,3,4,5], k=4, x=3
	// Output: [1,2,3,4]
	public List<Integer> kClosest(List<Integer> list, int target, int k){
		List<Integer> res = new ArrayList<Integer>();
		if(list == null || list.size() == 0 || k <= 0) return list;
		if(list.size() < k) return list;
		// step 1: find the largest smaller or equal element;
		int left = largestSamllerEqual(list, target);
		int right = left + 1;
		for(int i = 0; i < k; i++) {
			if(right >= list.size() || left >= 0 && Math.abs(list.get(left) - target) < Math.abs(list.get(right) - target)) {
				res.add(list.get(left));
				left--;
			} else {
				res.add(list.get(right));
				right++;
			}
		}
		return res;
	}
	private int largestSamllerEqual(List<Integer> list, int target) {
		int left = 0, right = list.size() - 1;
		while(left < right - 1) {
			int mid = left + (right - left)/2;
			if(list.get(mid) <= target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		if(list.get(right) <= target) {
			return right;
		} else if(list.get(left) <= target) {
			return left;
		}
		// if nothing find
		return -1;
		
	}
	public static void main(String[] args) {
		FindKClosestElements solution = new FindKClosestElements();
		Integer[] array = new Integer[] {1,2,3,4,5,6,7};
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(array));
		List<Integer> findKClosest = solution.kClosest(list, -1, 8);
		System.out.println(findKClosest.toString());
	}
}
