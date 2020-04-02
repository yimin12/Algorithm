/**
 * 
 */
package williamsNotebook.easy.binarySearch;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��7�� ����11:13:02
* Description: Test all the result, then test in JUnit
*/

/**
 * @author 61771
 *
 */
public class Test {
	public static void main(String[] args) {
		ClassicalBinarySearch classicalBinarySearch = new ClassicalBinarySearch();
		int[] test = new int[] {-3,-1,1,2,3,5,6};
		int result = classicalBinarySearch.binarySearch(test, 2);
		
		System.out.println(result);
	}
}
