/**
 * 
 */
package williamsNotebook.easy.array;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��8�� ����4:24:23
* Description: 
* 	Use different way to implement the solution of FibonacciNumber
*/

/**
 * @author 61771
 *
 */
public class FibonacciNumber {
	
//	Method1: Recursion way, this method might be timeout if you set the running time
	public long fibonacci(int K) {
//		Conner case:
		if(K < 0) {
			return 0;
		}
//		Base Case:
		if(K == 1) {
			return 1;
		}
		return fibonacci(K - 1) + fibonacci(K - 2);
	}
//	Dynamic Solution with O(n) space
	public long fibonacciI(int k) {
		if(k <= 0) {
			return 0;
		}
//		you should create space for k + 1
		long dp[] = new long[k + 1];
		dp[1] = 1;
//		int[] if it is not initialized, it will auto fill 0, "0, false, null" rule
		
		for(int i = 2; i <= k; i++) {
//			dp[0] = 0;
			dp[i] = dp[i-2] + dp[i-1];
		}
		return dp[k];
	}
//	Dynamic Solution with O(1) space
	public long fibonacciII(int k) {
		long a = 0;
		long b = 1;
		if(k<=0) {
			return 0;
		}
//		Follow this specific rule and run k-1 times;
		while(k > 1) {
			long temp = a + b;
			a = b;
			b = temp;
			k--;
		}
		return b;
	}
//	Method4: Optimization by using matrix multiplication Time O(logN)
	public static final long[][] seed = {{1L, 1L},{1L, 0L}};
	public long fibonacciIII(int k) {
		if(k<=0)return 0;
		if(k==1)return 1;
		long[][] matrix = {{1L, 1L},{1L, 0L}};
		pow(matrix, k-1);
		return matrix[0][0];
	}
	// calculate matrix ^ pow, and use the result to update matrix value;
	// Using linear Algbra
	private void pow(long[][] matrix, int pow) {
		if (pow==1) {
			return;
		}
		pow(matrix, pow/2);
		multiply(matrix, matrix);
		if(pow%2 != 0) {
			multiply(matrix,seed);
		}
	}
	private void multiply(long[][] matrix, long[][] muliplier) {
		long topLeft = matrix[0][0] * muliplier[0][0] + matrix[0][1] * muliplier[1][0];
		long topRight = matrix[0][0] * muliplier[0][1] + matrix[0][1] * muliplier[1][1];
		long bottomLeft = matrix[1][0] * muliplier[0][0] + matrix[1][1] * muliplier[1][0];
		long bottomRight = matrix[1][0] * muliplier[0][1] + matrix[1][1] * muliplier[1][1];
		matrix[0][0] = topLeft;
		matrix[0][1] = topRight;
		matrix[1][0] = bottomLeft;
		matrix[1][1] = bottomRight;
	}
}
