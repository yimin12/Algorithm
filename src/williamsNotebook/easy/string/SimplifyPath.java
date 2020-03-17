package williamsNotebook.easy.string;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yimin Huang
 *	
 *	Given an absolute path for a file (Unix-style), simplify it.
 *	For example,
 *		path = "/home/", => "/home"
 *		path = "/a/./b/../../c/", => "/c"	
 * Algorithm Class
 */

// Have problem, not able to handle all the corner case
public class SimplifyPath {

	// Corner Cases:	
	// 	1. Did you consider the case where path = "/../"? you should return "/".
	//  2. Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/". In this case, you should ignore redundant slashes and return "/home/foo"
	public String simplifyPath(String path) {
		// All the input is valid for unix command
		Deque<String> stack = new ArrayDeque<String>();
		// once get into stack, all string is valid for a unix path
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < path.length(); i++) {
			// case 1. when you encounter a '/' 
			if(i == path.length() || path.charAt(i) == '/') {
				// Case 1.1 "../", which means return to previous level
				if(sb.toString().equals("..")) {
					if(!stack.isEmpty()) stack.pollLast();
				} else if(sb.length() > 0 && !sb.toString().equals(".")) {
					// Case 1.2 "xx/", x is not ".", it contains the situation that x is "/"
					stack.add(sb.toString());
				}
				// clear the String Builder
				sb.delete(0, sb.length());
			} else {
				// case 2. when you encounter a string that is not "/"
				sb.append(path.charAt(i));
			}
		}
		// post processing: check the last validation of string builder
		if(sb.toString().equals("..")) {
			if(stack.isEmpty()) sb.delete(0, sb.length());
			if(!stack.isEmpty()) stack.pollLast();
		} else if(sb.length() > 0 && !sb.toString().equals(".")) {
			stack.add(sb.toString());
		} else {
			sb.delete(0, sb.length());
		}
		if(stack.isEmpty()) stack.add("");
		sb.delete(0, sb.length());
		while(!stack.isEmpty()) {
			sb.append("/").append(stack.pollFirst());
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		SimplifyPath solution = new SimplifyPath();
		String simplifyPath = solution.simplifyPath("/a//b////c/d//././/../..");
		System.out.println(simplifyPath);
	}
}
