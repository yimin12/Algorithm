/**
 * 
 */
package williamsNotebook.common.basicCalculator;

/**
 * @author yimin Huang
 *	
 *	provide helper function APIs for basic calculator
 * 
 * Algorithm Class
 */
public class ExpressionTokenizer {
	
	public String expression; // take the expression parse from user
	public int count; // record the current index you traverse in the expression
	ExpressionToken token; 
	
	public ExpressionTokenizer(String expression) {
		this.expression = expression;
		this.count = 0;
		this.token = null;
	}
	
	public ExpressionToken nextToken() throws Exception {
		if(expression == null || expression.length() < 0) throw new IllegalArgumentException("invalid expression");
		if(count > expression.length() - 1) return null;
		// take the next valid token
		while(count < expression.length()) {
			if(this.expression.charAt(count) == ' ') {
				this.count++;
			} else {
				break;
			}
		}
		this.count++;
		// post processing: handle the trailing empty space
		if(count - 1 > expression.length() - 1 || this.expression.charAt(count - 1) == ' ') {
			return null;
		}
		return new ExpressionToken(this.expression.charAt(count-1));
	}
}
