/**
 * 
 */
package practise.concurrency.example;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月29日 下午7:19:59
* Description:
*/
public class TestProducerConsumer {

	public static void main(String[] args) {
		
		
		
		
		SynStack stack = new SynStack();
		Purchaser purchaser = new Purchaser(stack);
		Seller seller = new Seller(stack);
		
		new Thread(purchaser).start();
		new Thread(seller).start();
	}
}


class Stock {
	private int stockId;

	/**
	 * @param stockId
	 */
	public Stock(int stockId) {
		super();
		this.stockId = stockId;
	}
	public int getStockId() {
		return this.stockId;
	}
}
// buffer : a concurrent container
class SynStack{
	int index = 0;
	Stock[] stocks = new Stock[10]; // Assuming the fixed size is 10
	
	public synchronized void push(Stock stock) {
		while(index == stocks.length) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		stocks[index] = stock;
		index++;
	}
	
	public synchronized Stock pop() {
		while(index == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		return stocks[--index];
	}
}
class Purchaser implements Runnable{ // Acting as Producer
	SynStack buffer = null;
	public Purchaser(SynStack buffer) {
		this.buffer = buffer;
	}
	@Override
	public void run() {
		for(int i = 0; i < 30; i++) {
			System.out.println("purchasing the stock " + i);
			Stock stock = new Stock(i);
			buffer.push(stock);
		}
	}
}
// Acting as Seller
class Seller implements Runnable{
	SynStack buffer = null;
	public Seller(SynStack buffer) {
		this.buffer = buffer;
	}
	@Override
	public void run() {
		for(int i = 0; i < 30; i++) {
			Stock stock = buffer.pop();
			System.out.println("We are selling the stock " + stock.getStockId());
		}
	}
}
