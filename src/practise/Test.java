/**
 * 
 */
package practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月13日 下午8:40:16
* Description:
*/

public class Test {
	
	private static final char[] PS = new char[]{'(',')','[',']','{','}'};
	private static final int[] PRIORITY = new int[]{1,2,3};  // { > [ > (

	public List<String> generateParenthesesIII(int l, int m, int n){
	    if(l < 0 || m < 0 || n < 0) return new ArrayList<String>();
	    int[] remain = new int[]{l,l,m,m,n,n};
	    int tarLen = 2 * (m+l+n);
	    StringBuilder cur = new StringBuilder();
	    Deque<Character> stack = new LinkedList<Character>();
	    Deque<Integer> priority = new LinkedList<Integer>();
	    List<String> res = new ArrayList<String>();
	    dfs(cur, stack, priority, res, tarLen, remain);
	    return res;
	}
	private void dfs(StringBuilder cur, Deque<Character> stack, Deque<Integer> priority, List<String> res, int tarLen, int[] remain){
	    // base case
	    if(cur.length() == tarLen){
	        res.add(cur.toString());
	        return;
	    }
	    for(int i = 0; i < remain.length; i++){
	        // slightly difference for rule of left bracket
	        if(i % 2 == 0){
	            if(remain[i] > 0 && (priority.isEmpty() || PRIORITY[priority.peekFirst()/2] > PRIORITY[i/2])){
	                cur.append(PS[i]);
	                stack.offerFirst(PS[i]);
	                priority.offerFirst(i); // record the index rather than value
	                remain[i]--;
	                dfs(cur, stack, priority, res, tarLen, remain);
	                remain[i]++;
	                priority.pollFirst();
	                stack.pollFirst();
	                cur.deleteCharAt(cur.length()-1); 
	            }
	        } else {
	            if(!stack.isEmpty() && stack.peek() == PS[i-1]){
	                cur.append(PS[i]);
	                stack.pollFirst();
	                priority.pollFirst();
	                remain[i]--;
	                dfs(cur, stack, priority, res, tarLen, remain);
	                remain[i]++;
	                priority.offerFirst(i-1);
	                stack.offerFirst(PS[i-1]);
	                cur.deleteCharAt(cur.length()-1);
	            }
	        }
	    }
	}
	public static void main(String[] args) {
		Test s = new Test();
		List<String> generateParenthesesII = s.generateParenthesesIII(1, 1, 1);
		System.out.println(generateParenthesesII);
	}
}
