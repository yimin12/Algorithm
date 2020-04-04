/**
 * 
 */
package williamsNotebook.easy.math;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 下午6:55:42
* Description:
* 	Given a 32-bit signed integer, reverse digits of an integer.
* 	Input: 123  Output: 321
* Assumption:
* 	Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: 
* 	[−2^31, 2^31 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer 
* 	overflows.
*/

public class ReverseInteger {

	// This problem focus on the overflow problem
	public int reverse(int x) {
		int rev = 0;
		while(x != 0) {
			int pop = x % 10;
			// before we calculate rev * 10, we need to guarantee there is no overflow problem
			if(rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
			if(rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
			rev = rev *10 + pop;
		}
		return rev;
	}
	// another method to handle the overflow problem
	public int reverseI(int x) {
		int rev = 0;
		while(x != 0) {
			int tail = x % 10;
			int temp = rev * 10 + tail;
			// validate whether is has overflowed
			if((temp - tail) / 10 != rev) return 0;
			rev = temp;
			x /= 10;
		}
		return rev;
	}
}
