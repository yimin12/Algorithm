/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;

/**
 * @author yimin Huang
 *
 *	There are n coins in a line. (Assume n is even). Two players take turns to take a coin from one of the 
 *	ends of the line until there are no more coins left. The player with the larger amount of money wins.
 *		would you rather go first or second? Does it matter?
 *		Assume that you go first, describe an algorithm to compute the maximum amount of money you can win
 *	Hints:
 *		If you go first, is there a strategy you can follow which prevents you from losing? Try to consider how it matters when the number of coins is odd vs. even.
 * Algorithm Class
 */
public class CoinsInLine {

	// 1. 2-d DP: Time ~ O(N^2), Space ~ O(2N^2)
	// Key Insight: Go first can guarantee winning. We can sum up the odd coins and even coins. If odd sum is bigger, we always take the odd coin, and vice versus.
	// Although this guarantees winning, it does not necessarily yields the max amount of money to win. We can take DP to find out the max.
	// Let d(i, j) be the maximum amount of money that you can win given the coins [i ... j].
	// If we take A[i], then the max amount that the opponent will get is d(i + 1, j);
	// If we take A[j], then the max amount that the opponent will get is d(i, j - 1);
	// Induction rule: d(i, j) = sum_{k = i}^j A[k] - min{d(i + 1, j), d(i, j - 1)},   i < j
	// d(i, j) = A[i],   i == j
	public int maxMoney(int[] array) {
		// Assumption: 
		if(array == null || array.length == 0) return 0;
		int N = array.length;
		int[][] sumA = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = i; j < N; j++) {
				sumA[i][j] = ((i==j) ? 0 : sumA[i][j - 1]) + array[j];
			}
		}
		int[][] dp = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N - i; j++) {
				int r = j, c = i + j;
				dp[r][c] = (r == c) ? array[r] : (sumA[r][c] - Math.min(dp[r + 1][c], dp[r][c-1]));
			}
		}
		System.out.println(Arrays.deepToString(sumA));
		return dp[0][N - 1];
	}
	// Method 2 :  avoid pre-computing and storing sum_{k = i}^j A[k].
	// Give coins [i ... j], and d(i, j) is defined as the above. Since the opponent will take the best strategy, he will minimize your winning amount d(i, j).
	// If we take A[i], d(i, j) = A[i] + min{d(i + 2, j), d(i - 1, j - 1)};
	// If we take A[j], d(i, j) = A[j] + min{d(i + 1, j - 1), d(i, j - 2)}.
	// Hence, d(i, j) = max{A[i] + min{d(i + 2, j), d(i - 1, j - 1)}, A[j] + min{d(i + 1, j - 1), d(i, j - 2)}},   i < j
	// base case: d(i, j) = A[i],   i == j, d(i, j) = max{A[i], A[j]},   i == j - 1
	// Fill up the table: from diagonal to diagonal, move toward upper right (note the for loops for row and column);
	public int maxMoneyII(int[] A) {
	    int N = A.length;
	    int[][] d = new int[N][N];
	    
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N - i; j++) {
	            int r = j, c = i + j;
	            if (r == c)
	                d[r][c] = A[r];
	            else if (r == c - 1)
	                d[r][c] = Math.max(A[r], A[c]);
	            else
	                d[r][c] = Math.max(A[r] + Math.min(d[r + 2][c], d[r + 1][c - 1]), 
	                                   A[c] + Math.min(d[r + 1][c - 1], d[r][c - 2]));
	        }
	    
	    return d[0][N - 1];
	}
	
	// Follow Up 2: Optimal Moves
	// Print out all the moves you and the opponent make, and the total amount of money that you win.
	public String printMoves(int[] A) {
	    int N = A.length;
	    int[][] d = new int[N][N];
	    
	    // compute DP table d[][]
	    for (int i = N - 1; i >= 0; i--)
	        for (int j = i; j < N; j++) {
	            if (i == j)
	                d[i][j] = A[i];
	            else if (i == j - 1)
	                d[i][j] = Math.max(A[i], A[j]);
	            else
	                d[i][j] = Math.max(A[i] + Math.min(d[i + 2][j], d[i + 1][j - 1]), 
	                                   A[j] + Math.min(d[i + 1][j - 1], d[i][j - 2]));
	        }
	    
	    // generate the path
	    StringBuilder str = new StringBuilder();
	    boolean myTurn = true;
	    int r = 0, c = N - 1;
	    while (r <= c) {
	        if (myTurn) str.append("I take ");
	        else        str.append("You take ");
	        myTurn = !myTurn;
	        if (d[r + 1][c] < d[r][c - 1])  str.append(A[r++]);
	        else                            str.append(A[c--]);
	        str.append("\n");
	    }
	    str.append("The total amount of money that I get is " + d[0][N - 1]);        
	    return str.toString();
	}
	
	public static void main(String[] args) {
		CoinsInLine solution = new CoinsInLine();
		int maxMoney = solution.maxMoney(new int[] {3,2,2,3,1,2});
		System.out.println(maxMoney);
	}
}
