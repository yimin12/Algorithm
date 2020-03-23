/**
 * 
 */
package williamsNotebook.common.QueueAndStack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月23日 下午2:48:38
* Description:
* 	Design your implementation of the circular queue. The circular queue is a linear data structure in which the 
* 	operations are performed based on FIFO (First In First Out) principle and the last position is connected back 
* 	to the first position to make a circle. It is also called "Ring Buffer".
* 	One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. 
* 	In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space 
* 	in front of the queue. But using the circular queue, we can use the space to store new values.
* 	Your implementation should support following operations:
	    MyCircularQueue(k): Constructor, set the size of the queue to be k.
		Front: Get the front item from the queue. If the queue is empty, return -1.
		Rear: Get the last item from the queue. If the queue is empty, return -1.
		enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
		deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
		isEmpty(): Checks whether the circular queue is empty or not.
		isFull(): Checks whether the circular queue is full or not.

*/

public class MyCircularQueue {

	// Implement by an cicular array
	private int capacity;
	private int rear, front;
	private int[] queue;
	
	// Initialize the data structor here
	public MyCircularQueue(int capacity) {
		this.capacity = capacity;
		this.queue = new int[capacity];
		this.front = 0;
		this.rear = -1;
	}
	// API for whether the circular queue is full or not
	public boolean isFull() {
		return capacity == queue.length;
	}
	// API for whether the circular queue is empty or not
	public boolean isEmpty() {
		return capacity == 0;
	}
	// Insert an element into circular queue. Return true if the operation is successful
	public boolean enQueue(int value) {
		if(isFull()) {
			return false;
		}
		rear = (rear + 1) % queue.length;
		queue[rear] = value;
		capacity++;
		return true;
	}
	// Delete an element from the circular queue, return true if the operation is successful
	public boolean deQueue() {
		if(isEmpty()) {
			return false;
		}
		// you do not need to erase corresponding value, you can just move the front index
		// the valid elements are always between front and rear
		front = (front + 1)%queue.length;
		capacity--;
		return true;
	}
	public int Rear() {
		return isEmpty() ? -1 : queue[rear];
	}
	public int Front() {
		return isEmpty() ? - 1 : queue[front];
	}
}

// Official design
class CircularQueue{
	
	private int[] data;
	private int head;
	private int tail;
	private int size;
	
	public CircularQueue(int k) {
		this.data = new int[k];
		this.head = -1;
		this.tail = -1;
		this.size = k;
	}
	// when the head and next tail has encountered, this queue is full
	public boolean isFull() {
		return ((tail + 1)%size) == head;
	}
	public boolean isEmpty() {
		return head == -1;
	}
	public boolean enQueue(int val) {
		if(isFull()) return false;
		if(isEmpty()) head = 0;
		tail = (tail + 1) % size;
		data[tail] = val;
		return true;
	}
	public boolean deQueue() {
		if(isEmpty()) return false;
		// In this case, means that there is only one element left
		if(head == tail) {
			head = -1;
			tail = -1;
			return true;
		}
		head = (head + 1) % size;
		return true;
	}
	public int Front() {
		if(isEmpty()) {
			return -1;
		}
		return data[head];
	}
	public int Reat() {
		if(isEmpty()) {
			return -1;
		}
		return data[tail];
	}
}
