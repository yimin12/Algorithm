package williamsNotebook.medium;

import java.util.Arrays;

/**
 * @author yimin Huang
 *		Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * Algorithm Class
 */
public class StringPatternMatchingI {
	
	// Brute force: Time ~ O(MN), Space ~ O(1)
	public int stringMatch(String target, String pattern) {
//		sanity check 		
		if(target == null || pattern == null || target.length() < pattern.length()) return -1;
		int N = target.length(), M = pattern.length();
		for(int i = 0; i <= N - M; i++) {
			int j = 0;
			for(;j < M; j++) {
				if(target.charAt(i + j) != pattern.charAt(j)) {
					break;
				}
			}
			if(j == M) {
				return i; // match complete
			}
		}
// 		nothing match
		return -1;
	}
	
	// Method 2 : KMP Algorithm: Time ~ O(M), Space ~ O(N) where N is the length of target string and need extra space with the length of pattern string
	public int stringMatchKMP(String target, String pattern) {
		if(target == null || pattern == null || target.length() < pattern.length()) return -1;
//		you should get the prefix table of pattern first
		int[] prefix = new int[pattern.length()];
//		shift advance each index by one
		getPrefix(pattern, prefix);
		rightShift(prefix);
//		start to search pattern in target, i, j is the traversing index in target and pattern
		int i = 0, j = 0; 
		while(i < target.length()) {
			if(j == pattern.length() - 1 && pattern.charAt(j) == target.charAt(i)) {
				// if you need to find every single occurrence match in target, just keep search by same logic
				// j = prefix[j];
				return i - j;
			}
			if(target.charAt(i) == pattern.charAt(j)) {
				i++;j++;
			} else {
				// shift back j to prefix j
				j = prefix[j];
				// dealing with the first char is still not matching, shift them advance both with one position
				if(j == -1) {
					i++;j++;
				}
			}
		}
// 		nothing match
		return -1;
	}
	private void getPrefix(String pattern, int[] prefix) {
//		in this part I will use an dp array to fill out the required array
//		basecase 
		prefix[0] = 0;
		int prefix_position = 0, i = 1;
		while(i < pattern.length()) {
			// case 1.0	if pattern match in the corresponding position
			if(pattern.charAt(i) == pattern.charAt(prefix_position)) {
				prefix_position++;
				prefix[i] = prefix_position;
				i++;
			}
			// case 2.0 if pattern the do not match in the corresponding position
			else {
				if(prefix_position > 0) {
					// specific trick: shift back one position when you back tracking
					prefix_position = prefix[prefix_position-1];
				} else {
					// handle the problem out of index
					prefix[i] = 0;
					i++;
				}
			}
		}
	}
	private void rightShift(int[] prefix) {
		// shift advance by one position and set the first value as -1;
		for (int i = prefix.length - 1; i > 0; i--) {
			prefix[i] = prefix[i-1];
		}
		prefix[0] = -1;
	}
	
	// Method 3: Booyer-Moore algorithm:  Time ~ O(N/M), Space ~ O(1)
	// No backup. Skip as many as M chars when finding one not in pattern.
	// Two cases:
	//		1. if mismatched char not in pat, increment i one char beyond the mismatched char;
	//		2. if mismatched char in pat, line up the rightmost same char in pat with the mismatched char.
	// Need to create a Boyer-Moore Skip Table: int[] right = new int[R].
	//		right[c] = the rightmost index of c in pat ( -1 if c is not in pat ).
	//		loop i : scan txt forward; From 0 to N - M, i += skip;
	//		loop j : scan pat backward; From M - 1 to 0, j--;
	//		if there's a mismatch: txt.charAt(i + j) != pat.charAt(j),   skip = max{1, j - right[txt.charAt(i + j)]};
	//		if no mismatch for all j: skip == 0, the matched substring is found, and return i.
	public int strBM(String target, String pattern) {
		int n = target.length(), m = pattern.length();
		int R = 256; // ASCII, optimize it without using hashSet
		int[] right = new int[R];
		// set all the cell as -1
		Arrays.fill(right, -1);
		for(int j = 0; j < m; j++) {
			right[pattern.charAt(j)] = j;
		}
		int skip;
		for(int i = 0; i <= n - m; i += skip) {
			// it can work as flag as well
			skip = 0;
			for(int j = m - 1; j >= 0; j--) {
				// if detect that it do not match, at least right shift 1 position
				if(target.charAt(i+j) != pattern.charAt(j)) {
					skip = Math.max(1, j - right[target.charAt(i+j)]);
					break;
				}
			}
			// if the whole pattern match, skip = 0;
			if(skip == 0) return i;
		}
		return -1;
	}
	
	// Method 4: Sunday Algorithm, quick similar with BM algorithm but more efficient
	// Assume that all the given character in string can be represented by ASCII
	// Sunday Algorithm:  Time ~ O(N/M), Space ~ O(1)
	// Need to fix
	public int strSunday(String target, String pattern) {
		int n = target.length(), m = pattern.length();
		int[] set = new int[256];
		Arrays.fill(set, -1);
		for(int j = 0; j < m; j++) {
			set[pattern.charAt(j)] = j;
		}
		int skip;
		for(int i = 0; i < n - m; i+=skip) {
			skip = 0;
			for(int j = 0; j < m; j++) {
				if(target.charAt(i+j) != pattern.charAt(j)) {
					int k = 0;
					while(k < m && target.charAt(i+m-1-k) == pattern.charAt(m-1-k)) {
						k++;
					}
					if(set[target.charAt(i+m-1-k)] == -1) {
						skip = m - k;
						break;
					} else {
						skip = Math.max(1, m - k - set[target.charAt(i+m-1-k)]);
						break;
					}
				}
			}
			if(skip == 0) return i;
		}
		return -1;

	}
	
	
	public static void main(String[] args) {
		StringPatternMatchingI s = new StringPatternMatchingI();
		int stringMatchKMP = s.strSunday("ABCDEFGABCDEFG", "CDEFG");
		System.out.println(stringMatchKMP);

	}
}
