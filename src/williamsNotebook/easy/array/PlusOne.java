/**
 * 
 */
package williamsNotebook.easy.array;

/**
 * @author yimin Huang
 * 
 *		Given a non-negative number represented as an array of digits, plus one to the number.
 *		The digits are stored such that the most significant digit is at the head of the list.
 *
 * Algorithm Class
 */
public class PlusOne {

	public int[] plusOne(int[] digit) {
		// sanity check
		if(digit == null || digit.length == 0) return digit;
		for (int i = digit.length - 1; i >= 0; i--) {
			if(digit[i] + 1 < 10) {
				digit[i]++;
				return digit;
			} else {
				if(i == 0) {
					int[] newDigit = new int[digit.length + 1];
					newDigit[0] = 1;
					return newDigit;
				} else {
					digit[i] = 0;
				}
			}
		}
		return digit;
	}
}
