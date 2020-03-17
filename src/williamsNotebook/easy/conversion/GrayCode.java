/**
 * 
 */
package williamsNotebook.easy.conversion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yimin Huang
 *		
 *		The gray code is a binary numeral system where two successive values differ in only one bit.
 *		Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0
 *		For example, given n = 2, return [0,1,3,2]. Its gray code sequence is
 *		00 - 0; 01 - 1; 11 - 3; 10 - 2
 *
 * Algorithm Class
 */
public class GrayCode {
	
	// Solution 1: linear bit setter by the highest digit and add the result to the list
	// Time ~ O(N^2), Space ~ O(1) for no extra space
	public List<Integer> grayCode(int n){
		List<Integer> list = new ArrayList<Integer>();
		// every sequence should start with 0
		list.add(0);
		for(int i = 0; i < n; i++) {
			int flipper = 1 << i;
			for(int j = list.size() - 1; j>= 0; j--) {
				list.add(list.get(j) | flipper);
			}
		}
		return list;
	}
	// Solution 2: DFS , Time ~ O(N^2), Space ~ O(N)
	public List<Integer> grayCodeDFS(int n){
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		dfs(0, n, list);
		return list;
	}
	private void dfs(int index, int n, List<Integer> list) {
		// base case
		if(index == n) return;
		else {
			int flipper = 1 << index;
			for(int j = list.size();j >= 0; j--) {
				list.add(list.get(j) | flipper);
			}
			dfs(index+1, n, list);
		}
	}
}
