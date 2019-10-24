/**
 * 
 */
package LinkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月9日 下午4:58:36
* Description: Insert an specific ListNode to the ascending sortedList
*/

public class InsertSortedLinkedList {
	
	public ListNode insert(ListNode head, int value){
		ListNode newNode = new ListNode(value);
//		1.determine if the inserted node is before head;
		if(head == null || (int)head.data >= value) {
			newNode.next = head;
			return newNode;
		}
//		2.insert the new node to the right position. using the previous node to traverse
//		  linked list,the insert position of the new node should between prev and perv.next
		ListNode prev = head;
		while(prev.next != null && (int)prev.next.data < value) {
			prev = prev.next;
		}
		newNode.next = prev.next;
		prev.next = newNode;
		return head;
	}
}
