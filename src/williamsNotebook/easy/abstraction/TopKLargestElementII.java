/**
 * 
 */
package williamsNotebook.easy.abstraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月25日 下午3:36:41
* Description:
* 	Implement a data structure, provide two interfaces:
* 	add(number). Add a new number in the data structure.
* 	topk(). Return the top k largest numbers in this data structure. k is given when we create the data structure.
		s = new Solution(3);
		>> create a new data structure.
		s.add(3)
		s.add(10)
		s.topk()
		>> return [10, 3]
		s.add(1000)
		s.add(-99)
		s.topk()
		>> return [1000, 10, 3]
		s.add(4)
		s.topk()
		>> return [1000, 10, 4]
		s.add(100)
		s.topk()
		>> return [1000, 100, 10]
*/

// Metho1: maintain the minHeap for O(logk) each time, and O(k) extra space;
public class TopKLargestElementII {

	private PriorityQueue<Integer> minHeap;
	private int maxSize;
	
	public TopKLargestElementII(int k) {
		minHeap = new PriorityQueue<Integer>();
		this.maxSize = k;
	}
	
	public void add(int num) {
		if(minHeap.size() < maxSize) {
			minHeap.offer(num);
		} else {
			if(num > minHeap.peek()) {
				minHeap.poll();
				minHeap.offer(num);
			}
		}
	}
	public List<Integer> topK(){
		Iterator iterator = minHeap.iterator();
		List<Integer> result = new ArrayList<Integer>();
		while(iterator.hasNext()) {
			result.add((Integer)iterator.next());
		}
		Collections.sort(result, Collections.reverseOrder());
		return result;
	}
}
