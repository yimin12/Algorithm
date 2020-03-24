/**
 * 
 */
package williamsNotebook.easy.abstraction;

import java.util.ArrayDeque;
import java.util.Deque;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月23日 下午11:15:05
* Description:
* 	Given a list of daily temperaturesT, return a list such that, for each day in the input, tells you how many days you
* 	would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
* 	For example, given the list of temperaturesT = [73, 74, 75, 71, 69, 72, 76, 73], your output should be[1, 1, 4, 2, 1, 1, 0, 0].
* 	The length oftemperatureswill be in the range[1, 30000]. Each temperature will be an integer in the range[30, 100]
*/

public class DailyTemperatures {

	//  Monotonous stack inspired by Largest Rectangle in Histogram.
	// Time: O(n) Space: O(n)
	public int[] dailyTemperatures(int[] T) {
		int[] res = new int[T.length];
		Deque<Integer> stack = new ArrayDeque<Integer>();
		for(int i = T.length - 1; i >= 0; i--) {
			while(!stack.isEmpty() && T[i] >= T[stack.peek()]) {
				stack.pop();
			}
			res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
			stack.push(i);
		}
		return res;
	}
}
