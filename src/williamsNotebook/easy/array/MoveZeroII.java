/**
 * 
 */
package williamsNotebook.easy.array;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��22�� ����4:26:36
* Description:
* 	Given an array of integers, move all the 0s to the right end of the array.
	The relative order of the elements in the original
  Assumption:
  	The given array is not null
  Examples:
  	{1} --> {1}
  	{1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0}
*/
public class MoveZeroII {
//	Assumption: the array is not null
	public int[] moveZero(int[] array) {
		if(array.length <= 1) return array;
		int end = 0;
//		move all the non-zero value forward one by one, then set the rest of value zero
		for(int i = 0; i < array.length; i++) {
			if(array[i] != 0) {
				array[end++] = array[i];
			}
		}
//		fill in the right part with 0
		for(int i = end; i < array.length; i++) {
			array[i] = 0;
		}
		return array;
	}
}
