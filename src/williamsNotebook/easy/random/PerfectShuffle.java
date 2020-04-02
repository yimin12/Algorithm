/**
 * 
 */
package williamsNotebook.easy.random;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��21�� ����10:12:27
* Description:
* 	Given an array of integers (without any duplicates), shuffle the array 
* 	such that all permutations are equally likely to be generated.
* Assumption:
* 	The given array is not null
*/

public class PerfectShuffle {
	public void shuffle(int[] array) {
//		Assumption: the array is not null
		if(array.length <= 1) {
			return;
		}
//		from right to left
//		from index to i-1, randomly pick one of the first i element
		for(int i = array.length; i >= 1; i--) {
			int idx = (int)(Math.random()*i);
			swap(array,i-1, idx);
		}
	}
	private void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
}
