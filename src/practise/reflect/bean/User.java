/**
 * 
 */
package practise.reflect.bean;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月28日 下午2:49:14
* Description:
*/
public class User {

	private int id;
	private int age;
	private String username;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param id
	 * @param age
	 * @param username
	 */
	public User(int id, int age, String username) {
		super();
		this.id = id;
		this.age = age;
		this.username = username;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", age=" + age + ", username=" + username + "]";
	}
	/**
	 * 
	 */
	public User() {
		super();
	}
	
	public void sayHello(int a){
		System.out.println("sayHello,"+a);
	}
	
}
