/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yimin Huang
 *	
 *		Given a list of non negative integers, arrange them such that they form the largest number.
 *		For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 *		Note: The result may be very large, so you need to return a string instead of an integer.
 *
 * Algorithm Class
 */
public class LargestNumber {

	// Time ~ O(NlogN), Space ~ O(N)
	// Use Arrays.sort() and define a Comparator with the compare(a, b) method:
	public Comparator<String> comparator = new Comparator<String>() {
		public int compare(String a, String b) {
			String ab = a + b, ba = b + a;
			for(int i = 0; i < ab.length(); i++) {
				// determined from the highest digit first
				char c1 = ab.charAt(i), c2 = ba.charAt(i);
				if(c1 < c2) return -1;
				else if(c1 > c2) return 1;	
			}
			return 0;
		}
	};
	public String largestNum(int[] num) {
		int n = num.length;
		String[] numStr = new String[n];
		for(int i = 0; i < n; i++) {
			numStr[i] = Integer.toString(num[i]);
		}
		// It will be sorted by mergeSort, so guarantee to be O(nlogn)
		Arrays.sort(numStr, comparator);
		StringBuilder sb = new StringBuilder();
		for(int i = n - 1; i >= 0; i--) {
			sb.append(numStr[i]);
		}
		int start = 0;
		// delete the leading zeros if exist.
		while(start < sb.length() - 1 && sb.charAt(start) == '0') {
			start++;
		}
		return sb.substring(start);
	}
	public static void main(String[] args) {
		LargestNumber solution = new LargestNumber();
		int[] array = new int[] {3, 30, 34, 5, 9};
		String largestNum = solution.largestNum(array);
		System.out.println(largestNum);
	}
}
