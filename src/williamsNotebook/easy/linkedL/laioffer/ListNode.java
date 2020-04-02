/**
 * 
 */
package williamsNotebook.easy.linkedL.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��2�� ����9:36:45
* Description:
*/


public class ListNode {
	
	 public Object data;// Ҫ���������
	 public ListNode next;
	
	 public ListNode() {
		 
	 }
	 
	 public ListNode(Object data) {
		 super();
		 this.data = data;
	 }
	 
	 public ListNode(Object data, ListNode next) {
		 super();
		 this.data = data;
		 this.next = next;
	 }

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the next
	 */
	public ListNode getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(ListNode next) {
		this.next = next;
	}
	 
	 
}
