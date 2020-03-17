/**
 * 
 */
package williamsNotebook.easy.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yimin Huang
 * 
 *	Given a set of distinct integers, S, return all possible subsets.
 *	For example,
 *	If S = [1,2,3], a solution is:
 *	[[3],[1],[2],[1,2,3],[1,3],[2,3],[1,2],[]]
 * Algorithm Class
 */
public class Subsets {
	
	// Situation 1: there are no duplicate elements in the given input
	// Method 1: Vertical dfs
	// Time: O(2^n)   Space:O(n)
	public List<String> subSetsI(String set){
		List<String> result = new ArrayList<String>();
		if(set == null || set.length() == 0) return result;
		char[] array = set.toCharArray();
		StringBuilder sb = new StringBuilder();
		verticaldfs(array, sb, 0, result);
		return result;
	}
	private void verticaldfs(char[] array, StringBuilder sb, int index, List<String> result) {
		if(index == array.length) {
			result.add(sb.toString());
			return;
		}
		// recursive rule
		// case 1. did not add any element of given array
		verticaldfs(array, sb, index + 1, result);
		// case 2. add corresponding element of current level
		verticaldfs(array, sb.append(array[index]), index + 1, result);
		sb.deleteCharAt(sb.length() - 1);
	}
	// Method 2: horizontal dfs
	// Time: O(n!)   Space:O(n)
	public List<String> subSetII(String set){
		List<String> result = new ArrayList<String>();
		if(set == null || set.length() == 0) return result;
		char[] array = set.toCharArray();
		StringBuilder sb = new StringBuilder();
		horizontaldfs(array, sb, 0, result);
		return result;
	}
	private void horizontaldfs(char[] array, StringBuilder sb, int index, List<String> result) {
		result.add(sb.toString());
		for(int i = index; i < array.length; i++) {
			sb.append(array[i]);
			horizontaldfs(array, sb, i+1, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	
	// Follow Up 2: There are might be duplicate in the given elements
	// Method 1: Vertical dfs Time: O(2^n)   Space:O(n)
	public List<String> subSetsWithDup(String set){
		List<String> result = new ArrayList<String>();
		if(set == null || set.length() == 0) return null;
		char[] array = set.toCharArray();
		StringBuilder sb = new StringBuilder();
		verticaldfsII(array, sb, 0, result);
		return result;
	}
	private void verticaldfsII(char[] array, StringBuilder sb, int index, List<String> result) {
		if(index == array.length) {
			result.add(sb.toString());
			return;
		}
		verticaldfsII(array, sb.append(array[index]), index + 1, result);
		sb.deleteCharAt(sb.length() - 1);
		while(index < array.length - 1 && array[index] == array[index + 1]) {
			index++;
		}
		verticaldfsII(array, sb, index + 1, result);
	}
	// Method 2: Horizontal dfs 
	// Time: O(n!)   Space:O(n)
	public List<String> subSetsWithDupII(String set){
		List<String> result = new ArrayList<String>();
		if(set == null || set.length() == 0) return null;
		char[] array = set.toCharArray();
		StringBuilder sb = new StringBuilder();
		horizontaldfsII(array, sb, 0, result);
		return result;
	}
	private void horizontaldfsII(char[] array, StringBuilder sb, int index, List<String> result) {
		result.add(sb.toString());
		for(int i = index; i < array.length; i++) {
			if(i == index || array[i] != array[i-1]) {
				sb.append(array[i]);
				horizontaldfsII(array, sb, i + 1, result);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
	
	public static void main(String[] args) {
		Subsets solution = new Subsets();
		List<String> subSetsI = solution.subSetII("123");
		System.out.println(subSetsI);
		
		List<String> subSetsWithDup = solution.subSetsWithDupII("122");
		System.out.println(subSetsWithDup);
	}
}
