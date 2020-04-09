/**
 * 
 */
package williamsNotebook.common.node;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 下午9:40:28
* Description:
*/
public class HuffmanNode {

	public int data;
	public char c;
	public HuffmanNode left, right;
	public HuffmanNode(int data, char c) {
		this.data = data;
		this.c = c;
	}
	/**
	 * 
	 */
	public HuffmanNode() {
		// TODO Auto-generated constructor stub
	}
}
