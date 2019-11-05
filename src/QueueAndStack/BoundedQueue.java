/**
 * 
 */
package QueueAndStack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年11月4日 下午7:46:36
* Description:
* 	We have simpler case, assume that the capacity of the queue is bounded. And we only need to
* 	maintain the position of head and tail by using two pointer
* 	offer -> put one element, tail++ (tail pointer to the next available position)
* 	poll -> grab element at head, head++ (head points to the next element in queue)
* Key insight:
* 	What is the problem here? what if tail == array.length - 1 and we need to do tail++;
* 	We use Circular Array - we can connect teh start and the end of the array, so that it is a cycle
* 	index array.length <-> index of 0
* 	 head = (head+1==array.length) ? 0 : head + 1; 
* or head = (head + 1) % array.length  
* 	Secondary, we need to know when is empty, and when is full
* 	head == tail -> empty
* 	head == tail -> full
* 	two solutions:
* 		1.record thes size, size = 0 (empty), size = array.length (full)
* 		2.head + 1 = tail (empty), head = tail + 1 (full), capacity = array.length - 1 
* 	
*/


public class BoundedQueue {

	int head;
	int tail;
	int size;
	Integer[] array;
	public BoundedQueue(int cap) {
		array = new Integer[cap];
		head = tail = 0;
		size = 0;
	}
	public boolean offer(Integer ele) {
//		1st method to know whether it is full
		if(size == array.length) {
			return false;
		}
		array[tail] = ele;
		tail = tail + 1 == array.length ? 0 : tail+1 ;
		size++;
		return true;
	}
	public Integer peek() {
		if(size == 0) {
			return null;
		}
		return array[head];
	}
	public Integer poll() {
		if(size == 0) {
			return null;
		}
		Integer result = array[head];
		head = head + 1 == array.length ? 0 : head +1;
//		head = (head + 1) % array.length
		size --;
		return result;
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public boolean isFull() {
		return size == array.length;
	}
}
