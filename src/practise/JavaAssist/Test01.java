/**
 * 
 */
package practise.JavaAssist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月28日 上午10:39:43
* Description:
*/
public class Test01 {

	public static void main(String[] args) throws Exception{
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass("com.yimin.Emp");
		
		// create field(local variable)
		CtField f1 = CtField.make("private int empno;", cc);
		CtField f2 = CtField.make("private String ename;", cc);
		cc.addField(f1);
		cc.addField(f2);

		// create method
		CtMethod m1 = CtMethod.make("public int getEmpno(){return empno;}", cc);
		CtMethod m2 = CtMethod.make("public void setEmpno(int empno){this.empno = empno;}", cc);
		cc.addMethod(m1);
		cc.addMethod(m2);
		
		// create constructor
		CtConstructor constructor = new CtConstructor(new CtClass[] {CtClass.intType, pool.get("java.lang.String")}, cc);
		constructor.setBody("{this.empno = empno; this.ename = ename;}");
		cc.addConstructor(constructor);
		cc.writeFile("e:/test"); // output the class to e:/test
		
	}
}
