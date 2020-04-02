/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��25�� ����12:44:55
* Description:
* 	Given a 2D array A[8][8] with all integer numbers if we take a number a[i][j], then we cannot take its 8 neighboring cells. 
* 	How should we take the numbers to make their sum as large as possible.
* Assumption:
* 	The given matrix is 8 * 8;
* Key insight:
* 	1. Think about one way of how observe matrix:
* 	   take each row as a single element, the matrix is actually an array of rows. The benefit of doing so is to convert
* 	   a two dimensional space problem to a one dimensional space problem
* 	2. How do you represent each row? More accurately
* 	   we want to know for each row, what are all possible ways of choosing some numbers from the row. we call each
* 	   of the ways is a "configuration", we can choose index of 0,3,5,7 as one configuration, or choose 1,6
* 	3. The problem is simply converted to:
* 	   There are 8 configurations in the array, and each pair of adjacent configurations c1 and c2 need to maintain certain
* 	   property: if index i is picked in c1, then c1, c1-1, c1+1 can not be picked in c2
*   4. We need a efficient way for the representations of each of the configurations, essentially what we need is just
*      8 bit, each bit means if the corresponding index is picked i the configuration or not
*/
public class LargestSumOfValidNumbers {
	public int largestSum(int[][] matrix) {
//		Assumption: matrix is not null, and it has size of 8 * 8
		int k = 8;
//		get all the possible configurations, each configuration is represented by an int value, and we use the lowest
//		8 bit to know which indices has been picked in any of the configuration
//		Here we guarantee that no adjacent bit is been picked in any of the configuration
		List<Integer> configs = validConfigs(k);
//		dp[i][j] = the max possible sum for the submatrix of row 0 - i and row i pick the jth configuration
		int[][] largest = new int[k][configs.size()];
		for(int i = 0; i < k; i++) {
//			dp[i][j] = max(dp[i-1][k]) for all possible configuration k that does not have conflict with configuration j
			for(int j = 0; j < configs.size(); j++) {
				largest[i][j] = Integer.MIN_VALUE;
				if(i==0) {
					largest[i][j] = sum(matrix[i], configs.get(j));
				} else {
					for(int l = 0; l < configs.size(); l++) {
						if(noConflict(configs.get(j), configs.get(i))) {
							largest[i][j] = Math.max(largest[i][j], largest[i-1][l] + sum(matrix[i], configs.get(j)));
						}
					}
				}
			}
		}
		int result = largest[k-1][0];
		for(int i = 1; i < configs.size();i++) {
			result = Math.max(result, largest[k-1][i]);
		}
		return result;
	}
//	get all possible configurations, each one is represented as an int value and we use the lowest 8 bits, we
//	gurarantee no adjacent bit is chosen for the lowest 8 bit
	private List<Integer> validConfigs(int k){
		List<Integer> configs = new ArrayList<Integer>();
		helper(configs, 0, k, 0);
		return configs;
	}
	private void helper(List<Integer> configs, int index, int k, int cur) {
		configs.add(cur);
		for(int i = index; i < k; i++) {
			helper(configs, i+2, k, cur | (1<<i));
		}
	}
//	trick th check if configuration c1 and configuration c2 has any conflict. By conflict, it means if ith bit is 1 in c1
//	then ith, (i-1)th, (i+1)th bit can not be 1 in c2
	private boolean noConflict(int c1, int c2) {
		return (c1 & c2) == 0 && ((c1 << 1) & c2) == 0 && (c1 & (c2 << 1)) == 0;
	}
//	use teh configuration to calculate the real sum
	private int sum(int[] array, int config) {
		int sum = 0;
		for(int i = 0; i < array.length;i++) {
			if(((config >>> i) & 1) != 0) {
				sum+=array[i];
			}
		}
		return sum;
	}
}
