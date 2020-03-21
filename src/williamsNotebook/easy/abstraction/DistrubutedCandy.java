/**
 * 
 */
package williamsNotebook.easy.abstraction;

import java.util.HashSet;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月20日 下午4:11:57
* Description:
* 	Given an integer array with even length, where different numbers in this array represent different kinds of candies. 
* 	Each number means one candy of the corresponding kind. You need to distribute these candies equally in number 
* 	to brother and sister. Return the maximum number of kinds of candies the sister could gain.
*/
public class DistrubutedCandy {

	// Iterate the whole elements
	// Time: O(n)  Space: O(n) worst case
	public int distribute(int[] candies) {
		HashSet<Integer> set = new HashSet<Integer>();
		for(int candy : candies) {
			set.add(candy);
		}
		return set.size() < candies.length / 2 ? set.size() : candies.length/2;
	}
}
