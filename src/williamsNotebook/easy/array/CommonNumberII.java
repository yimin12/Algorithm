/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��13�� ����3:27:14
* Description:
* 
* Assumption: There is no duplicated elements in the given arrays
*/

public class CommonNumberII {
//	Method 1.two pointers
	public List<Integer> commonI(int[] a, int[] b){
//		Assume that a, b is not null
		List<Integer> common = new ArrayList<Integer>();
		int i = 0;
		int j = 0;
		while(i < a.length && j < b.length) {
			if(a[i] == b[j]) {
				common.add(a[i]);
				i++;
				j++;
			} else if(a[i] < b[j]) {
				i++;
			} else {
				j++;
			}
		}
		return common;
	}
//	Method 2. HashMap
	public List<Integer> commonII(int[] a, int[] b){
		List<Integer> common = new ArrayList<Integer>();
		HashMap<Integer, Integer> countA = new HashMap<Integer, Integer>();
		for(int num : a) {
			Integer ct = countA.get(num);
			if(ct!=null) {
				countA.put(num, ct+1);
			} else {
				countA.put(num, 1);
			}
		}
		HashMap<Integer, Integer> countB = new HashMap<Integer, Integer>();
		for(int num : b) {
			Integer ct = countA.get(num);
			if(ct!=null) {
				countA.put(num, ct+1);
			} else {
				countA.put(num, 1);
			}
		}
		for (Map.Entry<Integer, Integer> entry:countA.entrySet()) {
			Integer ctInB = countB.get(entry.getKey());
			if(ctInB != null) {
				int appear = Math.min(entry.getValue(), ctInB);
				for(int i = 0; i < appear; i++) {
					common.add(entry.getKey());
				}
			}
		}
		return common;
	}
}
