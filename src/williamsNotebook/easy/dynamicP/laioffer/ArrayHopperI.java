/**
 * 
 */
package williamsNotebook.easy.dynamicP.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��18�� ����11:09:54
* Description:
* 	Given an array A of non-negative integers, you are initially positioned at index 0 
* 	of the array. A[i] means the maximum jump distance from that position (you can only 
* 	jump towards the end of the array).Determine if you are able to reach the last index.
* Assumption:
* 	The given array is not null and has length of at least 1
* Examples:
* 	{1, 3, 2, 0, 3}, we are able to reach the end of array
* 	(jump to index 1 then reach the end of the array)
* 	{2, 1, 1, 0, 2}, we are not able to reach the end of array


*/
public class ArrayHopperI {
//	Method 1: DP, canJump[i] means from index 0, can jump to index i; (Forwarding direction)
	public boolean canJumpI(int[] array) {
//		Assumption: array is not null and is not empty
		boolean[] canJump = new boolean[array.length];
		canJump[0] = true;
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < i; j++) {
//				if index j is reachable from index 0, and from index j, 
//				it is possible to jump to index i
				if(canJump[j] && array[j] + j >= i) {
					canJump[i] = true;
					break;
				}
			}
		}
		return canJump[array.length-1];
	}
//	Method 2: DP, can Jump[i] means from index i, can jump to index array.length - 1
//	DP in reverse forwarding direction
	public boolean canJumpII(int[] array) {
		if(array.length == 1) {
			return true;
		}
		boolean[] canJump = new boolean[array.length];
		for(int i = array.length - 2; i>= 0; i--) {
//		if from index i, it is already possible to jump to the end of the array
			if(array[i] + i >= array.length - 1) {
				canJump[i] = true;
			} else {
//			if any of the reachable indices from index i is reachable to the end of the array
				for(int j = array[i]; j >= 1; j--) {
					if(canJump[j+i]) {
						canJump[i] = true;
						break;
					}
				}
			}
		}
		return canJump[0];
	}
//	Method 3:Greedy Solution
//	Keep the max index reachable by jump x steps, and the max index reachable by jumping x + 1 steps
	public boolean canJumpIII(int[] array) {
//		Assumption: array is not null and array.length >= 1
		if(array.length == 1) {
			return true;
		}
//		the max index jumping currents can reach, the max index jumping current + 1 steps can reach
		int cur = 0, next = 0;
		for(int i = 0; i < array.length;i++) {
			if(i > cur) {
//				if i > cur, we can not use current steps to jump to i
				if(cur == next) {
//					cur == next means there is no progress for using current + 1 jumps steps
//					if that is the case, we can never reach the end
					return false;
				}
				cur = next;
			}
//			update the max index jumping one more step can reach
			next = Math.max(next, i+array[i]);
		}
		return true;
	}
}
