/**
 * 
 */
package williamsNotebook.common.basicCalculator;

/**
 * @author yimin Huang
 *
 * Algorithm Class
 */
public class OperatorLevel {

	Character operand;
	Integer level;
	/**
	 * 
	 */
	public OperatorLevel() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param operand
	 * @param level
	 */
	public OperatorLevel(Character operand, Integer level) {
		super();
		this.operand = operand;
		this.level = level;
	}
	/**
	 * @return the operand
	 */
	public Character getOperand() {
		return operand;
	}
	/**
	 * @param operand the operand to set
	 */
	public void setOperand(Character operand) {
		this.operand = operand;
	}
	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
}
