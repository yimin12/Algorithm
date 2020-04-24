/**
 * 
 */
package practise.concurrency.example;

import java.util.concurrent.CompletableFuture;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月21日 下午10:35:52
* Description:
*/
public class TestCompletableFuture {

	public static void main(String[] args) throws InterruptedException {
		//	 创建异步执行任务:
		CompletableFuture<Double> cf1 = CompletableFuture.supplyAsync(TestCompletableFuture::fetchPrice);
		// if operation success
		cf1.thenAccept((result) -> {
			System.out.println("price : " + result);
		});
		// if operation fail
		cf1.exceptionally((e)-> {
			e.printStackTrace();
			return null;
		});
        // 	主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
		Thread.sleep(2000);
		
	}
	
	static Double fetchPrice() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(Math.random() < 0.3) {
			throw new RuntimeException("fetch price failed");
		}
		return 5 + Math.random() * 20;
	}
}
