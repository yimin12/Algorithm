/**
 * 
 */
package williamsNotebook.easy.linkedL.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��22�� ����10:46:07
* Description:
* 	��һ�����������а����������ҳ�������Ļ�����ڽ�㣬�������null��
* Explaination:
	����xΪ��ǰ���·�̣���ɫ·�̣���aΪ����ڵ��������·�̣���ɫ·�̣�����˳ʱ���ߣ��� cΪ���ĳ��ȣ���ɫ+��ɫ·�̣�
	������ָ��������ʱ��
	
	��ʱ��ָ���ߵ�·��ΪSslow = x + m * c + a
	��ָ���ߵ�·��ΪSfast = x + n * c + a
	2 Sslow = Sfast
	2 * ( x + m*c + a ) = (x + n *c + a)
	�Ӷ������Ƶ�����
	x = (n - 2 * m )*c - a
	= (n - 2 *m -1 )*c + c - a
	����ǰ���·�� = �������ĳ��ȣ�Ϊ����Ϊ0�� + c - a
	ʲô��c - a������������󣬻����沿�ֵ�·�̡�����ɫ·�̣�
	���ԣ����ǿ�����һ��ָ������A��ʼ�ߣ���һ��ָ���������B��ʼ���������ߣ�
	2��ָ���ٶ�һ������ô������ԭ���ָ���ߵ�����ڵ��ʱ�򣨴�ʱ�պ�����x��
	�������㿪ʼ�ߵ��Ǹ�ָ��Ҳһ���պõ��ﻷ��ڵ㡣
	����2�߻���������ǡ�������ڻ�����ڵ㡣
	
	����ж��Ƿ��л������һ����㷨���Ӷ�Ϊ��
	
	ʱ�临�Ӷȣ�O(n)
	
	�ռ临�Ӷȣ�O(1)
*/
public class FindEntranceOfCycle {
	public ListNode EntryNodeOfLoop(ListNode head) {
//		if it has a cycle, at least it contains 3 listnodes
		if(head == null || head.next == null || head.next.next == null) {
			return null;
		}
		ListNode fast = head;
		ListNode slow = head;
		while(fast != slow) {
			if(fast.next != null && fast.next.next == null) {
				fast = fast.next.next;
				slow = slow.next;
			} else {
//				if fast reach null, means there are no loop
				return null;
			}
		}
//		reset slow to the start point
		slow = head;
		while(fast!= slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}
}
