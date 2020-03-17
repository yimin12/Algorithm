/**
 * 
 */
package williamsNotebook.medium;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author yimin Huang
 *
 *		Implement a basic calculator to evaluate a simple expression string.
 *		The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *		
 * Algorithm Class
 */
public class BasicCalculatorII {
	
	interface Operator{
		int eval(int x, int y);
	}
	
	// basic required parameters
	private static final Map<String, Operator> OPERATORS;
	static {
		OPERATORS = new HashMap<String, Operator>();
		OPERATORS.put("+", new Operator() { public int eval(int x, int y) {return x + y;} });
		OPERATORS.put("-", new Operator() { public int eval(int x, int y) {return x - y;} });
		OPERATORS.put("*", new Operator() { public int eval(int x, int y) {return x * y;} });
		OPERATORS.put("/", new Operator() { public int eval(int x, int y) {return x / y;} });
	}
	private static final Map<String, Integer> OPERATORS_PRIORITY;
	static {
		OPERATORS_PRIORITY = new HashMap<String, Integer>();
	    OPERATORS_PRIORITY.put("+", 1);
	    OPERATORS_PRIORITY.put("-", 1);
	    OPERATORS_PRIORITY.put("*", 2);
	    OPERATORS_PRIORITY.put("/", 2);
	}
	
	public int calculate(String s) {
		String[] strings = toStrArray(s);
		String[] rpn = toRPN(strings);
		return evalRPN(rpn);
	}
	// convert an expression string to a string array (infix notation), like a cursor
	private String[] toStrArray(String s) {
		Queue<String> q = new LinkedList<String>();
		int n = s.length(), pos = 0;
		// capture the useful information and discard space or other useless information
		while(pos < n) {
			char c = s.charAt(pos);
			if (Character.isDigit(c)) {
				// Case 1: if it is digit
				int val = Character.getNumericValue(c);
				while(pos < n - 1 && Character.isDigit(s.charAt(pos+1))) {
					val = val * 10 + Character.getNumericValue(++pos);
				}
				q.add(Integer.toString(val));
			} else if(OPERATORS.containsKey(Character.toString(c))) {
				// Case 2: if it is Operators
				q.add(Character.toString(c));
			} else if(c == '(' || c == ')') {
				q.add(Character.toString(c));
			}
			pos++;
		}
		String[] tokens = new String[q.size()];
		for(int i = 0; i < tokens.length; i++) {
			tokens[i] = q.poll();
		}
		return tokens;
	}
	// convert infix notation to reverse polish notation: Shunting-yard algorithm
	private String[] toRPN(String[] tokens) {
		Queue<String> queue = new LinkedList<String>();
		Deque<String> stack = new LinkedList<String>();
		for(int i = 0; i < tokens.length; i++) {
			String s = tokens[i];
			if(OPERATORS.containsKey(s)) {
				// pop stack to queue if the operator has same or smaller priorty
				while(!stack.isEmpty() && !stack.peekLast().equals("(") && OPERATORS_PRIORITY.get(s) <= OPERATORS_PRIORITY.get(stack.peekLast())) {
					queue.add(stack.pollLast());
				}
				stack.offerLast(s); // push operator to stack
			} else if(s.equals("(")) {
				stack.offerLast(s); // push "(" to stack
			} else if(s.equals(")")) {
				// pop all operators between "(" and ")" to queue
				while(!stack.peekLast().equals("(")) queue.add(stack.pollLast());
				stack.pollLast(); // remove "(" from stack
			} else {
				queue.add(s); //  add number to queue
			}
		}
		// pop the rest of opreators in stack to queue
		while(!stack.isEmpty()) {
			queue.add(stack.pollLast());
		}
		String[] rpn = new String[queue.size()];
		for(int i = 0; i < rpn.length; i++) {
			rpn[i] = queue.poll();
		}
		return rpn;
	}
	// evaluate reverse polish notation expression
	private int evalRPN(String[] tokens) {
		Deque<Integer> stack = new LinkedList<Integer>();
		for(String s:tokens) {
			if(OPERATORS.containsKey(s)) {
				int y = stack.pollLast();
				int x = stack.pollLast();
				stack.offerLast(OPERATORS.get(s).eval(x, y));
			} else {
				stack.offerLast(Integer.parseInt(s));
			}
		}
		return stack.pollLast();
	}
	
	public static void main(String[] args) {
		BasicCalculatorII solution = new BasicCalculatorII();
		int calculate = solution.calculate("(1+(4+5*2)-3)+(6+8)");
		System.out.println(calculate);
	}
}

