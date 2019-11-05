/**
 * 
 */
package QueueAndStack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年11月4日 下午8:28:49
* Description:
*/

/**
 * @author 61771
 *
 */
public class MyStack {
	int[] array;
	int size;
	int capacity;
//	head never moves advance
	int head;
//	push and pop at tail, tail points at one after the last the element in the array
	int tail;
	public MyStack(int capacity) {
		if(capacity <= 0) {
			throw new IllegalArgumentException("the capacity of array should not equal to or smaller than 0");
		}
		size = 0;
		this.capacity = capacity;
		head = tail = 0;
		array = new int[capacity]; 
	}
	public void push(int element) {
		if(size == capacity) {
//			you need to expand the array like arraylist, expand 1.5 times
			capacity += (capacity >> 1);
			int[] newArray = new int[capacity];
			for(int i = 0; i < size; i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}
		array[tail++] = element;
		size++;
	}
	public Integer pop() {
		if(size == 0) {
			return null;
		}
//		tail points at one after the last the element in the array
		tail--;
		size--;
		int result = array[tail];
		return result;
	}
	
	public Integer peek() {
		if(size == 0) {
			return null;
		}
		return array[tail-1];
	}
}
