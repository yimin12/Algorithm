/**
 * 
 */
package practise.JavaAssist.entity;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月28日 上午10:42:14
* Description:
*/

public class Emp {

	private String ename;
	private int empno;
	/**
	 * @return the ename
	 */
	public String getEname() {
		return ename;
	}
	/**
	 * @param ename the ename to set
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}
	/**
	 * @return the empno
	 */
	public int getEmpno() {
		return empno;
	}
	/**
	 * @param empno the empno to set
	 */
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	/**
	 * @param ename
	 * @param empno
	 */
	public Emp(String ename, int empno) {
		super();
		this.ename = ename;
		this.empno = empno;
	}
	/**
	 * 
	 */
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void sayHello(int a){
		System.out.println("sayHello,"+a);
	}
}
