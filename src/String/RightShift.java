/**
 * 
 */
package String;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月15日 上午12:18:56
* Description:
* 	Right shift a given string by n characters
* Assumptions:	
	The given string is not null.
	n >= 0.
  Examples:
	"abc", 4 -> "cab"
*/
public class RightShift {
//	Assumption: input is not null, n > 0
	public String rightShift(String input, int n) {
		if(input.length() <= 1) {
			return input;
		}
		char[] array = input.toCharArray();
//		determine how many slots it need to switch exactly
		n %= array.length;
		reverse(array, array.length - n, array.length - 1);
		reverse(array, 0, array.length - n - 1);
		reverse(array, 0, array.length - 1);
		return new String(array);
	}
	private void reverse(char[] array, int left, int right) {
		while(left < right) {
			char temp = array[left];
			array[left] = array[right];
			array[right] = temp;
			left++;
			right--;
		}
	}
}
