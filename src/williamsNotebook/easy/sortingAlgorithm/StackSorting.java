/**
 * 
 */
package williamsNotebook.easy.sortingAlgorithm;

import java.util.Stack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月26日 下午2:40:45
* Description:
* 	Sort a stack in ascending order (with biggest terms on top).
* 	You may use at most one additional stack to hold items, but you may not copy the elements into any other data structure (e.g. array).
* 	The data will be serialized to [4,2,1,3]. The last element is the element on the top of the stack.
* 	O(n^2) time is acceptable.
*/

public class StackSorting {

	// Time :O(n^2) Extra Space : O(n)
	public void stackSorting(Stack<Integer> stack) {
		Stack<Integer> swap = new Stack<Integer>();
		while(!stack.isEmpty()) {
			int element = stack.pop();
			while(!swap.isEmpty() && swap.peek() < element) {
				stack.push(swap.pop());
			}
			swap.push(element);
		}
		while(!swap.isEmpty()) {
			stack.push(swap.pop());
		}
	}
}
