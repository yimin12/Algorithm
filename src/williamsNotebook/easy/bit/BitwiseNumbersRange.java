/**
 * 
 */
package williamsNotebook.easy.bit;

/**
 * @author yimin Huang
 *	
 *	Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive
 *	For example, given the range [5, 7], you should return 4. 
 *		Explaination: 101 && 110 && 111 = 100 = 4
 * Algorithm Class
 * 
 */
public class BitwiseNumbersRange {
	
	// Linear traversal.
	// step 1: find the highest digit that is different (m^n)
	// step 2: mark all the digit as 1 after that highest different digit, and reverse
	// step 3: m & n & step2's result
	// key insight: within the range, all and and operation will return 0;
	public int rangeBitwiseAnd(int m, int n) {
		int x = m ^ n;
		int s = x >> 1;
		while(s > 0) {
			x |= s;
			s >>= 1;
		}
		return m & n & ~x;
	}
	public static void main(String[] args) {
		BitwiseNumbersRange solution = new BitwiseNumbersRange();
		int rangeBitwiseAnd = solution.rangeBitwiseAnd(5, 13);
		System.out.println(rangeBitwiseAnd);
	}
}
