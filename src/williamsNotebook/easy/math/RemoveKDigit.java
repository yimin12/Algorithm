/**
 * 
 */
package williamsNotebook.easy.math;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 上午10:24:53
* Description:
* 	Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible
* 	Input: num = "1432219", k = 3  Output: "1219"
* 	Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
*/

public class RemoveKDigit {

	// Greedy Solution and Recursion
	// 	核心思想，位置越高，数字越大，权重越大，在满足要求情况下可以优先删除并且不影响正确答案。
	public String removeKdigit(String num, int k) {
		String result = helper(num, k);
		// remove leading 0s
		int i = 0;
		while(i < result.length() - 1) {
			if(result.charAt(i) != '0') break;
			i++;
		}
		result = result.substring(i, result.length());
		return result;
	}
	private String helper(String num, int k) {
		if(num == null || k < 0 || num.length() < k) return "";
		if(k == 0) return num;
		int m = num.length();
		String candidate = num.substring(0, k+1);
		int minDigit = Integer.MAX_VALUE;
		int index = 0;
		
		for(int i = 0; i < candidate.length(); i++) {
			int digit = candidate.charAt(i) - '0';
			if(digit < minDigit) {
				minDigit = digit;
				index = i;
			}
		}
		return candidate.substring(index,index+1) + helper(num.substring(index+1, m), k-index);
	}
	// Version 2: Stack with storing the index
	// 	维持一个递增栈
	// Time: O(n) and Space : O(n)
	public String removeKDigit(String num, int k) {
		int digits = num.length() - k;
		char[] stk = new char[num.length()];
		int top = 0;
		for(int i = 0; i < num.length(); i++) {
			char c = num.charAt(i);
			while(top > 0 && stk[top - 1] > c && k > 0) {
				top -= 1;
				k -= 1;
			}
			stk[top++] = c;
		}
		// handle the leading zero
		int idx = 0;
		while(idx < digits && stk[idx] == 0) idx++;
		return idx == digits ? "0" : new String(stk, idx, digits - idx);
	}
}
