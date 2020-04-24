/**
 * 
 */
package practise.concurrency.example;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月21日 下午11:33:25
* Description:
* 	我们来看如何使用Fork/Join对大数据进行并行求和
*/
public class TestForkJoin {

	public static void main(String[] args) {
		long[] array = new long[2000];
		long exceptedSum = 0;
		for(int i = 0; i < array.length; i++) {
			array[i] = random();
			exceptedSum += array[i];
		}
		System.out.println("Excepted Sum : " + exceptedSum);
		ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
		long startTime = System.currentTimeMillis();
		Long result = ForkJoinPool.commonPool().invoke(task);
		long endTime = System.currentTimeMillis();
		System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
	}
	static Random random = new Random(0);
	static long random() {
		return random.nextInt(10000);
	}
}

class SumTask extends RecursiveTask<Long>{
	static final int THRESHOLD = 500;
	long[] array;
	int start;
	int end;
	
	SumTask(long[] array, int start, int end){
		this.array = array;
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Long compute() {
		// base case
		if(end-start <= THRESHOLD) {
			long sum = 0;
			for(int i = start; i < end; i++) {
				sum += this.array[i];
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			return sum;
		}
		int middle = (end - start)/2 + start;
		System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
		SumTask subtask1 = new SumTask(this.array, start, middle);
		SumTask subtask2 = new SumTask(this.array, middle, end);
		invokeAll(subtask1, subtask2);
		Long subresult1 = subtask1.join();
		Long subresult2 = subtask2.join();
		Long result = subresult1 + subresult2;
		System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
		return result;
				
	}
}
