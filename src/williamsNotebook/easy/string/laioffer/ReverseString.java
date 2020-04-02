/**
 * 
 */
package williamsNotebook.easy.string.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��14�� ����10:35:12
* Description:
* 	Reverse a given string.
* Assumptions:
	The given string is not null.
*/

public class ReverseString {
//	Method 1: iterative reverse
	public String reverse(String input) {
		if(input == null || input.length() <= 1) {
			return input;
		}
		char[] array = input.toCharArray();
		for(int left = 0, right = array.length - 1; left < right; left++, right--) {
			swap(array, left, right);
		}
		return new String(array);
	}
	
//	Method 2: recursive reverse
	public String reverseRecursive(String input) {
		if(input == null || input.length() <= 1) {
			return input;
		}
		char[] array = input.toCharArray();
		reverseHelper(array, 0, array.length - 1);
		return new String(array);
	}
	private void reverseHelper(char[] array, int left, int right) {
		if(left >= right) {
			return;
		}
		swap(array, left, right);
		reverseHelper(array, left++, right--);
	}
	private void swap(char[] array, int left, int right) {
		char temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
}
