/**
 * 
 */
package GraphSearchAlgorithmDFS;

import java.util.ArrayList;
import java.util.List;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月12日 下午9:23:25
* Description:
* 	Given N pairs of parentheses “()”, return a list with all the valid permutations.
* 	N = 1, all valid permutations are ["()"]
	N = 3, all valid permutations are ["((()))", "(()())", "(())()", "()(())", "()()()"]
  Assumption:
  	N > 0;
*/

public class ValidParentheseI {
	public List<String> validParentheses(int k){
		List<String> result = new ArrayList<String>();
//		the final string contains 2k characters
		char[] cur = new char[2*k];
		helper(cur, k, k, 0, result);
		return result;
	}
//	left : how many '(' we still have
//	right: how many ')' we still have
//	index: the current position in cur we want to fill with '(' or ')'
	private void helper(char[] cur, int left, int right, int index, List<String> result) {
//		terminate condition:
//		when we do not have any parentheses 
		if(left == 0 && right == 0) {
			result.add(new String(cur));
			return;
		}
//		when can add a '(' ? whenever there is some '(' we can still use
		if(left > 0) {
			cur[index]='(';
			helper(cur, left - 1, right, index+1, result);
//			NOTICE: it looks like we do not do anything when back tracking to previous level
//					it still work because
//					1. we are setting the character at index and when back tracking,
//						what we need is just i) remove the character at index and 
//						 ii) add different character at index
//					2. only when we fill in all positions in cur, we have complete solution
//					the code itself actually already suffices the above two points and it already
//					does the correct removing operation when back tracked to previous level
		}
//		when we can add ')'? when there is more '(' than used, because each ')' should be associate with 
//		a previous '('
		if(right > left) {
			cur[index] = ')';
			helper(cur, left, right-1, index+1, result);
		}
//		you need to know the difference when you use StringBuilder and just an array
	}
}
