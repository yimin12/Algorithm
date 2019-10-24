/**
 * 
 */
package BitOperation;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月17日 下午2:04:55
* Description:
* 	Determine the number of bits that are different for two given integers.
  Examples:
	5(“0101”) and 8(“1000”) has 3 different bits
*/

public class NumberOfDiffBits {
	public int diffBits(int a, int b) {
//		after exclusive or, only the bits where a and are different will be 1
		a ^= b;
		int count = 0;
//		In java, we used logical right shift >>>. and a != 0 as the terminate condition
		while(a != 0) {
			count += a & 1;
			a >>>= 1;
		}
		return count;
	}
}
