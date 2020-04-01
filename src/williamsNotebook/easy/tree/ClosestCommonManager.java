/**
 * 
 */
package williamsNotebook.easy.tree;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午8:20:39
* Description:
* 	-- Who is our Boss? LCA for n-array Tree
* 	In a company which has CEO Bill and a hierarchy of employees. Employees can have a list of other employees reporting 
* 	to them, which can themselves have reports, and so on. An employee with at least one report is called a manager.
* 	Please implement the closestCommonManager method to find the closest manager (i.e. farthest from the CEO) to two employees. 
* 	You may assume that all employees eventually report up to the CEO. Assume the following class which you can’t change –
* 	Given two employees in a company, find the closest common boss of the two employees.
* 	ImplementEmployee closestCommonManager(Employee e1, Employee e2)that will return closest common manager e1 and e2 directly or indirectly reports to.
* 	For example, closestCommonManager(Milton, Nina) = Peter , closestCommonManager(Nina, Porter) = Dom, closestCommonManager(Nina, Samir) = Bill, closestCommonManager(Peter, Nina) = Peter, etc.
*  			Bill    --> CEO
       /     |    \
    DOM      SAMIR  MICHAEL
   /  \   \
  Peter Bob Porter
 /     \
Milton Nina
*/
public class ClosestCommonManager {

	// Method 1: LCA Recursive for N-Ary Tree, ceo is root, emp1 is node1, emp2 is node2
	// O(n) time and O(n) space
	public Employee comManager(Employee ceo, Employee emp1, Employee emp2) {
		if(ceo == null || ceo == emp1 || ceo == emp2) return ceo;
		boolean judgeemp1 = false, judgeemp2 = false;
		for(Employee em : ceo.getReports()) {
			Employee result = comManager(em, emp1, emp2);
			if(result == emp1) {
				judgeemp1 = true;
			} else if(result == emp2) {
				judgeemp2 = true;
			}
		}
		if(judgeemp1 == true && judgeemp2 == true) {
			return ceo;
		} else if(judgeemp1 == true) {
			return emp1;
		} else if(judgeemp2 = true) {
			return emp2;
		}
		return null;
	}
}

class Employee{
	private final int id;
	private final String name;
	private final List<Employee> reports;
	public Employee(int id, String name) {
		this.id = id;
		this.name = name;
		this.reports = new ArrayList<Employee>();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the reports
	 */
	public List<Employee> getReports() {
		return reports;
	}
	
	public void addReport(Employee employee) {
		this.reports.add(employee);
	}
}
