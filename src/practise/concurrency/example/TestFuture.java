/**
 * 
 */
package practise.concurrency.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月21日 下午10:01:58
* Description:
*/
public class TestFuture {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newFixedThreadPool(4);
		Future<BigDecimal> future = es.submit(new Task1("601959"));
		System.out.println(future.get());
		es.shutdown();
	}
	
}

class Task1 implements Callable<BigDecimal>{
	
	public Task1(String code) {
		
	}
	

	@Override
	public BigDecimal call() throws Exception {
		Thread.sleep(1000);
		double d = 5 + Math.random() * 20;
		return new BigDecimal(d).setScale(2, RoundingMode.DOWN);
	}
	
}