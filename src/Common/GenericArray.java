package Common;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月22日 下午7:04:59
* Description:
* 	The general rule is to use wildcards when you can because code with wildcards is generally more readable than code with multiple
*	type parameters. When deciding if you need a type variable, ask yourself if that type variable is used to relate two or more parameters
*	or to relate a parameter type with the return type. If the answer is no, then a wildcard should suffice.
*/

public class GenericArray {
	// this function will throw an exception
	public static void normalList() {
		Integer[] iArray = new Integer[2];
		Object[] oArray = iArray;
		oArray[0] = 1;
		oArray[1] = "a";
		System.out.println(oArray.toString());
	}
	// this function can work in runtime
	public static void genericList() {
		List<Integer>[] lists = (List<Integer>[])new List[2];
		List[] olists = lists;
		olists[0] = new ArrayList<Integer>();
		// it can not be detected at runtime and compile time
		olists[1] = new ArrayList<String>();
		olists[1].add("winter is coming");
		System.out.println(olists[1].toString());
	}
	public static void main(String args[]) {
//		normalList();
		genericList();
	}
}
