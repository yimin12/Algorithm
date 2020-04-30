/**
 * 
 */
package practise.concurrency.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月21日 下午11:59:49
* Description:
*/
public class TestThreadLocal {
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(3);
		String[] users = new String[]{"Bob", "Alice", "Tim", "Mike", "Lily", "Jack", "Bush"};
		for(String user : users) {
			es.submit(new Task131(user));
		}
		es.awaitTermination(3, TimeUnit.SECONDS);
		es.shutdown();
	}
}
class UserContext implements AutoCloseable{
	private static final ThreadLocal<String> userThreadLocal = new ThreadLocal<String>();
	
	public UserContext(String name) {
		userThreadLocal.set(name);
		System.out.printf("[%s] init user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
	}

	public static String getCurrentUser() {
		return userThreadLocal.get();
	}
	@Override
	public void close() throws Exception {
		System.out.printf("[%s] cleanup for user %s...\n", Thread.currentThread().getName(),UserContext.getCurrentUser());
		userThreadLocal.remove();
	}
	
}


class Task131 implements Runnable{
	final String userName;
	
	public Task131(String userName) {
		this.userName = userName;
	}

	@Override
	public void run() {
		try(var ctx = new UserContext(this.userName)){
			new Task13111().process();
			new Task1312().process();
			new Task1313().process();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
class Task13111{
	public void process() {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			
		}
		System.out.printf("[%s] check user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
	}
}

class Task1312{
	public void process() {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			
		}
		System.out.printf("[%s] check user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
	}
}

class Task1313{
	public void process() {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			
		}
		System.out.printf("[%s] check user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
	}
}
