/**
 * 
 */
package williamsNotebook.easy.dynamicP.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��18�� ����12:10:44
* Description:
* 	Given an array A of non-negative integers, you are initially positioned at index 0 of the 
* 	array. A[i] means the maximum jump distance from index i (you can only jump towards the end of the array).
* 	Determine the minimum number of jumps you need to reach the end of array. If you can not reach the end of 
* 	the array, return -1.
* Assumption:
* 	The given array is not null and has length of at least 1.
* Examples:
* 	{3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the end of array)	
	{2, 1, 1, 0, 2}, you are not able to reach the end of array, return -1 in this case
*/

public class ArrayHopperII {
	
//	Method 1: DP
	public int minJump(int[] array) {
//		Assumption: array is not null and it not empty
		int length = array.length;
//		minJump record the min number of jumps from 0 to each of the indices
		int[] minJump = new int[length];
//		we do not need to jump from index 0;
		minJump[0] = 0;
		for(int i = 1; i < length; i++) {
			minJump[i] = -1; // initialized as unreachable
			for(int j = i - 1; j >= 0; j--) {
				if(j + array[j] >= i && minJump[j] != -1) {
					if(minJump[i] == -1 || minJump[i] > minJump[j] + 1) {
						minJump[i] = minJump[i] + 1;
					}
				}
			}
		}
		return minJump[length-1];
	}
//	Method 2: Greedy Solution
	public int minJumpII(int[] array) {
		if(array.length == 1) {
			return 0;
		}
//		# of jumps currently
		int jump = 0;
//		max index by current # of jumps
		int cur = 0;
//		max index by current + 1 # of jumps
		int next = 0;
		for(int i = 0; i < array.length; i++) {
			if(i > cur) {
//				if index i can not reached by current # of jumps, we need to jump one more step
				jump++;
				if(cur==next) {
					return -1;
				}
				cur = next;
			}
			next = Math.max(next, array[i]+ i);
		}
		return jump; 
	}
}
