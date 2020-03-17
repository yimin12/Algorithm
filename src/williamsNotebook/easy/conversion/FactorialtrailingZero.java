/**
 * 
 */
package williamsNotebook.easy.conversion;

/**
 * @author yimin Huang
 *	
 *		Given an integer n, return the number of trailing zeroes in n!.
 *		Your solution should be in logarithmic time complexity.
 *
 * Algorithm Class
 */
public class FactorialtrailingZero {

	public int traillingZero(int n) {
		int count = 0;
		while(n > 0) {
			n = n / 5;
			count += n;
		}
		return count;
	}
}
