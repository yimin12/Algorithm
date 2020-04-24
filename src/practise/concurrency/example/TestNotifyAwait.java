/**
 * 
 */
package practise.concurrency.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月21日 下午2:05:59
* Description:
*/
public class TestNotifyAwait {

	public static void main(String[] args) throws InterruptedException {
		var q = new TaskQueue(); // TaskQueue q = new TaskQueue(); can be used the sames as auto in c++
		var ts = new ArrayList<Thread>(); // Can use thread Local here
		for(int i = 0; i < 5; i++) {
			Thread t = new Thread("Thread " + i) {
				public void run() {
					while(true) {
						try {
							String string = q.getTask();
							System.out.println("execute task: " + Thread.currentThread()+ string);
						} catch (Exception e) {
							return;
						}
					}
				};
			};
			t.start();
			ts.add(t);
		}
		var add = new Thread(()->{
			for(int i = 0; i < 10; i++) {
				String s = "t-" + Math.random();
				System.out.println("add task: " + s);
				q.addTask(s);
				try {Thread.sleep(100);} catch (Exception e) {}
			}
		});
		add.start();
		add.join();
		Thread.sleep(100);
		for(var t : ts) {
			t.interrupt();
		}
	}
}

class TaskQueue{
	Queue<String> queue = new LinkedList<String>();
	
	public synchronized void addTask(String s) {
		this.queue.add(s);
		this.notifyAll();
	}
	
	public synchronized String getTask() throws InterruptedException{
		while(queue.isEmpty()) {
			this.wait();
		}
		return queue.remove();
	}
	
}
