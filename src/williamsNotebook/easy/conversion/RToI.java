/**
 * 
 */
package williamsNotebook.easy.conversion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yimin Huang
 * 	Roman to Integer:
 *		Given a roman numeral, convert it to an integer.
 *		Input is guaranteed to be within the range from 1 to 3999.
 *	Integer to Roman:
 *		Given an integer, convert it to a roman numeral.
 *		Input is guaranteed to be within the range from 1 to 3999.
 * Algorithm Class
 */
public class RToI {
	// corresponding Digit to Roman numerals
	private static final int[] values = {
	    1000, 900, 500, 400,
	    100, 90, 50, 40,
	    10, 9, 5, 4,
	    1
	};
	private static final String[] symbol = {
	    "M", "CM", "D", "CD",
	    "C", "XC", "L", "XL",
	    "X", "IX", "V", "IV",
	    "I"
	};
	
	// intToRoman Coversion, you should familiar with the Roman pattern
	// Time ~ O(N), Space ~ O(1)
	public String itor(int num) {
		int n = num, i = 0;
		StringBuilder roman = new StringBuilder();
		while(n > 0) {
			while(n / values[i] > 0) {
				roman.append(symbol[i]);
				n -= values[i];
			}
			i++;
		}
		return roman.toString();
	}
	
	// romeToInt Conversion
	// Time ~ O(N), Space ~ O(1)
	/*
	 * 从罗马数字转阿拉伯数字要用到减法规则，因为其小数字出现在大数字之前时必练成一组运用减法规则，如 IV 一定是 4，而不是 1 +
	 * 5，所以我们要判断如果当前位比前一位大，则将当前位减去两倍的前一位（因为之前已经加过一次前一位）。
	 */
	public int romanToInt(String s) {
		// use hashMap to map the corresponding between Roman and Integer
	    Map<String, Integer> map = new HashMap<>();
	    for (int i = 0; i < symbol.length; i++)
	        map.put(symbol[i], values[i]);
	    
	    int num = 0, prev = 0;
	    for (int i = 0; i < s.length(); i++) {
	        int curr = map.get(s.substring(i, i + 1)); // find the ith character 
	        num += (curr > prev) ? (curr - 2 * prev) : curr;    // use subtractive rule
	        prev = curr;
	    }
	    return num;
	}
	
	public static void main(String[] args) {
		RToI solution = new RToI();
		int romanToInt = solution.romanToInt("MXCVI");
		System.out.println(romanToInt);
	}
}
