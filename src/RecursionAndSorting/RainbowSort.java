/**
 * 
 */
package RecursionAndSorting;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月8日 下午10:40:55
* Description: 
* 	we have three colors denoted as -1, 0, 1 and all the elements in the array are valid
* 
*/

/**
 * @author 61771
 *
 */
public class RainbowSort {
	public int[] rainbowSort(int[] array) {
		if(array == null || array.length <= 1)return array;
//		There are three bounds
//		1.the left side of neg is -1(exclusive of neg)
//		2.the right side of one is 1(exclusive of one)
//		3.the part between neg and zero is zero(exclusive of zero)
//		4.between zero and one is to be discovered(inclusive of both)
		int neg = 0;
		int one = array.length - 1;
		int zero = 0;
		while(zero <= one) {
			if(array[zero] == -1) {
				swap(array, neg++, zero++);
			} else if(array[zero] == 0) {
				zero++;
			} else {
				// can not zero++, otherwise you have not determine where array[zero] should be placed
				swap(array, zero, one--);
			}
		}
		return array;
		
	}
	private void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
}
