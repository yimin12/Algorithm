/**
 * 
 */
package practise.concurrency.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月29日 下午3:37:38
* Description:
* 
* 	simulate tortoise and rabbit race
*/

public class TestCallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newFixedThreadPool(2);
		Race tortoise = new Race("wangba", 1000); // tortoise need 1000 ms to finish one step
		Race rabbit = new Race("tuzi", 500); // rabbit need 500 ms to finish one step
		
		Future<Integer> result1 = es.submit(tortoise);
		Future<Integer> result2 = es.submit(rabbit);
		
		Thread.sleep(2000);
		tortoise.setFlag(false);
		rabbit.setFlag(false);
		
		int num1 = result1.get();
		int num2 = result2.get();
		
		System.out.println("tortoise : " + num1 + "....");
		System.out.println("rabbit : " + num2 + "....");
		es.shutdown();
	}
}

class Race implements Callable<Integer>{
	private String name;
	private long time;
	private boolean flag = true;
	private int step = 0;
	/**
	 * 
	 */
	public Race() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param name
	 * @param time
	 */
	public Race(String name, long time) {
		super();
		this.name = name;
		this.time = time;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}
	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	/**
	 * @return the step
	 */
	public int getStep() {
		return step;
	}
	/**
	 * @param step the step to set
	 */
	public void setStep(int step) {
		this.step = step;
	}
	@Override
	public Integer call() throws Exception {
		while(flag) {
			Thread.sleep(time);
			step++;
		}
		return step;
	}
	
	
}