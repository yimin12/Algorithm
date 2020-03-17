/**
 * 
 */
package williamsNotebook.easy.conversion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yimin Huang
 *		
 *		Given numRows, generate the first numRows of Pascal's triangle.
 *		For example, given numRows = 5,
 *		Return
			[
			     [1],
			    [1,1],
			   [1,2,1],
			  [1,3,3,1],
			 [1,4,6,4,1]
			]
 * Algorithm Class
 */
public class PascalTriangle {

	// Time ~ O(k), Space ~ O(k^2 = 1+2+...+k)
	// formula: T[row][col] = T[row - 1][col - 1] + T[row - 1][col].
	public List<List<Integer>> generatePascalTriangle(int n){
		if(n < 0) throw new IllegalArgumentException("n should be larger than 0");
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		for(int i = 0; i < n; i++) {
			List<Integer> list = new ArrayList<Integer>();
			for(int j = 0; j <= i; j++) {
				if(j == 0 || j == i) list.add(1);
				else list.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
			}
			res.add(list);
		}
		return res;
	}
	
	// Follow Up 2: Generate kth Row of Pascal's Triangle
	// Given an index k, return the kth row of the Pascal's triangle.
	// Return [1,3,3,1].
	// Could you optimize your algorithm to use only O(k) extra space?
	// use to container to record the result
	public List<Integer> getRow(int rowIndex){
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> prev = new ArrayList<Integer>();
		for(int i = 0; i < rowIndex; i++) {
			list = new ArrayList<Integer>();
			for(int j = 0; j <= i; j++) {
				if(j == 0 || j == i) list.add(1);
				else {
					list.add(prev.get(j-1) + prev.get(j));
				}
			}
			prev = new ArrayList<Integer>(list);
		}
		return list;
	}
	public static void main(String[] args) {
		PascalTriangle solution = new PascalTriangle();
		List<List<Integer>> generatePascalTriangle = solution.generatePascalTriangle(5);
		for(List<Integer> list:generatePascalTriangle) {
			System.out.println(list.toString());
		}
		List<Integer> row = solution.getRow(6);
		System.out.println(row.toString());
	}
}
