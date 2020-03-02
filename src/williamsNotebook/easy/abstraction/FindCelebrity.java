/**
 * 
 */
package williamsNotebook.easy.abstraction;

/**
 * @author yimin Huang
 *	
 *	Description:	
 *		Suppose you are at a party with n people (labeled from 0 - 1) and among them, there may (may not) exist one celebrity.
 *		The definition of a celebrity is that all the other n - 1 people know him but she does not know the others
 *	Given API:
 *		boolean knows(int a, int b) by using int to denote people
 *	Assumption:
 *		n > 1
 * Algorithm Class
 */
public class FindCelebrity {
	
	public boolean knows(int a, int b) {
		return true;
	}
	
	// Method 1 : BruteForce Time: O(n^2) and Space:O(1) 
	public int findCelebrity(int n) {
		// if exist celebrity, return its label, if do not exist, return -1
		int candidate = 0;
		for(; candidate < n; candidate++) {
			for(int j = 0; j < n; j++) {
				if(candidate == j || (!knows(candidate, j) && knows(j, candidate) && j < n-1)) {
					continue;
				} else if (knows(candidate, j) || !knows(j, candidate)){
					break;
				}
				// check the last one
				if(knows(candidate, j) && !knows(j, candidate)) {
					return candidate;
				} 
			}
			
		}
		return -1;
	}
	
	// Method 2: optimize by linear search 
	// 1. Find a candidate by one pass: (eliminate candidate when he knows other people)
	// Time: O(n), Space: O(1)
	public int findCelebrityI(int n) {
		// Assumption: there are at most 1 celebrity
		int candidate = 0;
		for(int i = 1; i < n; i++) {
			if(knows(candidate, i)) {
				candidate = i;
			}
		}
		// another loop to traverse the people before the candidate to ensure whether he/she is a celebrity
		for(int i = 0; i < n; i++) {
			if(candidate == i) continue;
			if(knows(candidate, i) || !knows(i, candidate)) {
				return -1;
			}
		}
		return candidate;
	}
}
