/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月25日 下午4:00:45
* Description:
* 	There areNworkers. Thei-th worker has aquality[i]and a minimum wage expectationwage[i].
* 	Now we want to hire exactlyK workers to form apaid group. When hiring a group of K workers, we must pay them according to the following rules:
* 	Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
* 	Every worker in the paid group must be paid at least their minimum wage expectation.
* Example:	
* 	Input: quality = [10,20,5], wage = [70,50,30], K = 2 Output: 105.00000
* 	Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
*/

public class MinimumCostHireKWorkers {
	
	//Time Complexity: O(NlogN), where N is the number of workers. 先排序 O(NlogN), 外层循环 O(N)，内层Heap操作O(logK)
	class Worker implements Comparable<Worker>{
		int wage, quality;
		public Worker(int wage,int quality) {
			this.wage = wage;
			this.quality = quality;
		}
		public double ratio() {
			return (double)wage/quality;
		}
		@Override
		public int compareTo(Worker o) {
			return Double.compare(ratio(), o.ratio());
		}
	}
	public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
		int N = quality.length;
		Worker[] workers = new Worker[N];
		for(int i = 0; i < N; i++) {
			workers[i] = new Worker(wage[i], quality[i]);
		}
		// Sorted by wage/quality in ascending order, Why should we sort first?
		// because we have not recorded the ratios for the first k elements, the final ratio will decided by the largest ratio.
		// so we need to sorted in ascending order.
		Arrays.sort(workers);
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
		int sumOfQuality = 0;
		double res = Double.MAX_VALUE;
		// key insight: we first remove the people with max quality
		for(Worker worker:workers) {
			double ratio = worker.ratio();
			maxHeap.offer(worker.quality);
			sumOfQuality += worker.quality;
			if(maxHeap.size() > k) {
				sumOfQuality -= maxHeap.poll();
			}
			if(maxHeap.size() == k) {
				res = Math.min(res, ratio * sumOfQuality);
			}
		}
		return res;
	}
	public static void main(String[] args) {
		MinimumCostHireKWorkers solution = new MinimumCostHireKWorkers();
		int[] quality = {10,20,5}, wage = {70,50,30};
		double mincostToHireWorkers = solution.mincostToHireWorkers(quality, wage, 2);
		System.out.println(mincostToHireWorkers);
	}
}
