/**
 * 
 */
package QueueAndStack;

import java.util.LinkedList;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月9日 上午10:59:36
* Description:
* 	The numbers are in s1 originally, after sorting, the numbers should be in s1 as well
* 	and from top to bottom the numbers are sorted in ascending order
*/

public class SortArrayThreeStacks {
	
	// Assumption: s1 is not null
	public void sort(LinkedList<Integer> s1) {
		LinkedList<Integer> s2 = new LinkedList<Integer>();
		LinkedList<Integer> s3 = new LinkedList<Integer>();
		sort(s1, s2, s3, s1.size());
	}
	private void sort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int size) {
		if(size <= 1) return;
		int mid1 = size / 2;
		int mid2 = size - size/2;
		for(int i = 0; i < mid1; i++) {
			s2.offerFirst(s1.pollFirst());
		}
//		Use other stacks to sort s2/s1;
//		after sorting the numbers in s2/s1 are in ascending order from top to bottom in two stacks
		sort(s2, s3, s1, mid1);
		sort(s1, s3, s2, mid2);
		int i = 0, j = 0;
		while(i < mid1 && j < mid2) {
			if(s2.peekFirst() < s1.peekFirst()) {
				s3.offerFirst(s2.pollFirst());
				i++;
			} else {
				s3.offerFirst(s1.pollFirst());
				j++;
			}
		}
		while(i < mid1) {
			s3.offerFirst(s2.pollFirst());
			i++;
		}
		while(j < mid2) {
			s3.offerFirst(s1.pollFirst());
			j++;
		}
//		after merging, the numbers are in descending order from top to bottom
//		in s3, we need to push them back to s1 so that they are in ascending order
		for(int index = 0; index < size; index++) {
			s1.offerFirst(s3.pollFirst());
		}
	}
}
