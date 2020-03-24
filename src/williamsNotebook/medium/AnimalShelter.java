/**
 * 
 */
package williamsNotebook.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月23日 下午8:26:06
* Description:
* 	An animal shelter holds only dogs and cats, and operates on a strictly "first in, first out" basis. People must adopt either the "oldest"
*  (based on arrival time) of all animals at the shelter, or they can select whether they would prefer a dog or a cat 
*  (and will receive the oldest animal of that type). They cannot select which specific animal they would like. Create the data structures to maintain 
*  	this system and implement operations such as enqueue, dequeueAny, dequeueDog and dequeueCat
* Example:
* int CAT = 0   int DOG = 1
*   enqueue("james", DOG);
	enqueue("tom", DOG);
	enqueue("mimi", CAT);
	dequeueAny();  // should return "james"
	dequeueCat();  // should return "mimi"
	dequeueDog();  // should return "tom"
*/
public class AnimalShelter {
	
	static class Node{
		int time;
		String name;
		Node(String name, int time){
			this.name = name;
			this.time = time;
		}
		public String getName() {
			return this.name;
		}
		public int getTime() {
			return this.time;
		}
	}
	// Method 1: implement with two queues
	private static final int DOG = 1;
	private static final int CAT = 0;
	private int count;
	private LinkedList<Node> cats, dogs;
	public AnimalShelter() {
		this.count = 0;
		this.cats = new LinkedList<Node>();
		this.dogs = new LinkedList<Node>();
	}
	// We only need to maintain the relative sequence, so count is perfect for time fields of Node
	public void enqueue(String name, int type) {
		this.count += 1;
		if(type == DOG) {
			dogs.add(new Node(name, count));
		} else {
			cats.add(new Node(name, count));
		}
	}
	public String dequeAny() {
		if(cats.isEmpty()) {
			return dequeDog();
		} else if(dogs.isEmpty()) {
			return dequeCat();
		} else {
			int dogTime = dogs.getFirst().getTime();
			int catTime = cats.getFirst().getTime();
			if(catTime < dogTime) {
				return dequeCat();
			} else {
				return dequeDog();
			}
		}
	}
	public String dequeDog() {
		String name = dogs.getFirst().getName();
		dogs.removeFirst();
		return name;
	}
	public String dequeCat() {
		String name = cats.getFirst().getName();
		cats.removeFirst();
		return name;
	}
}


// Method 2: Implement it with single queue
class AnimalShelterSingle{
	
	static class Node{
		String name;
		int type;
		Node(String name, int type){
			this.name = name;
			this.type = type;
		}
		
	}
	Queue<Node> queue;
	public AnimalShelterSingle() {
		queue = new LinkedList<Node>();
	}
	void enqueue(String name, int type) {
		queue.offer(new Node(name, type));
	}
	public String dequeueAny() {
		Node node = queue.poll();
		return node.name;
	}
	public String dequeueDog() {
		return dequeueType(1);
	}
	public String dequeueCat() {
		return dequeueType(0);
	}
	private String dequeueType(int type) {
		int shiftTime = 0;
		while(queue.peek().type != type) {
			queue.offer(queue.poll());
			shiftTime++;
		}
		Node node = queue.poll();
		shiftTime = queue.size() - shiftTime;
		while(shiftTime > 0) {
			queue.offer(queue.poll());
			shiftTime--;
		}
		return node.name;
	}
}
