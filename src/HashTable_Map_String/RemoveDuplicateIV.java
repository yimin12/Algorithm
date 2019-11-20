/**
 * 
 */
package HashTable_Map_String;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月13日 下午10:09:03
* Description:
* 	Repeatedly remove all adjacent, repeated characters in a given string from left to right.
	No adjacent characters should be identified in the final string.
  Examples:
	"abbbaaccz" → "aaaccz" → "ccz" → "z"
	"aabccdc" → "bccdc" → "bdc"
*/

public class RemoveDuplicateIV {
	public String deDup(String input) {
		if(input == null || input.length() <= 1) {
			return input;
		}
		char[] array = input.toCharArray();
//		instead of using extra stack explicitly, we can actually reuse the left side of the original char[]
//		as "stack". end: is where the top of the stack is.
		int end = 0;
		for(int i = 1; i < array.length; i++) {
// 			if the stack is empty(when end == 1) or there is no duplicate chars
//			we are able to push the character into the stack
			if(end == -1 || array[i] != array[end]) {
				array[++end] = array[i];
			} else {
//				otherwise, we need to pop the top element by end--;
				end--;
				while(i + 1 < array.length && array[i] == array[i+1]) {
					i++;
				}
			}
		}
		return new String(array, 0, end + 1);
	}
}
