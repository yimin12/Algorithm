/**
 * 
 */
package williamsNotebook.easy.calculator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yimin Huang
 *
 *		Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *		If the fractional part is repeating, enclose the repeating part in parentheses.
 *		For example,
 *			Given numerator = 1, denominator = 2, return "0.5".
 *			Given numerator = 2, denominator = 1, return "2".
 *			Given numerator = 2, denominator = 3, return "0.(6)".
 *
 * Algorithm Class
 */
public class FractionToRecurringDecimal {

	public String fractionToDecimal(int numerator, int denominator) {
		if(numerator == 0) return "0";
		StringBuilder sb = new StringBuilder();
		if(numerator < 0 ^ denominator < 0) sb.append("-");
		long n = numerator, d = denominator;
		n = Math.abs(n);
		d = Math.abs(d);
		sb.append(n/d);
		// add the digit part
		long r = n % d;
		if(r == 0) return sb.toString();
		else sb.append(".");
		
		// add fractional part
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		while(r > 0) {
			if(map.containsKey(r)) {
				sb.insert(map.get(r), "(");
				sb.append(")");
				break;
			} else {
				map.put(r, sb.length());
				r *= 10;
				sb.append(r/d);
				r %= d;
			}
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		FractionToRecurringDecimal solution = new FractionToRecurringDecimal();
		String fractionToDecimal = solution.fractionToDecimal(1, 7);
		float num =  (1f) / 7;
		System.out.println(num);
		System.out.println(fractionToDecimal);
	}
}
