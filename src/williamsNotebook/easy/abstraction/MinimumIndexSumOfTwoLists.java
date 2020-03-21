/**
 * 
 */
package williamsNotebook.easy.abstraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月20日 下午3:10:38
* Description:
* 	Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
* 	You need to help them find out theircommon interestwith theleast list index sum. If there is a choice tie between answers, output all 
* 	of them with no order requirement. You could assume there always exists an answer.
*
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
Output:
 ["Shogun"]
Explanation:
 The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
* 	
*/
public class MinimumIndexSumOfTwoLists {

	public String[] findResturant(String[] str1, String[] str2) {
		// Assumption: the given strings are not null
		if(str1.length == 0 && str2.length == 0) return new String[0];
		if(str1.length == 0 || str2.length == 0) return str1.length == 0 ? str2 : str1;
		if(str2.length > str1.length) return findResturant(str2, str1);
		List<String> res = new ArrayList<String>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int minIndex = Integer.MAX_VALUE;
		for(int i = 0; i < str1.length; i++) {
			if(!map.containsKey(str1[i])) {
				map.put(str1[i], i);
			}
		}
		for(int i = 0; i < str2.length; i++) {
			if(map.containsKey(str2[i])) {
				int temp = map.get(str2[i]) + i;
				if(minIndex > temp) {
					minIndex = temp;
					res.clear();
					res.add(str2[i]);
				} else if(temp == minIndex) {
					res.add(str2[i]);
				}
			}
		}
		return res.toArray(new String[res.size()]);
	}
	public static void main(String[] args) {
		MinimumIndexSumOfTwoLists solution = new MinimumIndexSumOfTwoLists();
		String[] str1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
		String[] str2 = {"KFC", "Shogun", "Burger King"};
		String[] findResturant = solution.findResturant(str1, str2);
		System.out.println(Arrays.toString(findResturant));
 	}
}
