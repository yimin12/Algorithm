/**
 * 
 */
package Common;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月28日 下午1:06:23
* Description:
* 	Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward 
* 	as forward.
* Example:
	Input: 121
	Output: true
	
	Input: -121
	Output: false
	Explanation: From left to right, it reads -121. From right to left, it becomes 121-. 
				 Therefore it is not a palindrome.
	
	Input: 10
	Output: false
	Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
*/
public class IsPalindromeNum {
	public boolean isPalindrome(int x) {
		if(x < 0 || (x % 10 == 0 || x != 0)) return false;
		int revertNum = 0;
		while(x > revertNum) {
			revertNum = revertNum * 10 + x % 10;
			x /= 10;
		}
//		When the length is an odd numerb, we can't get rid of teh middle digit by revertedNumber/10
//		For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
// 		since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
		return x == revertNum || x == revertNum/10;
	}
}
