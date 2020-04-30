/**
 * 
 */
package practise.JavaAssist;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月28日 上午11:20:34
* Description:
*/
public class Test02 {

	/**
	 * basic operations
	 * @param args
	 */
	public static void test01() throws Exception{
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("practise.JavaAssist.entity.Emp");
		
		byte[] b = cc.toBytecode();
		System.out.println(Arrays.toString(b));
		
		System.out.println(cc.getName());
		System.out.println(cc.getSimpleName());
		System.out.println(cc.getSuperclass());
		System.out.println(cc.getInterfaces());
	}
	
	public static void test02() throws Exception{
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("practise.JavaAssist.entity.Emp");
		
//		CtMethod m = CtNewMethod.make("public int add(int a,int b){return a+b;}", cc);
		
		CtMethod m = new CtMethod(CtClass.intType,"add",
				new CtClass[]{CtClass.intType,CtClass.intType},cc);
		m.setModifiers(Modifier.PUBLIC);
		m.setBody("{System.out.println(\"www.yimin.cn\");return $1+$2;}");
		
		cc.addMethod(m);
	
		Class<?> clazz = cc.toClass();
		Constructor<?> constructor = clazz.getDeclaredConstructor();
		Object object = constructor.newInstance();
		Method method = clazz.getDeclaredMethod("add", int.class,int.class);
		Object result = method.invoke(object, 200,300);
		System.out.println(result);
	}
	
	public static void test03() throws Exception{
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("practise.JavaAssist.entity.Emp");
		CtMethod cm = cc.getDeclaredMethod("sayHello", new CtClass[] {CtClass.intType});
		
		// AOP programming
		cm.insertBefore("System.out.println($1); System.out.println(\"start!!!\");");
		cm.insertAt(9, "int b = 3; System.out.println(\"b =\" + b);");
		cm.insertAfter("System.out.println(\"end!!!\");");
		
		Class<?> clz = cc.toClass();
		Constructor<?> constructor = clz.getDeclaredConstructor();
		Object obj = constructor.newInstance();
		Method method = clz.getDeclaredMethod("sayHello", int.class);
		method.invoke(obj, 300);
//		Return value:		
//		300
//		start!!!
//		b =3
//		sayHello,300
//		end!!!

	}
	
	public static void test04() throws Exception{
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("practise.JavaAssist.entity.Emp");
		
		CtField f1 = new CtField(CtClass.intType, "salary", cc);
		f1.setModifiers(Modifier.PRIVATE);
		cc.addField(f1);
		cc.addMethod(CtNewMethod.getter("getSalary", f1));
		cc.addMethod(CtNewMethod.setter("setSalary", f1));
		// test 
		Class<?> clz = cc.toClass();
		Constructor<?> constructor = clz.getDeclaredConstructor();
		Object obj = constructor.newInstance();
		Method method = clz.getDeclaredMethod("setSalary", int.class);
		Method method2 = clz.getDeclaredMethod("getSalary", null);
		method.invoke(obj, 1000);
		Object result = method2.invoke(obj);
		System.out.println(result);
	}
	
	public static void test05() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("practise.JavaAssist.entity.Emp");
		
		CtConstructor[] cs = cc.getConstructors();
		for (CtConstructor c : cs) {
			System.out.println(c.getLongName());
		}
	}
	public static void main(String[] args) throws Exception {
		//test01();
		//test02();
		//test03();
		//test04();
		test05();

	}
}
