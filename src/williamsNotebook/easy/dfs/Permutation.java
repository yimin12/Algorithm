/**
 * 
 */
package williamsNotebook.easy.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author yimin Huang
 *	
 *	Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *	If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *	The replacement must be in-place, do not allocate extra memory.
 *	Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *	1,2,3 鈫� 1,3,2; 3,2,1 鈫� 1,2,3; 1,1,5 鈫� 1,5,1
 *	
 * Algorithm Class
 */
public class Permutation {

	// Next Permutation: Time ~ O(2N), Space ~ O(1)
	// Time ~ O(2N), Space ~ O(1)
	// Key insight: 
	// step 1: search the first a[i+1] that is greater than a[i] from right to left
	// step 2: find the value is exact smallest larger than a[i-1], if no such element, return to the smallest situation
	// step 3: swap the next larger value with a[i-1]
	// step 4: reverse the element between [i+1, nums,length]
	public void nextPermutation(int[] num) {
		// Time: O(N), Space: O(1)
	    // E.g.: 6 8 7 4 3 2 -> 7 2 3 4 6 8 (partition number: 6, swap with 7)
	    // find longest descending tail and reverse it, num[curr - 1] is the partition number
	    int curr = num.length - 1;
	    while (curr > 0 && num[curr - 1] >= num[curr])  curr--;
	    reverse(num, curr, num.length - 1);
	    // swap num[curr - 1] and the first larger element on its right side
	    if (curr > 0) {
	        int next = curr;
	        curr--;
	        while (num[curr] >= num[next])  next++;
	        swap(num, curr, next);
	    }
	}
	private void reverse(int[] num, int start, int end) {
	    while (start < end) {
	        swap(num, start++, end--);
	    }
	}
	private void swap(int[] num, int a, int b) {
	    int temp = num[a];
	    num[a] = num[b];
	    num[b] = temp;
	}
	
	// Follow Up1: All permutation I(with no duplicate) 
	// [1,2,3] have the following permutations: [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
	// DFS: Time ~ O(N!), Extra Space ~ O(N) by using method swap
	public List<String> permutation(int[] array){
		List<String> res = new ArrayList<String>();
		if(array == null) return res;
		StringBuilder sb = new StringBuilder();
 		dfs(array, 0, res, sb);
		return res;
	}
	private void dfs(int[] array, int index, List<String> res, StringBuilder sb) {
		// base case:
		if(index == array.length) {
			for(int i : array) sb.append(i);
			res.add(sb.toString());
			sb.delete(0, sb.length());
			return;
		}
		// recursion rules
		for(int i = index; i < array.length; i++) {
			swap(array, i, index);
			dfs(array, index+1, res, sb);
			swap(array, i, index);
		}
	}
	// Method 2: Solution to maintain the order of all the permutation
	// DFS: Time ~ O(N!), Extra Space ~ O(N) by using method swap
	public List<String> permutationsWithOrder(int[] array){
		List<String> res = new ArrayList<String>();
		if(array == null) return res;
		Arrays.sort(array);
		boolean[] used = new boolean[array.length];
		StringBuilder cur = new StringBuilder();
		dfsI(array, used, cur, res);
		return res;
	}
	private void dfsI(int[] array, boolean[] used, StringBuilder cur, List<String> res) {
		if(cur.length() == array.length) {
			res.add(cur.toString());
			return;
		}
		for(int i = 0; i < array.length; i++) {
			if(!used[i]) {
				used[i] = true;
				cur.append(array[i]);
				dfsI(array, used, cur, res);
				cur.deleteCharAt(cur.length() - 1);
				used[i] = false;
			}
		}
	}
	
	// FollowUp 3: All Permutations II: contains duplicates
	// [1,1,2] have the following unique permutations: and return [1,1,2], [1,2,1], and [2,1,1].
	// DFS: Time ~ O(N!), Extra Space ~ O(N) by using method swap
	public List<String> permutationWithDuplicate(int[] array){
		List<String> res = new ArrayList<String>();
		if(array == null) return res;
		Arrays.sort(array);
		HashSet<Integer> set = new HashSet<Integer>();
		StringBuilder sb = new StringBuilder();
		dfsDuplicate(array, set, sb, res);
		return res;
	}
	private void dfsDuplicate(int[] array, HashSet<Integer> set, StringBuilder cur, List<String> res) {
		// base case:
		if(cur.length() == array.length) {
			res.add(cur.toString());
			return;
		}
		// recursion rule
		for(int i = 0; i < array.length; i++) {
			if(i > 0 && !set.contains(i-1) && array[i-1] == array[i]) {
				continue;
			}
			if(!set.contains(i)) {
				cur.append(array[i]);
				//System.out.println(cur.toString());
				set.add(i);
				dfsDuplicate(array, set, cur, res);
				set.remove(i);
				cur.deleteCharAt(cur.length() - 1);
			}
		}
	}
	// Method 2: using swap
	// DFS: Time ~ O(N!), Extra Space ~ O(N) by using method swap
	public List<String> permutationDuplicateSwap(int[] array){
		List<String> res = new ArrayList<String>();
		if(array == null) return res;
		StringBuilder sb = new StringBuilder();
		dfsDupSwap(array, 0, res, sb);
		return res;
	}
	private void dfsDupSwap(int[] array, int index, List<String> res, StringBuilder sb) {
		// base case
		if(index == array.length) {
			for(int i : array) sb.append(i);
			res.add(sb.toString());
			sb.delete(0, sb.length());
			return;
		}
		Set<Integer> set = new HashSet<Integer>();
		// recursion rule
		for(int i = index; i < array.length; i++) {
			if(set.add(array[i])) {
				swap(array, i, index);
				dfsDupSwap(array, index + 1, res, sb);
				swap(array, i, index);
			}
		}
	}
	
	// FollowUp 4: kth Permutation Sequence
	/*
	 * The set [1,2,3,鈥�,n] contains a total of n! unique permutations. By listing
	 * and labeling all of the permutations in order, We get the following sequence
	 * (ie, for n = 3): "123" "132" "213" "231" "312" "321" Given n and k, return
	 * the kth permutation sequence.
	 */
	// Time ~ O(N), Space ~ O(N)
	public String getPermutation(int n, int k) {
		// Assume that n >= 1
		StringBuilder num = new StringBuilder();
		int[] factorial = new int[n+1];
		factorial[0] = 1;
		for(int i = 1; i <=n; i++) {
			num.append(i);
			factorial[i] = factorial[i-1] * i;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = n; i >=1; i--) {
			int index = k/factorial[i];
			sb.append(num.charAt(index));
			num.deleteCharAt(index);
			k %= factorial[i];
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Permutation solution = new Permutation();
		int[] array = new int[] {1,1,2,2};
		List<String> permutation = solution.permutation(array);
		List<String> permutationsWithOrder = solution.permutationsWithOrder(array);
		List<String> permutationsWithOrderDuplicate = solution.permutationWithDuplicate(array);
		List<String> permutationDuplicateSwap = solution.permutationDuplicateSwap(array);
		
		System.out.println(permutationDuplicateSwap.toString());
		System.out.println(permutationsWithOrderDuplicate.toString());
	}
}
