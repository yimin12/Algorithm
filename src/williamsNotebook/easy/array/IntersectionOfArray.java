/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.HashMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月21日 上午12:24:33
* Description:
* 	Given two arrays, write a function to compute their intersection.
* 	Each element in the result must be unique. The result can be in any order.
* 	Example Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
*/
public class IntersectionOfArray {

	// Method 1: HashMap Approach
	public int[] intersection(int[] nums1, int[] nums2) {
		HashMap<Integer, Boolean> map1 = new HashMap<Integer, Boolean>();
		HashMap<Integer, Boolean> intersectMap = new HashMap<Integer, Boolean>();
		for(int i = 0; i < nums1.length; i++) {
			if(!map1.containsKey(nums1[i])) {
				map1.put(nums1[i],true);
			}
		}
		for(int j = 0; j < nums2.length; j++) {
			if(map1.containsKey(nums2[j]) && !intersectMap.containsKey(nums2[j])) {
				intersectMap.put(nums2[j], true);
			}
		}
		int[] result = new int[intersectMap.size()];
		int i = 0;
		for(Integer e : intersectMap.keySet()) {
			result[i] = e;
			i++;
		}
		return result;
	}
}
