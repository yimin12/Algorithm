/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月1日 下午8:25:50
* Description:
* 	Given an array of integers, how many three numbers can be found in the array, so that we can build an triangle whose three 
* 	edges length is the three numbers that we find?
* Example:
* 	Given array S = [3,4,6,7], return 3. They are:
* 	[3,4,6]
	[3,6,7]
	[4,6,7]	
	Assuming that there is no duplicate
*/

public class TriangleCount {

	// Time: O:(n^3) Brute Force
	public int triangleCountBrute(int[] s) {
		if(s == null || s.length < 3) return 0;
		int count = 0;
		for(int i = 0; i < s.length; i++) {
			for(int j = i + 1; j < s.length; j++) {
				for(int k = j + 1; k < s.length; k++) {
					if(valid(i, j, k, s))count++;
				}
			}
		}
		return count;
	}
	// Two Pointer by optimization
	// Time: (n^2 + nlogn) because we need sort first and it will consume nlogn
	public int triangleCount(int[] s) {
		Arrays.sort(s);
		int count = 0;
		for(int i = 0; i < s.length; i++) {
			int left = 0, right = i - 1;
			while(left < right) {
				// because it is sorted in ascending order
				// s[i] > s[right] > s[left] by default : s[i] + s[left] or s[right] > s[right] or s[left]
				if(s[left] + s[right] > s[i]) {
					count += right - left;
					right--;
				} else{
					left++;
				}
			}
		}
		return count;
	}
	private boolean valid(int i, int j, int k, int[] s) {
		return s[i] + s[j] > s[k] && s[i] + s[k] > s[j] && s[j] + s[k] > s[i];
	}
	
	public static void main(String[] args) {
		TriangleCount solution = new TriangleCount();
		int[] s = {4,4,4,4};
		int triangleCount = solution.triangleCount(s);
		System.out.println(triangleCount);
	}
}
