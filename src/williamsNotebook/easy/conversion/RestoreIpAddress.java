/**
 * 
 */
package williamsNotebook.easy.conversion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yimin Huang
 *	
 *		Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *		For example:
 *		Given "25525511135",
 *		return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 *
 * Algorithm Class
 */
public class RestoreIpAddress {

	// DFS: Time ~ O(N^4), Space ~ O(N)
	public List<String> restoreIpAddress(String s){
		List<String> list = new ArrayList<String>();
		dfs(s, 0, new StringBuilder(), list);
		return list;
	}
	private void dfs(String s, int numPt, StringBuilder path, List<String> list) {
	    if (numPt == 3) {
	    	System.out.println(s);
	        if (isValid(s)) list.add(path.toString() + s);
	    } else {
	        int len = path.length();
	        for (int i = 1; i <= 3 && i <= s.length(); i++) {
	            String num = s.substring(0, i);
	            if (isValid(num))
	                dfs(s.substring(i), numPt + 1, path.append(num).append('.'), list);
	            path.delete(len, path.length());
	        }
	    }
	}
	private boolean isValid(String s) {
		if(s.length() == 1 || s.length() >= 1 && s.length() <= 3 && !s.startsWith("0")) {
			int num = Integer.parseInt(s);
			if(num >= 0 && num <= 255) return true;
		}
		return false;
	}
	public static void main(String[] args) {
		RestoreIpAddress solution = new RestoreIpAddress();
		List<String> restoreIpAddress = solution.restoreIpAddress("25525511135");
		System.out.println(restoreIpAddress.toString());
	}
	
}
