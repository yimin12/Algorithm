/**
 * 
 */
package practise.concurrency.example;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月29日 下午4:52:40
* Description:
*/
public class StaticProxy {

	public static void main(String[] args) {
		Marry you = new You();
		WeddingCompany company = new WeddingCompany(you);
		company.marry();
	}
}

interface Marry{
	public abstract void marry();
}

class You implements Marry{

	@Override
	public void marry() {
		System.out.println("You Marry somebody");
	}
}

class WeddingCompany implements Marry{

	public Marry you;
	public WeddingCompany(Marry you) {
		this.you = you;
	}
	
	private void before() {
		System.out.println("You need to invite your friend and your parent");
	}
	
	
	private void after() {
		System.out.println("You need take care of friends and parent you invited");

	}
	@Override
	public void marry() {
	
		before();
		you.marry();
		after();
	}
}
