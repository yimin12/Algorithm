/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

import java.util.HashMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 下午11:27:39
* Description:
* 	You are playing the following Flip Game with your friend: Given a string that contains only these two characters:+and-, you and your friend take 
* 	turns to flip two consecutive"++"into"--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
* 	Write a function to determine if the starting player can guarantee a win.
* Example:
* 	Input: s = "++++"
* 	Output: true 	
* 	Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
*/
public class FlipGameII {

	// Analysis:	只有在flip之后对方无法保证赢的情况下，自己这一方才有可能保证赢。因此子问题便是，每次对"++"变形flip为“--”之后，新的字符串是否能保证赢？
	//				对子问题的搜索是可能有重复的，因此结合记忆化memoization，就可以解决这个问题。
	// 	对于这一类记忆化搜索问题计算复杂度，一般计算复杂度的上界（采用大O符号）。我们采取的计算公式为，复杂度 = 状态数 * 决策数目 * 转移费用。
	// Time : O(n*2^n)
	public boolean canWin(String s) {
		char[] arr = s.toCharArray();
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		return canWinHelper(s, map);
	}
	private boolean canWinHelper(String s, HashMap<String, Boolean> map) {
		if(s == null || s.length() == 0) return false;
		if(map.containsKey(s)) return map.get(s);
		char[] arr = s.toCharArray();
		boolean canWin = false;
		for(int i = 0; i < arr.length - 1; i++) {
			if(arr[i] == '+' && arr[i+1] == '+') {
				arr[i] = '-';
				arr[i+1] = '-';
				boolean search = canWinHelper(new String(arr), map);
				if(!search) {
					canWin = true;
					break;
				}
				arr[i] = '+';
				arr[i+1] = '+';
			}
		}
		map.put(s, canWin);
		return canWin;
	}
}
