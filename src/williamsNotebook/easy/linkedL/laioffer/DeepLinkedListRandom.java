/**
 * 
 */
package williamsNotebook.easy.linkedL.laioffer;

import java.util.HashMap;
import java.util.Map;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��23�� ����10:18:39
* Description:
* 	Each of the nodes in the linked list has another pointer pointing to a random 
* 	node in the list or null. Make a deep copy of the original list.
*/

public class DeepLinkedListRandom {
//	Method 1: using HashMap to avoid copy multiple times from same node
	public RandomListNode copy(RandomListNode head) {
		if(head == null) {
			return null;
		}
//		Sentinel node to help construct the deep copy
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode cur = dummy;
//	Maintain the mapping between the node n the original list and the corresponding node in new list		
		Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		while(head != null) {
//			Copy the current node if need
			if(!map.containsKey(head)) {
				map.put(head, new RandomListNode(head.value));
			}
//			Connect the copied node to the deep copy list.
			cur.next = map.get(head);
//			Copy the random node if necessary
			if(head.random != null) {
				if(!map.containsKey(head.random)) {
					map.put(head.random, new RandomListNode(head.random.value));
				}
//				Connect the copied node to the random pointer
				cur.next.random = map.get(head.random);
			}
			head = head.next;
			cur = cur.next;
		}
		return dummy;
	}
//	Method 2: Another three pass solution, not using HashMap, but changing the original list structure 
//	during the copy (it will change back at the end)
	public RandomListNode copyII(RandomListNode head) {
		if(head == null) {
			return null;
		}
//		First Pass, for each node in the original list, insert a copied node between the node 
//		and node.next
		RandomListNode cur = head;
		while(cur != null) {
//			Make a copy of cur node, insert it to the middle of cur and cur.next.
			RandomListNode copy = new RandomListNode(cur.value);
			copy.next = cur.next;
			cur.next = copy;
			cur = cur.next.next;
		}
//		Second pass, link the random pointer for the copied node
		cur = head;
		while(cur != null) {
			if(cur.random != null) {
				cur.next.random = cur.random.next;
			}
			cur = cur.next.next;
		}
//		Third pass, extract the copied node
		cur = head;
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode copyPrev = dummy;
		while(cur!=null) {
			copyPrev.next = cur.next;
			cur.next = cur.next.next;
			copyPrev = copyPrev.next;
			cur = cur.next;
		}
		return dummy.next;
	}
}

