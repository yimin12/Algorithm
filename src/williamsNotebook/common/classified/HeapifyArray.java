/**
 * 
 */
package williamsNotebook.common.classified;

import williamsNotebook.common.SWAP;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2020��3��26�� ����10:49:29
* Description:
* 	Given an integer array, heapify it into a min-heap array.
* 	For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
* 	What is heap? Heap is a data structure, which usually have three methods: push, pop and top. where "push" add a new element the heap, "pop" delete
* 		 the minimum/maximum element in the heap, "top" return the minimum/maximum element
* 	What is heapify? Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].
* 	What if there is a lot of solutions? Return any of them.
* 	Given [3,2,1,4,5], return [1,2,3,4,5] or any legal heap array.
* 	O(n) time complexity
*   Heapifyһ��Array��Ҳ���Ƕ�array�е�Ԫ�ؽ���siftup����siftdown�Ĳ���������min heap������в������ɡ�
*     ����ֵ��ע����ǣ�����ɨ������array������£�siftup��siftdown��complexity�ϵ�����
*     ������ԭ�����ڣ�siftdown��complexity��ʵ������node�����bottom�ƶ��Ĵ�����������binary heap��������ԣ�������Լ����bottom��nodeԽ�ࣻ����յ���siftup����node�����root�ڵ���ƶ�������
*    ���Heapify������O(n)ʵ�֣���ôHeapSort�����ʱ�临�Ӷ�Ϊ����O(nlogn)����ΪHeapSort��ʵ�������������裬��һ����Heapify��build (min) heap������ʱ�临�Ӷ�O(n)���ڶ���������ɾ����Сֵ��min heap����
*    ����Heap��˵��ɾ�������ĸ��Ӷ���O(logn)�� �����ɾ����Ҫִ��O(n)�����õ�����sort�Ľ������������ʱ�临�Ӷ���O(nlogn)��  
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
