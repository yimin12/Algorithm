/**
 * 
 */
package Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月22日 下午6:41:46
* Description:
* 	Determine if there exists three elements in a given array that sum to the given target number. 
* 	Return all the triple of values that sums to target.
* Assumption:
* 	The given array is not null and has length of at least 3
* 	No duplicate triples should be 
*/

public class ThreeSum {
	public List<List<Integer>> allTriples(int[] array, int target){
//		Assumption: array is not null and array.length >= 3
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for(int i = 0; i < array.length - 2; i++) {
//			Our goal is to find i < j < k, such that array[i] + array[j] + array[k] = target.
//			To make sure there is no duplicate tuple, we ignore all the duplicate possible i
//			e.g. if we have 2, 2, 2, only first 2 will be selected as i
			if(i > 0 && array[i] == array[i-1]) {
				continue;
			}
			int left = i+1;
			int right = array.length - 1;
			while(left < right) {
				int temp = array[left] + array[right];
				if(temp + array[i] == target) {
					result.add(Arrays.asList(array[i], array[left], array[right]));
					left++;
//					ignore all possible duplicate j as well
					while(left < right && array[left] == array[left - 1]) {
						left++;
					}
				} else if (temp + array[i] < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		return result;
	}
}
