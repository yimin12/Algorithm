/**
 * 
 */
package williamsNotebook.common.classified;

import williamsNotebook.common.SWAP;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月26日 上午10:49:29
* Description:
* 	Given an integer array, heapify it into a min-heap array.
* 	For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
* 	What is heap? Heap is a data structure, which usually have three methods: push, pop and top. where "push" add a new element the heap, "pop" delete
* 		 the minimum/maximum element in the heap, "top" return the minimum/maximum element
* 	What is heapify? Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].
* 	What if there is a lot of solutions? Return any of them.
* 	Given [3,2,1,4,5], return [1,2,3,4,5] or any legal heap array.
* 	O(n) time complexity
*   Heapify一个Array，也就是对array中的元素进行siftup或者siftdown的操作。根据min heap定义进行操作即可。
*     这里值得注意的是，对于扫描整个array的情况下，siftup和siftdown有complexity上的区别
*     基本的原因在于：siftdown的complexity，实质上是node相对于bottom移动的次数，而根据binary heap本身的特性，决定了约靠近bottom的node越多；相对照的是siftup，是node相对于root节点的移动次数。
*    如果Heapify可以用O(n)实现，那么HeapSort所需的时间复杂度为何是O(nlogn)？因为HeapSort其实包含了两个步骤，第一步，Heapify，build (min) heap，所需时间复杂度O(n)，第二步，依次删除最小值（min heap），
*    对于Heap来说，删除操作的复杂度是O(logn)， 而这个删除需要执行O(n)，来得到最终sort的结果，于是总体时间复杂度是O(nlogn)。  
*/
public class HeapifyArray {

	// Assumption: heapify for a minHeap Time O(nlogn)
	public void heapify(int[] array) {
		for(int i = 0; i < array.length; i++) {
			procolateUp(array, i);
		}
	}
	// Procolate Up: Time O(nlogn)
	private void procolateUp(int[] array, int k) {
		while(k != 0) {
			int parent = (k - 1)/2;
			if(array[k] > array[parent]) {
				break;
			}
			SWAP.intSwap(array, k, parent);
			k = parent;
		}
	}
	// Procolate Down: Time O(n)
	public void heapifyII(int[] array) {
		for(int i = array.length / 2; i >= 0; i--) {
			procolateDown(array, i);
		}
	}
	// Time O(n), proofs in practice class
	private void procolateDown(int[] array, int k) {
		while(k < array.length) {
			int smallest = k;
			if(k * 2 + 1 < array.length && array[k * 2 + 1] < array[smallest]) {
				smallest = k * 2 + 1;
			}
			if(k * 2 + 2 < array.length && array[k * 2 + 2] < array[smallest]) {
				smallest = k * 2 + 2;
			}
			if(smallest == k) break;
			SWAP.intSwap(array, k, smallest);
			k = smallest;
		}
	}
}
