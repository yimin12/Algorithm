/**
 * 
 */
package williamsNotebook.easy.bit;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��17�� ����2:18:06
* Description:
* 	Determine if the characters of a given string are all unique.
* Assumptions
	We are using ASCII charset, the value of valid characters are from 0 to 255
	The given string is not null
  Examples:
  	all the characters in "abA+\8" are unique
	"abA+\a88" contains duplicate characters
*/

public class AllUniqueCharsII {
	public boolean allUnique(String word) {
//		each int value can represent 32 bit, so we need 8 ints to represent 256 bit. (An int contains 4 bytes or 32 bits)
		int[] vec = new int[8];
		char[] array = word.toCharArray();
		for(char c : array) {
//			for a value c in the range of 0-255, it is actually mapped to the int value at c/32 as index,
//			and the c % 32'th bit of the int value
			if((vec[c/32] >>> (c % 32)&1) != 0) {
				return false;
			}
			vec[c/32] |= 1 <<(c % 32);
		}
		return true;
	}
}
