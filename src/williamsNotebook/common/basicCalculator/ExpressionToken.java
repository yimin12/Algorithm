/**
 * 
 */
package williamsNotebook.common.basicCalculator;

/**
 * @author yimin Huang
 *	
 *	helper function for ExpressionTokenizer
 *
 * Algorithm Class
 */
public class ExpressionToken {
	
	Type type; 
	Character token;
	
	public enum Type {
		NUMBER, // e.g. 23
		OPERATOR, // +, -, *, /
		PARENTHESIS // (,)
	}
	
	public ExpressionToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	// Constructor 
	public ExpressionToken(Character token){
		super();
		this.token = token;
		if(token - '0' <= 9 && token - '0' >= 0) {
			this.type = type.NUMBER;
		} else if(token == '(' || token == ')') {
			this.type = type.PARENTHESIS;
		} else {
			this.type = type.OPERATOR;
		}
	}
	public Type getType() {
		return this.type;
	}
	
	public int intValue() {
		return this.token - '0';
	}
	
	public Character charValue() {
		return token;
	}
}
