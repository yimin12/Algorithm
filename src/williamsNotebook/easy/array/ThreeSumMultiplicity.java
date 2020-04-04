/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 下午12:41:28
* Description:
* 	Given an integer arrayA, and an integertarget, return the number of tuples i, j, k such thati < j < kand A[i] + A[j] + A[k] == target.
* 	As the answer can be very large, return it modulo 10^9 + 7.
* Example:
* 	Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8  Output: 20
* 	Explanation: Enumerating by the values (A[i], A[j], A[k]):
* 		(1, 2, 5) occurs 8 times;
* 		(1, 3, 4) occurs 8 times;
* 		(2, 2, 4) occurs 2 times;
* 		(2, 3, 3) occurs 2 times.
*/
public class ThreeSumMultiplicity {

	// Method 1: Three Pointer with sorting it
	// Time Complexity: O(N^2), where N is the length of A.
	// Space Complexity: O(1).
	public int threeSumMulti(int[] A, int target) {
		if(A == null || A.length < 3) {
			return 0;
		}
		Arrays.sort(A);
		int MOD = 1000000007;
		int count = 0;
		int i, j, k;
		for(i = 0; i < A.length - 2; i++) {
			j = i + 1;
			k = A.length - 1;
			int t = target - A[i];
			while(j < k) {
				if(A[j] + A[k] > t) { // Case 1 ">"
					k--;
				} else if(A[j] + A[k] < t){ // Case 2: ">"
					j++;
				} else if(A[j] != A[k]) { //  Case 3: "=="
					int left = 1;
					int right = 1;
					while(j < k - 1 && A[j] == A[j+1]) {
						j++;
						left++;
					}
					while(j + 1 < k && A[k] == A[k-1]) {
						k--;
						right++;
					}
					count += left * right; // there number of combinations consisting these two elements
					count %= MOD;
					if(j + 1 == k) {
						break;
					} else {
						j++;
						k--;
					}
				} else { // Case 3.1: "==" %% A[i] == A[k]
					count += (k-j)*(k-j+1)/2; // Cn2 = n * (n-1)/2; Combinations
					count %= MOD;
					break;
				}
			}
		}
		return count;
	}
	
	// Store number of occurrence by HashMap
	int MOD = 1000000007;
	public int threeSumMultiMap(int[] A, int target) {
		if(A == null || A.length < 3) {
			return 0;
		}
		long count = 0;
		Arrays.sort(A);
		Map<Integer, Long> map = new HashMap<>();
		for(int i = 0; i < A.length; i++) {
			map.put(A[i], 1L + map.getOrDefault(A[i], 0L));
		}
		for(int i = 0; i < A.length; i++) {
			if(i > 0 && A[i] == A[i-1]) {
				continue;
			}
			int j = i + 1, k = A.length - 1;
			while(j < k) {
				if(A[i] + A[j] + A[k] == target) {
					// case 1.1: A[j] != A[i] and A[j] != A[k]
					if(A[i] != A[j] && A[j] != A[i]) {
						count += map.get(A[i]) * map.get(A[j]) * map.get(A[k]) % MOD;
					} 
					// Case 1.2: if A[j] == A[i]
					else if(A[j] != A[k]) {
						count += map.get(A[k])* map.get(A[j]) * (map.get(A[j])-1)/2 % MOD;
					} 
					// Case 1.3: if A[j] == A[k]
					else if(A[j] != A[i]){
						count += map.get(A[i]) * map.get(A[j]) * (map.get(A[j]) - 1)/2 % MOD;
					} else {
						// Case 1.4: A[i] == A[j] == A[k]
						count += map.get(A[j]) *  map.get(A[j] - 1) *  map.get(A[j] - 2) / 6 % MOD;
						return (int)count;
					}
					j++;
					k--;
					while (j < k && A[j] == A[j - 1]) j++;                    
					while (j < k && A[k] == A[k + 1]) k--;    
				}
				// Case 2: Sum of Three do not equal to target
				else if(A[i] + A[j] + A[k] < target) {
					j++;
					while(j < k && A[j] == A[j-1]) j++;
				} else if(A[i] + A[j] + A[k] > target) {
					k--;
					while(k > j && A[k] == A[k+1]) k--;
				}
			}
		}
		return (int)(count%MOD);
	}
}
