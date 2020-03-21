/**
 * 
 */
package williamsNotebook.easy.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月20日 下午9:02:10
* Description:
* 	Given a time represented in the format "HH:MM", form the next closest time by reusing the current 
* 	digits. There is no limit on how many times a digit can be reused.
* 	ou may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. 
* 	"1:34", "12:9" are all invalid.
* Input: "19:34" Output: "19:39"
*/
public class NextClosestTime {
	
	// method 1: find next digit for each position in "HH:MM" from right to left;
	public String nextClosest(String time) {
		char[] result = time.toCharArray();
        char[] digits = new char[] {result[0], result[1], result[3], result[4]};
        Arrays.sort(digits);

        // find next digit for HH:M_
        result[4] = findNext(result[4], (char)('9' + 1), digits);  // no upperLimit for this digit, i.e. 0-9
        if(result[4] > time.charAt(4)) return String.valueOf(result);  // e.g. 23:43 -> 23:44

        // find next digit for HH:_M
        result[3] = findNext(result[3], '5', digits);
        if(result[3] > time.charAt(3)) return String.valueOf(result);  // e.g. 14:29 -> 14:41

        // find next digit for H_:MM
        result[1] = result[0] == '2' ? findNext(result[1], '3', digits) : findNext(result[1], (char)('9' + 1), digits);
        if(result[1] > time.charAt(1)) return String.valueOf(result);  // e.g. 02:37 -> 03:00 

        // find next digit for _H:MM
        result[0] = findNext(result[0], '2', digits);
        return String.valueOf(result);  // e.g. 19:59 -> 11:11
	}
	private char findNext(char current, char upperLimit, char[] digits) {
		if(current == upperLimit) {
			return digits[0];
		}
		int pos = Arrays.binarySearch(digits, current) + 1;
		while(pos < 4 && (digits[pos] > upperLimit || digits[pos] == current)) {
			pos++;
		}
		return pos == 4 ? digits[0] : digits[pos];
	}
	

	// Method 2: convert it to minutes and count it one minute by one minute
	// Time Complexity: O(1). We try up to 24 * 60 possible times until we find the correct time.
	// Space Complexity: O(1).
	public String nextCloseTimeI(String time) {
		int cur = 60 * Integer.parseInt(time.substring(0, 2));
		cur += Integer.parseInt(time.substring(3));
		Set<Integer> digit = new HashSet<Integer>();
		for(char c: time.toCharArray()) {
			digit.add(c - '0');
		}
		// the searching pattern in the while loop you should know
		while(true) {
			cur = (cur + 1) % (24 * 60);
			int[] digits = new int[] {cur / 60 / 10, cur / 60 % 10, cur % 60 / 10, cur % 60 % 10};
			search :{
				for(int d : digits) if(!digit.contains(d)) break;
				return String.format("%02d:%02d", cur/60, cur%60);
			}
		}
	}
	// Method 3: Build from allowed digits
	// We only need traverse 4^4 combinations to try all the possibilities
	// Time Complexity: O(1). We all4^4possible times and take the best one.
	// Space Complexity: O(1).
	public String nextClosestTime(String time) {
		int start = 60 * Integer.parseInt(time.substring(0,2));
		start += Integer.parseInt(time.substring(3));
		int res = start;
		int elapsed = 24 * 60;
		Set<Integer> digit = new HashSet<Integer>();
		for(char c : time.toCharArray()) {
			digit.add(c - '0');
		}
		for(int h1 : digit) for(int h2 : digit)if(h1 * 10 + h2 < 24) {
			for(int m1 : digit) for(int m2 : digit)if(m1 * 10 + m2 < 60) {
				int cur = 60 * (h1 * 10 + h2) + (m1 * 10 + m2);
				int candElapsed = Math.floorMod(cur - start, 24 * 60);
				if( 0 < candElapsed && candElapsed < elapsed) {
					res = cur;
					elapsed = candElapsed;
				}
			}
		}
		return String.format("%02d:%02d", res/60, res%60);
	}
	
}
