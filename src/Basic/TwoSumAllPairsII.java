/**
 * 
 */
package Basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��22�� ����5:53:35
* Description:
* 	Find all pairs of elements in a given array that sum to the pair the given 
* 	target number. Return all the distinct pairs of values.
* Assumptions:
* 	The given array is not null and has length of at least 2
* 	The order of the values in the pair does not matter
* Examples:
* 	A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]
*/
// Compare to the I, this is not to return the indices, but to return exact combo of two pairs 
public class TwoSumAllPairsII {
//	Method 1: sort the array first and use two pointers
	public List<List<Integer>> allPairs(int[] array, int target){
//		Assumptions�� array is not null, array.length >=2
		Arrays.sort(array);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int left = 0;
		int right = array.length-1;
		while(left<right) {
//			we should ignore all the consective duplicate values when we want to determine the
//			smaller element of the pair
			if(left > 0 && array[left] == array[left-1]) {
				left++;
				continue;
			}
			int cur = array[left]+array[right];
			if(cur == target) {
				result.add(Arrays.asList(array[left], array[right]));
				left++;
				right--;
			} else if(cur < target) {
				left++;
			} else {
				right--;
			}
		}
		return result;
	}
//	Method 2: use HashSet
	public List<List<Integer>> allPairsII(int[] array, int target){
//		Assumption: array is not null, array.length >=2 
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int num: array) {
//			Two cases when we need to make the pair a solution:
//				1: if 2 * x = target, we need to make sure there is no duplicates
//			    2: if x + y = target, and this is the first time both x and y are present.
			Integer count = map.get(num);
			if(num * 2 == target && count != null && count == 1) {
				result.add(Arrays.asList(num, num));
//				we need to maintain the distinct pairs as requirement, so we need to ensure count == null
			} else if (map.containsKey(target - num) && count == null) {
				result.add(Arrays.asList(target-num, num));
			}
			if(count == null) {
				map.put(num, 1);
			} else {
				map.put(num, count+1);
			}
		}
		return result;
	}
}
