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
* @version Create Time��2019��10��22�� ����5:42:07
* Description:
* 	Find all pairs of elements in a given array that sum to the given 
* 	target number. Return all the pairs of indices.
* Assumption:
* 	The given array is not null and has length of at least 2
* Examples:
* 	A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]
* 	A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]
*/
public class TwoSumAllPairsI {
	public List<List<Integer>> allPairs(int[] array, int target){
//		Assumption: array is not null
		List<List<Integer>> list = new ArrayList<List<Integer>>();
//		key: number, value: list of all possible indices
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
//		The following step is treating the duplicate problem of array, we should return all situations
		for(int i = 0; i < array.length; i++) {
			List<Integer> indices = map.get(target-array[i]);
//			if target - array[i] is in the map, we can all pair(j, i) with i as the larger index
			if(indices != null) {
				for(int j : indices) {
					list.add(Arrays.asList(j, i));
				}
			}
//			add current index i to all the possible indices for value of array[i]
			if(!map.containsKey(array[i])) {
				map.put(array[i], new ArrayList<Integer>());
			}
			map.get(array[i]).add(i);
		}
		return list;
	}
}
