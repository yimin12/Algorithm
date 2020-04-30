/**
 * 
 */
package practise.concurrency.example;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月29日 下午7:00:51
* Description:
*/

public class MoiveShow {

	public static void main(String[] args) throws Exception{
		Moive moive = new Moive();
		Player player = new Player(moive);
		Watcher watcher = new Watcher(moive);
		
		new Thread(player).start();
		new Thread(watcher).start();
	}
}

class Moive{
	private String movieName;
	private boolean flag = true; // if the movie is showing
	
	public synchronized void play(String moiveName) {
		if(!flag) {
			try {
				this.wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(500);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("The showing movie's name is " + moiveName);
		this.movieName = moiveName;
		this.notify();
		this.flag = false;
	}
	
	public synchronized void watch() {
		if(flag) {
			try {
				this.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("I am watching the " + movieName);
		this.notifyAll();
		this.flag = true;
	}
}
class Watcher implements Runnable{

	Moive moive;
	
	public Watcher(Moive moive) {
		super();
		this.moive = moive;
	}
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			moive.watch();
		}	
	}	
}
class Player implements Runnable{

	Moive moive;
	
	public Player(Moive moive) {
		super();
		this.moive = moive;
	}
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			if(i % 2 == 0) {
				moive.play("yuhuanshui");
			} else {
				moive.play("qingyunian");
			}
		}
		
	}
	
}