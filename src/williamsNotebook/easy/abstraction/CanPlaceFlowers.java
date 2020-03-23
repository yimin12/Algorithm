/**
 * 
 */
package williamsNotebook.easy.abstraction;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月23日 下午12:53:42
* Description:
* 	Suppose you have a long flower bed in which some of the plots are planted and some are not.
*   However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.
*   Given a flower bed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), 
*   and a number n, return if new flowers can be planted in it without violating the no-adjacent-flowers rule
* Input:							Output:
* 	flowerbed = [1,0,0,0,1], n = 1  		 True
*/

public class CanPlaceFlowers {
	// One Pass and linear Scan
	// O(n) time, O(1) space
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		for(int i = 0; i < flowerbed.length; i++) {
			if(flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length - 1 || flowerbed[i+1] == 0)){
				flowerbed[i] = 1;
				n--;
			}
			if(n <= 0) return true;
		}
		return false;
	}
}
