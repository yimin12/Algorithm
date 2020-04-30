/**
 * 
 */
package practise.concurrency.example;

import java.util.concurrent.CompletableFuture;

/**
 * @author yiminH-mail:hymlaucs@gmail.com
 * @version Create Time：2020年4月21日 下午11:00:12 Description:
 */
public class TestCompletableFutureI {

	public static void main(String[] args) throws InterruptedException {
		TestCompletableFutureI test = new TestCompletableFutureI();
		CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
			return queryCode("中国石油");
		});
		// cfQuery成功后继续执行下一个任务:
		CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync((code) -> {
			return fetchPrice(code);
		});
		// cfFetch成功后打印结果:
		cfFetch.thenAccept((result) -> {
			System.out.println("price: " + result);
		});
		// 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
		Thread.sleep(2000);
	}

	static String queryCode(String code) {
		try {
			Thread.sleep(500);
		} catch (Exception e) {

		}
		return code;
	}

	static Double fetchPrice(String code) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

		}
		return 5 + Math.random() * 20;
	}
}
