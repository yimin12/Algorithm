/**
 * 
 */
package williamsNotebook.easy.math;

import java.util.ArrayList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 下午3:32:59
* Description:
* 	Narcissistic Number is a number that is the sum of its own digits each raised to the power of the number of digits.See wiki
* 	For example the 3-digit decimal number153is a narcissistic number because 153 = 1^3+ 5^3+ 3^3.
* 	And the 4-digit decimal number1634is a narcissistic number because 1634 = 1^4+ 6^4+ 3^4+ 4^4.
* 	Given n, return all narcissistic numbers with n digits.
* Example:
* 	Given1, return[0,1,2,3,4,5,6,7,8,9].
* 	Given2, return[].
* You may assume n is smaller than 8.
*/

public class NarcissisticNumber {

	// Analysis
	// Might be better to implement custom power function, since Math.pow() is returning double type.
	public ArrayList<Integer> getNarcissisticNumbers(int n){
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(n == 1) {
			for(int i = 0; i < 10; i++) {
				res.add(i);
			}
			return res;
		}
		// 	遍历全部n位数的数 
		for(int i = pow(10, n-1); i < pow(10, n); i++) {
			int j = i;
			int s = 0;
			while(j > 0) {
				s += pow((j%10), n);
				j /= 10;
			}
			if(s == i) {
				res.add(i);
			}
		}
		return res;
	}
	private int pow(int a, int b) {
		int val = 1;
		for(int i = 1; i <= b; i++) {
			val *= a;
		}
		return val;
	}
	public static void main(String[] args) {
		NarcissisticNumber solution = new NarcissisticNumber();
		ArrayList<Integer> narcissisticNumbers = solution.getNarcissisticNumbers(6);
		System.out.println(narcissisticNumbers.toString());
	}
}
