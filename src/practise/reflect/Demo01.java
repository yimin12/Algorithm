/**
 * 
 */
package practise.reflect;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月28日 下午2:51:52
* Description:
*/

public class Demo01 {
	
	public static void main(String[] args) {
		String path = "practise.reflect.bean.User";
		
		try {
			
			Class clz = Class.forName(path);
			System.out.println(clz.hashCode());
			
			Class clz2 = Class.forName(path);
			System.out.println(clz == clz2); // true
			
			Class intClass = int.class;
			int[] array1 = new int[10];
			int[][] matrix = new int[30][0];
			int[] array2 = new int[30];
			double[] array3 = new double[30]; 
			System.out.println(array1.hashCode());
			System.out.println(array2.hashCode());
			System.out.println(array3.hashCode());
			System.out.println(matrix.hashCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
