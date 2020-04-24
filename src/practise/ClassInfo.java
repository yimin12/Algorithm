/**
 * 
 */
package practise;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月24日 下午1:22:34
* Description:
*/
public class ClassInfo {

	public static void main(String[] args) {
		printClassInfo("".getClass());
		printClassInfo(Runnable.class);
		printClassInfo(java.time.Month.class);
		printClassInfo(String[].class);
		printClassInfo(int.class);
	}
	static void printClassInfo(Class clz) {
		System.out.println("ClassName : " + clz.getName());
		System.out.println("SimpleName : " + clz.getSimpleName());
		if(clz.getPackage() != null) {
			System.out.println("PackageName : " + clz.getPackageName());
		}
		System.out.println("Is Interface ? : " + clz.isInterface());
		System.out.println("Is ENum ? : " + clz.isArray());
		System.out.println("Is Primitive");
	}
}
