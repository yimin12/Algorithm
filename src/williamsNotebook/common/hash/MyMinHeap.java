package williamsNotebook.common.hash;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

public class MyMinHeap {
	// this MinHeap can take interger rather than some genric type
	private int[] array;
	private int size;
	
	// Constructor
	public MyMinHeap(int[] array) {
		// the capacity of minHeap should greater than 0
		if(array == null || array.length == 0) {
			throw new IllegalArgumentException("input array can not be null or empty");
		}
		this.array = array;
		size = array.length;
		// the minHeap should be sorted by hepify
		heapify();
	}
	// heapify() by using percolateDown	
	private void heapify() {
		// According procolateDown from the last parent root to global root
		for(int i = size/2- 1; i >= 0; i--) {
			percolateDown(i);
		}
	}
	// Another Constructor instantiated by capacity
	public MyMinHeap(int cap) {
		if(cap <= 0) {
			throw new IllegalArgumentException("capacity can not be <= 0");
		}
		array = new int[cap];
		size = 0;
	}
	// The two most important method for heapify (percolateUp and percolateDown)
	private void percolateUp(int index) {
		while(index > 0) {
			int parentIndex = (index- 1) /2;
			if(array[index] < array[parentIndex]) {
				swap(array, parentIndex, index);
			} else {
				// it means that the value at the specific index has already found the suitable place
				break;
			}
			index = parentIndex;
		}
	}
	private  void percolateDown(int index) {
		// because the method is going down, and it require the root has at least one child, you need to check whether it is legal
		while(index < size/2 - 1) {
			int rightChildIndex = index * 2 + 2;
			int leftChildIndex = index * 2 + 1;
			// you need to determine which childroot are going to swap, you should create an variable swapCandidate
			int swapCandidate = leftChildIndex; // smallest one among leftChildIndex and rightChildIndex
			if(rightChildIndex < size - 1 && array[leftChildIndex] >= array[rightChildIndex]) {
				swapCandidate = rightChildIndex;
			}
			// determine whether should swap
			if(array[index] > array[swapCandidate]) {
				swap(array, index, swapCandidate);
			} else break;
			// after swaping, update the index to swapCandidate
			index = swapCandidate;
		}
	}
	private void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	// basic method implemented for minHeap
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public boolean isFull() {
		return size == array.length;
	}
	public int peek() {
		if(size==0) {
			throw new NoSuchElementException("currently, the heap is empty");
		}
		return array[0];
	}
	public int poll() {
		if(size==0) {
			throw new NoSuchElementException("currently, the heap is empty");
		}
		int result = array[0];
		// when polling the element from the top, replace it with last element in this heap
		array[0] = array[size - 1];
		size--;
		percolateDown(0);
		return result;
	}
	public void offer(int value) {
		if(size == array.length) {
			// expand the array like what arraylist do
			int[] newArray = new int[size + size >> 2];
			// copy each element from array to new array like arrayList do
			// newArray=Arrays.copyOf(array, size);
			copy(array, newArray);
			array = newArray;
		}
		array[size] = value;
		size++;
		percolateUp(size-1);
	}
	private void copy(int[] array, int[] newArray) {
		if(array.length == 0) {
			return;
		}
		for(int i = 0; i<array.length; i++) {
			array[i] = newArray[i];
		}
	}
	public int update(int index, int target) {
		if(index < 0 || index > size-1) {
			throw new ArrayIndexOutOfBoundsException("invalid index range");
		}
		int result = array[index];
		array[index] = target;
		if(result > target) {
			percolateUp(index);
		} else {
			percolateDown(index);
		}
		return result;
	}
}
