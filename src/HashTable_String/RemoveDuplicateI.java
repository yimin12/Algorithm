/**
 * 
 */
package HashTable_String;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月13日 下午10:04:02
* Description:
*/


public class RemoveDuplicateI {
//	everytime you want to do it in place, you need to convert the input to char[]
	public String deDup(String input) {
		if(input == null) {
			return null;
		}
		char[] array = input.toCharArray();
		int end = 0;
		for(int i = 0; i < array.length; i++) {
//			repeated characters will be ignored expect the first character in the sequence
			if(i==0||array[i] != array[i-1]) {
				array[end++] = array[i];
			}
		}
		return new String(array, 0, end);
	}
}
