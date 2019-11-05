/**
 * 
 */
package QueueAndStack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年11月4日 下午8:05:07
* Description:
* 	Compare to the BoundedQueue, you only need one pointer head;
*/

public class BoundedStack {
	int[] array;
	int head;
	public BoundedStack(int cap) {
		if(cap < 0) {
			throw new IllegalArgumentException("The capacity of array could not smaller 0");
		}
		array = new int[cap];
		head = -1;
	}
	public boolean push(int ele) {
		if(head == array.length - 1) {
			return false;
		}
		array[++head] = ele;
		return true;
	}
	public Integer pop() {
		return head == -1 ? null : array[head--];
	}
	public Integer top() {
		return head == -1 ? null : array[head];
	}
}
