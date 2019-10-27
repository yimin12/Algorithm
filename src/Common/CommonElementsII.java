/**
 * 
 */
package Common;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月24日 上午9:05:38
* Description:
* 	Find all common elements in 3 sorted arrays.
* Assumptions:
* 	The 3 given sorted arrays are not null
* 	THere could be duplicate elements in each of the arrays
* Examples:
* 	A = {1, 2, 2, 3}, B = {2, 2, 3, 5}, C = {2, 2, 4}, the common elements are [2, 2]	
*/
public class CommonElementsII {
	public List<Integer> common(int[] a, int[] b, int[] c){
		List<Integer> result = new ArrayList<Integer>();
		int ai = 0;
		int bi = 0;
		int ci = 0;
		while(ai < a.length && bi < b.length && ci < c.length) {
			if(a[ai] == b[bi] && b[bi] == c[ci]) {
				result.add(a[ai]);
				ai++;
				bi++;
				ci++;
			} else if(a[ai] <= b[bi] && a[ai] <= c[ci]) {
				ai++;
			} else if(b[bi] <= a[ai] && b[bi] <= c[ci]) {
				bi++;
			} else {
				ci++;
			}
		}
		return result;
	}
}
