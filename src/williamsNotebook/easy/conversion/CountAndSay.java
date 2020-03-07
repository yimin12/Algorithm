/**
 * 
 */
package williamsNotebook.easy.conversion;

/**
 * @author yimin Huang
 * 
 *		The count-and-say sequence is the sequence of integers beginning as follows:
 *		1, 11, 21, 1211, 111221, ...
 *		1 is read off as "one 1" or 11.
 *		11 is read off as "two 1s" or 21.
 *		21 is read off as "one 2, then one 1" or 1211.
 *		Given an integer n, generate the nth sequence.
 *		Note: The sequence of integers will be represented as a string.
 *
 * Algorithm Class
 */
public class CountAndSay {

	// Naive solution by using string builder
	// Time ~ O(N^2), Space ~ O(N)
	public String countAndSay(int n) {
		if(n < 0) throw new IllegalArgumentException("input is invalid");
		StringBuilder sb = new StringBuilder("1");
		for(int i = 1; i < n; i++) {
			StringBuilder next = new StringBuilder();
			int count = 1;
			for(int j = 1; j < sb.length(); j++) {
				if(sb.charAt(j) == sb.charAt(j-1)) {
					count++;
				} else {
					next.append(count);
					next.append(sb.charAt(j-1));
					// reset count to 1
					count = 1;
				}
			}
			next.append(count);
			next.append(sb.charAt(sb.length()-1));
			sb = next;
		}
		return sb.toString();
	}
}
