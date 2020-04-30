/**
 * 
 */
package practise.reflect.annotation;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月28日 下午9:01:12
* Description:
*/

@SxtTable("tb_student")
public class SxtStudent {

	@SxtField(columnName = "id", type = "int", length = 10)
	private int id;
	@SxtField(columnName = "sname", type = "varchar", length = 10)
	private String studentName;
	@SxtField(columnName = "age", type = "int", length = 3)
	private int age;

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
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	
	
}
