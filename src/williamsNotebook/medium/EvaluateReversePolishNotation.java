/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


/**
 * @author yimin Huang
 *		
 *		Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *		Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *		Some examples:
 *			
 *		Division between two integers should truncate toward zero.
 *		The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be 
 *			any divide by zero operation.

 * Algorithm Class
 */
public class EvaluateReversePolishNotation {
	
	private static final Set<String> OPERATORS = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
	public int evalRPN(String[] tokens) {
		Deque<Integer> stack = new LinkedList<Integer>();
		for(String s : tokens) {
			if(OPERATORS.contains(s)) {
				int y = stack.pollLast();
				int x = stack.pollLast();
				stack.offerLast(eval(x, y, s));
			} else {
				stack.offerLast(Integer.parseInt(s));
			}
		}
		return stack.pollFirst();
	}
	private int eval(int x, int y, String operator) {
		switch(operator) {
		case "+" : return x + y;
		case "-" : return x - y;
		case "*" : return x * y;
		default : return x / y;
		}
	}
}
