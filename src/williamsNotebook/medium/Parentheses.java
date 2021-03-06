/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * @author yimin Huang
 *
 *	Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *	The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 *
 * Algorithm Class
 */
public class Parentheses {

	// FollowUp 1, give k pair of parentheses "()", return a list of permutation
	// Method 1: dfs, traverse all possible solution Time: O(2^k), Space:O(k)
	public List<String> validParenthesesPermutation(int k){
		// Assumption: k > 0
		List<String> res = new ArrayList<String>();
		char[] cur = new char[k * 2];
		helper(cur, k, k, 0, res);
		return res;
	}
	// dfs function implement
	public void helper(char[] cur, int left, int right, int level, List<String> res) {
		// base case, termination condition
		if(left == 0 && right == 0) {
			res.add(new String(cur));
			return;
		}
		// recursion rule: when we can add a '(' ? whenever there is some '(' we can still use
		if(left > 0) {
			cur[level] = '(';
			helper(cur, left - 1, right, level + 1, res);
			// it looks like we do not handle the back tracking problem. the code itself actually suffices the above
			// two points and it already does the correct moving. The judgement is not determined by cur, and it is a 'write' operation
		} 
		// when can we add '(' ? when there is more '(' than ')'
		if(right > left) {
			cur[level] = ')';
			helper(cur, left, right - 1, level + 1, res);
		}
	}
	
	// FollowUp 2: determine if the input string of parenthesis is valid.
	public static final Map<Character, Character> map = new HashMap<Character, Character>() {{
        put('(', ')');
        put('[', ']');
        put('{', '}');
    }};
    // validation, no need to use dfs, use stack to test
    public boolean isValid(String s) {
    	Deque<Character> stack = new ArrayDeque<Character>();
    	for(Character c : s.toCharArray()) {
    		if(stack.isEmpty() || map.containsKey(c)) {
    			stack.offerLast(c);
    		} else {
    			if(!c.equals(map.get(stack.pollLast()))) return false;
    		}
    	}
    	return stack.isEmpty();
    }
    
    // FollowUp 3:
	/*
	 * Given a string containing just the characters '(' and ')', find the length of
	 * the longest valid (well-formed) parentheses substring. For "(()", the longest
	 * valid parentheses substring is "()", which has length = 2. Another example is
	 * ")()())", where the longest valid parentheses substring is "()()", which has
	 * length = 4. 
	 * dp[i] : length of the longest valid parenthesis start from s[i];
	 * dp[i] = dp[i+1] + 2 + dp[i+1+dp[i+1]+1) if s[i] == '(' and s[i+1+d[i+1] == ')'
	 */
    // Method 1 :Dynamic Program: Time: O(n), Space: O(n)
    public int longestValidParenthesis(String s) {
    	if(s == null) return 0;
    	int n = s.length();
    	if(n < 2) return 0;
    	int max = 0;
    	int[] dp = new int[n];
    	dp[n-1] = 0;
    	for(int i = n - 2; i >= 0; i++) {
    		if(s.charAt(i) == '(') {
    			int j = i + 1 + dp[i+1];
    			if(j < n && s.charAt(j) == ')') {
    				dp[i] = dp[i+1] + 2;
    				if(j + 1 < n) {
    					dp[i] += dp[j+1];
    				}
    			}
    		}
    		max = Math.max(max, dp[i]);
    	}
    	return max;
    }
    // Method 2: Storing Index in stack and without elimination
    // Time: O(n) and Space O(n) in worst case
    public int longestValidParentheses(String s) {
    	Deque<Integer> stack = new LinkedList<Integer>();
    	int len = s.length();
    	int longest = 0;
    	stack.offerFirst(-1);
    	for(int i = 0; i < len; i++) {
    		if(s.charAt(i) == '(') {
    			stack.offerFirst(i);
    		} else {
    			stack.pop();
    			if(stack.isEmpty()) {
    				stack.push(i);
    			} else {
    				longest = Math.max(longest, i-stack.peek());
    			}
    		}
    	}
    	return longest;
    }
    
    // Method 3: Two Counters, kind of greedy (why must we traverse it in two different directions) try "(()"
    // Time: O(n) and Space O(1)
    public int longestValidParenthesesIII(String s) {
    	int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    // followUp 4: 
    // All Validation of Parenthesis II(L pairs of (), M pairs of[], N pairs of {})
    // get all valid permutation of pairs of (), m pairs of <> and n pairs of {}
    // still use dfs to traverse all the possible solution
    public static final char[] PS = new char[] {'(',')','[',']','{','}'};
    public List<String> validParenthesisII(int l, int m, int n){
    	// Assumption: l, m, n > 0;
    	int[] remain = new int[] {l, l, m, m, n, n};
    	int targetLen = 2 * (l + m + n);
    	StringBuilder sb = new StringBuilder();
    	Deque<Character> stack = new LinkedList<Character>();
    	List<String> result = new ArrayList<String>();
    	helperI(sb, stack, remain, targetLen, result);
    	return result;
    }
    private void helperI(StringBuilder sb, Deque<Character> stack, int[] remain, int length, List<String> result) {
    	// base case
    	if(sb.length() == length) {
    		result.add(sb.toString());
    		return;
    	}
    	//recursion rule, try every option
    	for(int i = 0; i < remain.length; i++) {
    		if(i % 2 == 0) {
    			if(remain[i] > 0) {
    				sb.append(PS[i]);
    				stack.offerFirst(PS[i]);
    				remain[i]--;
    				helperI(sb, stack, remain, length, result);
    				// handle the back tracking 
    				sb.deleteCharAt(sb.length() - 1);
    				stack.pollFirst();
    				remain[i]++;
    			} 
    		} else {
				if(!stack.isEmpty() && stack.peekFirst() == PS[i - 1]) {
					sb.append(PS[i]);
					stack.pollFirst();
					remain[i]--;
					helperI(sb, stack, remain, length, result);
					sb.deleteCharAt(sb.length() - 1);
					stack.offerFirst(PS[i-1]);
					remain[i]++;
				}
			}
    	}
    }
    
    // Follow Up : If we impose an addition restriction that {} > [] > (), then in this case, what should we do?  ( valid: "{ [ ( ) ] }",   invlaid "( [ { } ] ) " )
    
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
    	Parentheses solution = new Parentheses();
//    	List<String> validParenthesisII = solution.validParenthesisII(2, 2, 1);
//    	for(String string : validParenthesisII) {
//    		System.out.println(string);
//    	}
    	int longestValidParentheses = solution.longestValidParenthesesIII("(()");
    	System.out.println(longestValidParentheses);
	}
}
	