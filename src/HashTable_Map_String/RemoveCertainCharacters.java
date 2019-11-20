/**
 * 
 */
package HashTable_Map_String;

import java.util.HashSet;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月13日 下午6:14:12
* Description:
* 	Remove given characters in input string, the relative order of other characters should be remained. 
* 	Return the new string after deletion.
* 
* Assumptions:
	The given input string is not null.
	The characters to be removed is given by another string, it is guaranteed to be not null.
  Examples:
	input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd".
*/

public class RemoveCertainCharacters {
//	Assumption: input and t are not null
	public String remove(String input, String t) {
//	we should solve this problem with inplace way, usually we can convert the immutable String to Char[]
		char[] array = input.toCharArray();
//		Get set of all distinct characters in t, so that loop up operation will be efficient
		Set<Character> set = buildSet(t);
//		slow:[0, slow) contains valid result
//		fast:[fast, array.length) is area to explore
		int slow = 0;
		for(int fast = 0; fast < array.length; fast++) {
//			the exploring character can only be put into valid area if it is not in the set
			if(!set.contains(array[fast])) {
				array[slow++] = array[fast];
			}
		}
//		convert the char[] subarray back to string Object
		return new String(array, 0, slow);
	}
	private Set<Character> buildSet(String t){
		Set<Character> set = new HashSet<Character>();
		for(int i = 0; i < t.length();i++) {
			set.add(t.charAt(i));
		}
		return set;
	}
}
