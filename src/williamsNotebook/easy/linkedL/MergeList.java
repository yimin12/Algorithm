/**
 * 
 */
package williamsNotebook.easy.linkedL;


import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import williamsNotebook.common.ListNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月18日 下午8:18:14
* Description:
* 	Merge k sorted linked lists and return it as a new list. 
* 	The new list should be made by splicing together the nodes of the first two lists.
*/

public class MergeList {
	
	// Follow Up 1: Merge Two Sorted List 
	// Input: 1->2->4, 1->3->4    Output: 1->1->2->3->4->4
	// Method 1: Iteration with two pointers Time complexity : O(n+m) Space complexity : O(1)
	public ListNode mergeListIteration(ListNode one, ListNode two) {
		// Sanity check
		if(one == null || two == null) return one == null ? two : one;
		ListNode dummy = new ListNode(0);
		ListNode pOne = one, pTwo = two, cur = dummy;
		while(pOne != null && pTwo != null) {
			if(pOne.key < pTwo.key) {
				cur.next = pOne;
				pOne = pOne.next;
			} else {
				cur.next = pTwo;
				pTwo = pTwo.next;
			}
			cur = cur.next;
		}
		if(pOne != null) {
			cur.next = pOne;
		} else {
			cur.next = pTwo;
		}
		return dummy.next;
	}
	// Method 2: Recursion, Time complexity : O(n+m) Space complexity : O(n+m) 
	public ListNode mergeListRecursion(ListNode one, ListNode two) {
		// base case
		if(one == null) return two;
		if(two == null) return one;
		// recursion rule, if there are duplicate, pick one first
		if(one.key <= two.key) {
			one.next = mergeListRecursion(one.next, two);
			return one;
		} else {
			two.next = mergeListRecursion(one, two.next);
			return two;
		}
	}
	
	// Follow Up 2: merge k sorted list 
	// Input: [2->6->null, 5->null, 7->null] and Output: 2->5->6->7->null
	// Implemented with Best First Search
	public ListNode mergeKList(List<ListNode> listOfLists) {
		// Assumptions:
		if(listOfLists == null || listOfLists.size() == 0) throw new IllegalArgumentException("The given input is invalid");
		// Step 1: Use priority Queue to sort the entry
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(11, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if(o1.key == o2.key) return 0;
				return o1.key < o2.key ? -1 : 1;
			}
		});
		ListNode dummy = new ListNode(0), cur = dummy;
		for(ListNode list:listOfLists) {
			if(list != null) {
				minHeap.offer(list);
			}
		}
		// Best first Search
		while(!minHeap.isEmpty()) {
			ListNode temp = minHeap.poll();
			if(temp.next != null) {
				minHeap.offer(temp.next);
			}
			cur.next = temp;
			cur = cur.next;
		} 
		return dummy.next;
	}
	// Method 2, divide and conquer, recursion, we can do merge list one by one, or we can do binary reduction
	// We use Binary Reduction here Time : O(nlogn) Space: O(logn) for call stack where n is the number of list
	// the logic is quite similar to merge Sort
	public ListNode mergeKListsBinaryReduction(List<ListNode> listOfLists) {
		if(listOfLists == null || listOfLists.size() == 0) return null;
		return binaryReduction(listOfLists, 0, listOfLists.size() - 1);
	}
	private ListNode binaryReduction(List<ListNode> listOfLists, int left, int right) {
		if(left == right) return listOfLists.get(left);
		int mid = left + (right - left) / 2;
		ListNode first = binaryReduction(listOfLists, left, mid);
		ListNode second = binaryReduction(listOfLists, mid+1, right);
		return merge(first,second);
	}
	private ListNode merge(ListNode one, ListNode two) {
		ListNode dummy = new ListNode(0), cur = dummy;
		while(one != null && two != null) {
			if(one.key <= two.key) {
				cur.next = one;
				one = one.next;
			} else {
				cur.next = two;
				two = two.next;
			}
			cur = cur.next;
		}
		if(one != null) cur.next = one;
		else cur.next = two;
		return dummy.next;
	}
	// Method 2: Another solution for binary reduction, Time : O(nlogn) Space: O(logn) for call stack where n is the number of list
	public ListNode mergeKListsBinaryReductionII(List<ListNode> listsOfLists) {
		if(listsOfLists.size() == 0) return null;
		if(listsOfLists.size() == 1) return listsOfLists.get(0);
		if(listsOfLists.size() == 2) return merge(listsOfLists.get(0), listsOfLists.get(1));
		return mergeListIteration(
				mergeKListsBinaryReductionII(listsOfLists.subList(0, listsOfLists.size()/2)),
				mergeKListsBinaryReductionII(listsOfLists.subList(listsOfLists.size()/2, listsOfLists.size())));
	}
	// Method 3: Naive Solution, connect the list one by one
	// Time : O(kn) Space : O(1)
	public ListNode mergeKListsNaive(List<ListNode> listsOfLists) {
		if(listsOfLists == null || listsOfLists.size() == 0) return null;
		if(listsOfLists.size() == 1) return listsOfLists.get(0);
		int listSize = listsOfLists.size();
		ListNode base = listsOfLists.get(0);
		for(int i = 1; i < listSize; i++) {
			base = mergeListIteration(base, listsOfLists.get(i));
		}
		return base;
	}
	public static void main(String[] args) {
		MergeList solution = new MergeList();
		ListNode one = new ListNode(2);
		ListNode aa = new ListNode(4);
		ListNode ab = new ListNode(6);
		ListNode ac = new ListNode(8);
		ListNode two = new ListNode(1);
		ListNode ba = new ListNode(3);
		ListNode bb = new ListNode(5);
		ListNode bc = new ListNode(7);
		one.next = aa; aa.next = ab; ab.next = ac;
		two.next = ba; ba.next = bb; bb.next = bc;
		ListNode mergeListRecursion = solution.mergeListRecursion(one, two);
		while(mergeListRecursion != null) {
			System.out.print(mergeListRecursion.key + " ");
			mergeListRecursion = mergeListRecursion.next;
		}
	}
}
