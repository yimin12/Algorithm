/**
 * 
 */
package DFS_BFS;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��24�� ����10:11:19
* Description:
* 	Find the Kth smallest number s such that s = 3 ^ x * 5 ^ y * 7 ^ z, x > 0 and y > 0 and z > 0, x, y, z 
* 	are all integers.
* Assumptions:
* 	K >= 1
* Examples:
*   the smallest is 3 * 5 * 7 = 105
*   the 2nd smallest is 3 ^ 2 * 5 * 7 = 315
*   the 3rd smallest is 3 * 5 ^ 2 * 7 = 525
*   the 5th smallest is 3 ^ 3 * 5 * 7 = 945
*/

public class KthSmallestThreeFiveSeven {
//	Method 1: BFS
	public long kth(int K) {
//		Assumption: k >= 1
		PriorityQueue<Long> minHeap = new PriorityQueue<Long>(K);
		Set<Long> visited = new HashSet<Long>();
//		we use the actual product value to represent the states <x,y,z> the value is 3^x * 5^y *7^z
//		and the initial state is <1,1,1>
		minHeap.offer(3*5*7L);
		visited.add(3*5*7L);
		while(K > 1) {
			long current = minHeap.poll();
//			for state <x+1,y,z>, the actual value is * 3
			if(visited.add(3 * current)) {
				minHeap.offer(3*current);
			}
//			for state <x,y+1,z> the actual value is * 5
			if(visited.add(5 * current)) {
				minHeap.offer(5*current);
			}
//			for state <x,y,z+1> the actual value is * 7
			if(visited.add(7 * current)) {
				minHeap.offer(7*current);
			}
			K--;
		}
		return minHeap.peek();
	}
//	Method 2: Linear solution using 3 deques
	public long kthII(int K) {
//		Assumption: K >= 1
		long seed = 3 * 5 * 7L;
//		we use three deques to maintain all the possible values.
//		The rule is:
//		deque3 only maintains the value of seed *3^x.
//		deque5 only maintains the value of seed *3^x * 5^y.
//		deque7 only maintains the value of seed *3^x * 5^y * 7^z.
		Deque<Long> three = new LinkedList<Long>();
		Deque<Long> five = new LinkedList<Long>();
		Deque<Long> seven = new LinkedList<Long>();
		three.add(seed*3);
		five.add(seed*5);
		seven.add(seed*7);
		long result = seed;
		while(K > 1) {
//			each round, pick the smallest one from the head of three deques.
//			when pushing back the value into the deques, following the rule:
//			if the smallest number x is from deque3:
//				we need to push x^3 to deque3, x*5 to deque5 and x*7 to deque7
//				to maintain the property of the three deques
//			if the smallest number x is from deque5:
//				we only need to push x*5 to deque5 and x*7 to deque7
//				we do not need to push x*3 again, because x*3 has to be already generated before,
//				x = 3^a*5^b, x*3 = 3^(a+1)*5^b = 3^(a+1)*5^(b-1)*5,
//				and 3^(a+1)*5^(b-1)<x,  it means x*3 has to be already generated by 3*(a+1)*5(b-1)
//				and it already in deque5
//			similarly, if the smallest number x is from deque7:
//				we only need to push x*7 to deque7
			if(three.peekFirst() < five.peekFirst() && three.peekFirst() < seven.peekFirst()) {
				result = three.pollFirst();
				three.offerLast(result*3);
				five.offerLast(result*5);
				seven.offerFirst(result*7);
			} else if(five.peekFirst() < three.peekFirst() && five.peekFirst() < seven.peekFirst()) {
				result = five.pollFirst();
				five.offerLast(result*5);
				seven.offerLast(result*7);
			} else {
				result = seven.peekFirst();
				seven.offerLast(result*7);
			}
			K--;
		}
		return result;
	}
}
