/**
 * 
 */
package williamsNotebook.common.classified;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月8日 下午3:27:58
* Description:
* 	Design and implement a TwoSum class. It should support the following operations:addandfind.
* 	add- Add the number to an internal data structure.
* 	find- Find if there exists any pair of numbers which sum is equal to the value.
* Example:
* 	add(1); add(3); add(5);
	find(4) -> true
	find(7) -> false
*/
public class TwoSumQuery {

	// Use HashMap: Time: O(n) find, O(1) add, Space : O(n)
	private HashMap<Integer, Integer> map;
	public TwoSumQuery() {
		map = new HashMap<Integer, Integer>();
		
	}
	public void add(int number) {
		map.put(number, map.getOrDefault(number, 0) + 1);
	}
	public boolean find(int val) {
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int num1 = entry.getKey();
			int num2 = val - num1;
			// Consider the duplicate problem
			if((num1 == num2 && map.get(num2) > 1) || (num1 != num2 && map.containsKey(num2))) {
				return true;
			}
		}
		return false;
	}	
}
class TwoSumQueryII{
	// key: the value in given input, value: whether it has multiple duplicate
	Map<Integer, Boolean> map;
	// store distinct value of the given input
	List<Integer> list;
	int low = Integer.MAX_VALUE;
	int high = Integer.MIN_VALUE;
	public TwoSumQueryII() {
		map = new HashMap<Integer, Boolean>();
		list = new LinkedList<Integer>();
	}
	public void add(int number) {
		if(map.containsKey(number)) {
			map.put(number, true);
		} else {
			map.put(number, false);
			list.add(number);
			low = Math.min(low, number);
			high = Math.max(high, number);
		}
	}
	public boolean find(int value) {
		if(value < 2 * low || value > 2 * high) return false;
		for(int num : list) {
			int target = value - num;
			if(map.containsKey(target)) {
				if(num != target) return true;
				else if(map.get(target)) return true;
			}
		}
		return false;
	}
}
