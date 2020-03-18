/**
 * 
 */
package Basic;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月28日 上午11:48:28
* Description:
* 	Given a 32-bit signed integer, reverse digits of an integer.
* Assumptions:
* 	Assume we are dealing with an environment which could only store integers within the 32-bit signed 
* 	integer range: [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your function 
* 	returns 0 when the reversed integer overflows.
* Examples:
	Input: 123
	Output: 321
	
	Input: -123
	Output: -321
	
	Input: 120
	Output: 21
*/

public class ReverseInteger {
	public int reverse(int n) {
//		dealing the corner case
		if(n > Integer.MAX_VALUE || n < Integer.MIN_VALUE) return 0;
		int sign = n < 0 ? -1:1;
		n = Math.abs(n);
		int result = 0;
		while(n > 0) {
			int tail = n % 10;
			result = result * 10 + tail;
			n /= 10;
		}
		return sign*result;
	}
}
