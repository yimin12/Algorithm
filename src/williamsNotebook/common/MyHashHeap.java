/**
 * 
 */
package williamsNotebook.common;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月27日 下午11:13:23
* Description:
*/

public class MyHashHeap {

	ArrayList<Integer> heap;
	String mode;
	int size_t;
	HashMap<Integer, Node> hash;
	
	class Node{
		public Integer id, num;
		Node(Node now){
			this.id = now.id;
			this.num = now.num;
		}
		Node(Integer first, Integer second){
			this.id = first;
			this.num = second;
		}
	}
	public MyHashHeap(String mod) {
		// if you parse min, means a minHeap, else maxHeap
		this.heap = new ArrayList<Integer>();
		mode = mod;
		this.hash = new HashMap<Integer, Node>();
		this.size_t = 0;
	}
	int peak() {
		return heap.get(0);
	}
	int size() {
		return this.size_t;
	}
	boolean isEmpty() {
		return heap.size() == 0;
	}
	int parent(int id) {
		if(id == 0) {
			return -1;
		}
		return (id - 1)/2;
	}
	int leftSon(int id) {
		return id*2 + 1;
	}
	int rightSon(int id) {
		return id*2 + 2;
	}
	boolean compareSmall(int a, int b) {
		if(a <= b) {
			if(mode == "min") {
				return true;
			} else return false;
		} else {
			if(mode == "max") return true;
			else return false;
		}
	}
	void swap(int left, int right) {
		int valueA = heap.get(left);
		int valueB = heap.get(right);
		int numA = hash.get(valueA).num;
		int numB = hash.get(valueB).num;
		hash.put(valueB, new Node(left, numB));
		hash.put(valueA, new Node(right, numA));
		heap.set(left, valueB);
		heap.set(right, valueA);
	}
	Integer poll() {
		this.size_t--;
		Integer now = heap.get(0);
		Node hashNow = hash.get(now);
		if(hashNow.num == 1) {
			swap(0, heap.size() - 1);
			hash.remove(now);
			heap.remove(heap.size() - 1);
			if(heap.size() > 0) {
				procolateDown(0);
			}
		} else {
			hash.put(now, new Node(0, hashNow.num - 1));
		}
		return now;
	}
	void add(int now) {
		this.size_t++;
		if(hash.containsKey(now)) {
			Node hashNow = hash.get(now);
			hash.put(now, new Node(hashNow.id,hashNow.num + 1));
		} else {
			heap.add(now);
			hash.put(now, new Node(heap.size() - 1, 1));
		}
		procolateUp(heap.size() - 1);
	}
	void delete(int now) {
		size_t--;
		Node hashNow = hash.get(now);
		int id = hashNow.id;
		int num = hashNow.num;
		if(hashNow.num == 1) {
			swap(id,  heap.size() - 1);
			hash.remove(now);
			heap.remove(heap.size() - 1);
			if(heap.size() > id) {
				procolateUp(id);
				procolateDown(id);
			}
		} else {
			hash.put(now, new Node(id, num - 1));
		}
	}
	void procolateUp(int id) {
		while(parent(id) > -1) {
			int parentId = parent(id);
			if(compareSmall(heap.get(parentId), heap.get(id))) {
				break;
			} else {
				swap(id, parentId);
			}
			id = parentId;
		}
	}
	void procolateDown(int id) {
		while(leftSon(id) < heap.size()) {
			int leftId = leftSon(id);
			int rightId = rightSon(id);
			int son;
			if(rightId >= heap.size() || (compareSmall(heap.get(leftId), heap.get(rightId)))) {
				son = leftId;
			} else {
				son = rightId;
			}
			if(compareSmall(heap.get(id), heap.get(son))) {
				break;
			} else {
				swap(id,  son);
			}
			id = son;
		}
	}
}
