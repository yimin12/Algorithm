/**
 * 
 */
package williamsNotebook.easy.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月10日 下午8:37:52
* Description:
* 	Demo 10 workers working together
*/
public class Workers implements Runnable{

	// set a flag for running state
	public boolean running = false;
	public Workers() {
		Thread thread = new Thread();
		System.out.println("In constructor , now ID = " + Thread.currentThread().getId());
		thread.start();
		thread.run();
		System.out.println("Finished run().");
	}
	
	public static void main(String... args) throws InterruptedException {
		List<Workers> workers = new ArrayList<Workers>();
		System.out.println("Main thread is running with thread ID: " + Thread.currentThread().getId());
		Date start = new Date();
		// start 10 workers by the main thread
		for(int i = 0; i < 10; i++) {
			System.out.println("i = " + i);
			workers.add(new Workers());
		}
		// now we need to force the MAIN thread to wait until all worker threads end, so we could calculate the duration
		for(Workers worker : workers) {
			System.out.println("Checking thread " + Thread.currentThread().getId() + " now.");
			System.out.println("worker.running = " + worker.running);
			while(worker.running) {
				Thread.sleep(1000);
			}
		}
		Date end = new Date();
		Long duration = end.getTime() - start.getTime();
		System.out.println("The whole process took " + duration/1000 + " seconds");
	}
	
	@Override
	public void run() {
		this.running = true;
		System.out.println("Thread " + Thread.currentThread().getId() + " is running");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}
		this.running = false;
	}
}
