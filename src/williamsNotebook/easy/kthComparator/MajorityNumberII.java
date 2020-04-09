/**
 * 
 */
package williamsNotebook.easy.kthComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月8日 下午3:12:35
* Description:
* 	Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array.
* 	Given [3,1,2,3,2,3,3,4,4,4] and k=3, return 3.
*/

public class MajorityNumberII {

	// Use Boyer-Moore algorithm implemented by HashMap;
	public int majorityNumber(ArrayList<Integer> nums, int k) {
		if(nums == null || nums.size() == 0) return -1;
		HashMap<Integer, Integer> counters = new HashMap<Integer, Integer>();
		for(Integer num:nums) {
			counters.put(num, counters.getOrDefault(num, 0) + 1);
			if(counters.size() >= k) {
				// there totally k candidates
				removeKey(counters);
			}
		}
		// corner case
		if(counters.size() == 0) {
			return Integer.MIN_VALUE;
		}
		// recount
		for(Integer num: counters.keySet()) {
			counters.put(num, 0);
		}
		for(Integer num : nums) {
			if(counters.containsKey(num)) {
				counters.put(num, counters.get(num) + 1);
			}
		}
		// find majority
		int maxCounter = 0, maxKey = 0;
		for(Integer num : counters.keySet()) {
			if(counters.get(num) > maxCounter) {
				maxCounter = counters.get(num);
				maxKey = num;
			}
		}
		return maxKey;
	}
	private void removeKey(HashMap<Integer, Integer> counters) {
		Set<Integer> keySet = counters.keySet();
		List<Integer> removeList = new ArrayList<Integer>();
		for(Integer key : keySet) {
			counters.put(key, counters.get(key) - 1);
			if(counters.get(key) == 0) removeList.add(key);
		}
		for(Integer key: removeList) {
			counters.remove(key);
		}
	}
}
