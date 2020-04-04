/**
 * 
 */
package williamsNotebook.easy.math;

import java.util.HashSet;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 下午3:56:03
* Description:
* 	A happy number is a number defined by the following process: Starting with any positive integer, replace the number by 
* 	the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it 
* 	loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
* Example:	
* 	Input: 19  Output: true
* Explanation: 
* 	1^2 + 9^2 = 82
	8^2 + 2^2 = 68
	6^2 + 8^2 = 100
	1^2 + 0^2 + 0^2 = 1
*/

public class HappyNumber {

	// Small trick of how to get out of dead loop
	public boolean isHappy(int n) {
		long t = n;
		Set<Long> seen = new HashSet<Long>();
		// small trick implement here, if the number has been generating before, it will cause infinite loop.
		// So we use HashSet to record the adding number;
		while(seen.add(t)) {
			t = process(t);
			if(t == 1) return true;
		}
		return false;
	}
	private long process(long n) {
		long res = 0;
		while(n > 0) {
			long rem = n % 10;
			res += rem * rem;
			n /= 10;
		}
		return res;
	}
}
