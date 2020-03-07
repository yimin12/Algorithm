/**
 * 
 */
package williamsNotebook.easy.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

import williamsNotebook.common.basicCalculator.ExpressionToken;
import williamsNotebook.common.basicCalculator.ExpressionTokenizer;
import williamsNotebook.common.basicCalculator.OperatorLevel;

/**
 * @author yimin Huang
 * 	
 *	Implement a basic calculator to evaluate a simple expression string.
 *	The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *	You may assume that the given expression is always valid
 *	"1 + 1" = 2
 *	" 2-1 + 2 " = 3
 *  "(1+(4+5+2)-3)+(6+8)" = 23
 *  use the eval built-in library function.
 *  
 * Algorithm Class
 */
public class BasicCalculator {
	
	// Start from simple situation: which does not support parentheses
	// n = the length of the expression string
	// Time: O(n), Extra Space = O(1)
	public int calculate(String expression) throws Exception {
		int result = 0;
		char lastOperator = '+';
		ExpressionTokenizer tokenizer = new ExpressionTokenizer(expression);
		for(ExpressionToken token = tokenizer.nextToken(); token != null; token = tokenizer.nextToken()) {
			if(token.getType() == ExpressionToken.Type.OPERATOR) {
				lastOperator = token.charValue();
			} else {
				if(lastOperator == '+') {
					result += token.intValue();
				} else {
					result -= token.intValue();
				}
			}
		}
		return result;
	}
	
	// FollowUp 2: which support parentheses, the execution order is not always from left to right
	// Ideas: 
	// 1. Compute the parentheses level of each operator
	// 2. Use a stack to maintain the execution order of operators
	//    	a. if top operator < current, push
	// 		b. if top operator > current, pop
	// 3. Use a separate stack to maintain the operands
	// 		a. when we scan an operand, push
	//		b. when we pop an operators, pop two operands and push the result.
	// Time: O(n), Extra Space = O(n)
	public int calculateWithParentheses(String expression) throws Exception {
		// maintain the level with its ascending order
		Deque<OperatorLevel> operatorStack = new ArrayDeque<OperatorLevel>();
		// maintain the calculated result
		Deque<Integer> operandStack = new ArrayDeque<Integer>();
		int parenthesesLevel = 0;
		ExpressionTokenizer tokenizer = new ExpressionTokenizer(expression);
		for(ExpressionToken token = tokenizer.nextToken(); token != null; token = tokenizer.nextToken()) {
			switch (token.getType()) {
			case NUMBER:
				operandStack.addLast(token.intValue());
				break;
			case PARENTHESIS:
				char parenthesis = token.charValue();
				if(parenthesis == '(') {
					parenthesesLevel++;
				} else {
					parenthesesLevel--;
				}
				break;
			case OPERATOR:
				while(!operatorStack.isEmpty() && operatorStack.getLast().getLevel() >= parenthesesLevel) {
					// case 2: start to pop the element that is larger or equal than parenthesesLevel
					evaluateTopOperator(operatorStack, operandStack);
				}
				operatorStack.addLast(new OperatorLevel(token.charValue(),parenthesesLevel));
				break;
			}
		}
		while(!operatorStack.isEmpty()) {
			evaluateTopOperator(operatorStack, operandStack);
		}
		return operandStack.getLast();
	}
	// helper function to pop the element until it meet the requirement
	private void evaluateTopOperator(Deque<OperatorLevel> operatorStack, Deque<Integer> operandStack) {
		// get the adjacent operand , and calculate from left to right
		int rightOperand = operandStack.removeLast();
		int leftOperand = operandStack.removeLast();
		char operator = operatorStack.removeLast().getOperand();
		int result = 0;
		switch (operator) {
		case '+':
			result = leftOperand + rightOperand;
			break;
		case '-':
			result = leftOperand - rightOperand;
			break;
		default:
			assert false : String.format("Operator '%c' is not supported", operator);
		}
		operandStack.add(result);
	}
	
	public static void main(String[] args) throws Exception {
		BasicCalculator solution = new BasicCalculator();
		int calculate = solution.calculateWithParentheses("(1+(4+5+2)-3)+(6-8)");
		System.out.println(calculate);
	}
}
