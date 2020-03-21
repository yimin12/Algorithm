/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2020��3��21�� ����1:11:01
* Description:
* 	Given an array of integers, find two non-overlapping subarrays which have the largest sum. 
* 	The number in each subarray should be contiguous.
* 	Return the largest sum.
* Assumption:
* 	The subarray should contain at least one number
* Example:
* 	For given[1, 3, -1, 2, -1, 2], the two subarrays are[1, 3]and[2, -1, 2]or[1, 3, -1, 2]and[2], 
* 	they both have the largest sum7.
*/
public class MaximumSubArrayII {

	// Method 1: Dynamic Programming, Kadane's Algorithm
	// Time: O(n) and Extra Space O(n);
	public int maxTwoSubArrays(int[] array) {
		if(array == null || array.length == 0) return 0;
		int n = array.length;
		// left[i] represent the maxSubArraySum between [0...i]
		int[] left = new int[array.length];
		// right[i] represent the maxSubArraySum between[i...n-1];
		int[] right = new int[array.length];
		// base case:
		left[0] = array[0];
		right[n-1] = array[n-1];
		// you can generate another dp array for finding prev, I optimize it to O(1) space comsumption
		int prev = left[0], max = left[0];
		for(int i = 1; i < n; i++) {
			prev = Math.max(prev + array[i], array[i]);
			max = Math.max(max, prev);
			left[i] = max;
		}
		// did the same thing as left array have done
		for(int i = n - 2; i >= 0; i--) {
			prev = Math.max(prev + array[i], array[i]);
			max = Math.max(max, prev);
			right[i] = max;
		}
		// Find teh max sum of left and right subarrays
		int res = Integer.MIN_VALUE;
		for(int i = 0; i < n - 1; i++) {
			res = Math.max(left[i] + right[i + 1], res);
		}
		return res;
	}
	// Method 2: Same logic by implementing prefix Sum
	// Time: O(n) and Extra Space O(n);
	public int maxTwoSubArraysSumII(int[] array) {
		if(array == null || array.length == 0) return 0;
		int n = array.length;
		// left[i] represent the maxSubArraySum between [0...i]
		int[] left = new int[array.length];
		// right[i] represent the maxSubArraySum between[i...n-1];
		int[] right = new int[array.length];
		// base case:
		left[0] = array[0];
		right[n-1] = array[n-1];
		int prefixSum = 0;
		int minPrevfixSum = 0;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++) {
			prefixSum += array[i];
			max = Math.max(max, prefixSum - minPrevfixSum);
			minPrevfixSum = Math.min(minPrevfixSum, prefixSum);
			left[i] = max;
		}
		// reset the variable
		prefixSum = 0;
		minPrevfixSum = 0;
		max = Integer.MIN_VALUE;
		for(int i = n - 1; i >= 0; i--) {
			prefixSum += array[i];
			max = Math.max(max, prefixSum - minPrevfixSum);
			minPrevfixSum = Math.min(minPrevfixSum, prefixSum);
			right[i] = max;
		}
		int res = Integer.MIN_VALUE;
		for(int i = 0; i < n - 1; i++) {
			res = Math.max(res, left[i] + right[i+1]);
		}
		return res;
	}
	
	// Follow Up 2: Given an array of integers and a numberk, find knon-overlapping subarrays 
	// which have the largest sum. The number in each subarray should be contiguous. Return the largest sum.
	// Example : Given[-1,4,-2,3,-2,3],k=2, return8
	// 2D Dynamic Programming
	// 	��Ҫ����������ά��ȫ�ֵĵ����ֵ��������ֱ�ΪlocalMax[i][j]��globalMax[i][j]
	//	localMax[i][j]�����ڷ�j��subarraysʱ����ȡ�˵�i��Ԫ�أ�������ɵ����subArraySum�Ƕ���
	//	GlobalMax[i][j]�����ڷ���j��subArraysʱ�������ȡ��ȡ��i��Ԫ�أ�������ɵ����subArraySum�Ƕ���
	// 	�ɴ˿�֪��GlobalMax���ӵ�generalizationһЩ��������󷵻صĴ𰸾���GlobalMax[i][j];
	//  Induction rule: ��������ά���Ķ�̬����
	//   	1. ����localMax[i][j] �� i-1 �� iΪ  Math.max(localMax[i-1][j], globalMax[i-1][j-1]) + array[i-1]
	//			Case 1: ���½�����Ԫ�����뵽���һ�飬�������ֵ �� localMax[i-1][j] (����һ��ȡ�˵�i-1��ǰi-1��Ԫ�أ���j��)
	//			Case 2�����½�����Ԫ�ص����Լ���Ϊһ�飬������ǰ����飺 GlobalMax[i-1][j-1] ������ǰ i-1��Ԫ�أ���Ϊ j-1�飩
	//		2. ����GlobalMax[i][j] �� i-1�� i Ϊ  Math.max(localMax[i][j], globalMax[i-1][j]);
	// 			Case 1�� ��j�������²�ȡ���½����ĵ�iλԪ�أ� globalMax[i-1][j]
	// 			Case 2: ��j��������ȡ�����½����ĵ�iλԪ�أ� localMax[i][j] 
	// 	Base Case: �� i < jʱ,����Ԫ�ز����ֵ�������ǲ��Ϸ��ģ���һ�����������
	//			     �� i = jʱ��Ԫ�ظ��������зָ����������������local[i][j]Ϊ׼������ȡ�˵�iλ����
	public int maxKSumArraySum(int[] array, int k) {
		if(array == null || array.length == 0) return 0;
		int n = array.length;
		int[][] localMax = new int[n+1][k+1];
		int[][] globalMax = new int[n+1][k+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= k; j++) {
				// case 1: if the index is meaningless
				// case 2: 
				localMax[i][j] = Math.max(localMax[i-1][j], globalMax[i-1][j-1]) + array[i-1];
				if(i == j) globalMax[i][j] = localMax[i][j];
				else globalMax[i][j] = Math.max(localMax[i][j], globalMax[i-1][j]);
			}
		}
		System.out.println(Arrays.deepToString(localMax));
		return globalMax[n][k];
	}
	public static void main(String[] args) {
		MaximumSubArrayII solution = new MaximumSubArrayII();
		int[] array = {-1,4,-2,3,-2,3};
		int maxKSumArraySum = solution.maxKSumArraySum(array, 2);
		System.out.println(maxKSumArraySum);
	}
}
