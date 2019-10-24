/**
 * 
 */
package BitOperation;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月17日 下午1:23:19
* Description:
* 	Determine if a given integer is power of 2.
* Examples:
	16 is power of 2 (2 ^ 4)
	3 is not
	0 is not
	-1 is not
*/

public class PowerOfTwo {

//	Method 1
	public boolean isPowerOfTwoI(int number) {
		if(number<=0) {
			return false;
		}
//		ignore all the trailing 0's, 1&1 = 1; 1 & 0 = 0
		while((number & 1) == 0) {
			number >>>= 1;
		}
		return number == 1;
	}
//	Method 2 ： 
	public boolean isPowerOfTwoII(int num) {
		if(num <= 0) {
			return false;
		}
		int count = 0;
		while(num > 0) {
			count += num & 1;
			num >>>= 1;
		}
//		for the number of power of 2, there should be only one 1
		return count == 1;
	}
//	Method 3:
	public boolean isPowerOfTwoIII(int num) {
//		use the trick of number & (num - 1) will remove the rightmost 1.
//		if num is power of 2 e.g. 0010000 then num - 1 = 000 1111 so (num & (num - 1)) == 0;
		return num > 0 && (num & (num - 1)) == 0;
	}
}
