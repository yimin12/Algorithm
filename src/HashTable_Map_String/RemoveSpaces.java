/**
 * 
 */
package HashTable_Map_String;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月13日 下午7:12:15
* Description:
* 	Given a string, remove all leading/trailing/duplicated empty spaces.
* Assumptions:
	The given string is not null.
  Examples:
	“  a” --> “a”
	“   I     love MTV ” --> “I love MTV”
*/

/**
 * @author 61771
 *
 */
public class RemoveSpaces {
//	Assumption:
	public String removeSpace(String input) {
		if(input.isEmpty()) {
			return input;
		}
		char[] array = input.toCharArray();
		int end = 0;
		for(int i = 0; i < array.length; i++) {
//			when we would ignore the current space character:
//			1. we ignore all the space characters followed by another space character,
//			so that if there are several consecutive space characters, only the first one will be remained
//			2. we ignore also the space character if it is the first character of the input string
			if(array[i] == ' ' && (i==0 || array[i-1] == ' ') ) {
				continue;
//				ignore space and keep moving
			}
			array[end++] = array[i];
//			the right part of end is the solution
//			post processing: it is possible we still have one trailing space character at the end;
			if (end > 0 && array[end - 1] == ' ') {
				return new String(array, 0, end - 1); 
			}
		}
		return new String(array, 0, end);
	}
}
