/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��22�� ����7:04:59
* Description:
* 	Determine if there exists a set of four elements in a given array that 
* 	sum to the given target number.
* Assumption:
* 	The given array is not null and has length of at least 4
* Examples:
* 	A = {1, 2, 2, 3, 4}, target = 9, return true(1 + 2 + 2 + 4 = 9)
* 	A = {1, 2, 2, 3, 4}, target = 12, return false
*/
public class FourSum {
//	Method 1: sort the array first, O(n^3)
	public boolean exist(int[] array, int target) {
		Arrays.sort(array);
		for(int i = 0; i < array.length - 3; i++) {
			for(int j = i + 1; j < array.length - 2; j++) {
				int left = j + 1;
				int right = array.length - 1;
				int curTarget = target - array[i] - array[j];
				while(left < right) {
					int sum = array[left] + array[right];
					if(sum == curTarget) {
						return true;
					} else if (sum < curTarget) {
						left++;
					} else {
						right--;
					}
				}
			}
		}
		return false;
	}
//	each element record a pair of numbers in the array
//	left: the smaller index of the pair of numbers
//	right: the larger index of the pair of numbers
//	sum: the sum of the pair of numbers
	static class Element implements Comparable<Element>{
		int left;
		int right;
		int sum;
		Element(int left, int right, int sum){
			this.left = left;
			this.right = right;
			this.sum = sum;
		}
//		we define the order of the element;
//		first by the sum value, then by the right index, then by the left index
		@Override
		public int compareTo(Element o) {
			if(this.sum != o.sum) {
				return this.sum < o.sum ? -1:1;
			} else if(this.right < o.right) {
				return this.right < o.right ? -1 : 1;
			} else if(this.left != o.right) {
				return this.left < o.left ? -1 : 1;
			}
			return 0;
		}
	}
//	 Method 2: O(n^2*logn), get all pairs of numbers and apply 2 sum
	public boolean existII(int[] array, int target) {
//		we need to sort the array first, and find i,j,k,l such that i < j < k < l, and the sum
//		is target. we split the tuples into two pairs elements (i,j) and (k,l),
//		which ensure array[i]+array[j]<=array[k]+array[l]
		Arrays.sort(array);
		Element[] pairSum = getPairSum(array);
		Arrays.sort(pairSum);
		int left = 0;
		int right = pairSum.length - 1;
		while(left < right) {
//			only return true if two pair sum's sum is target and the larger pair sum's left 
//			index > smaller pair sum's large index
			if(pairSum[left].sum + pairSum[right].sum == target && pairSum[left].right < pairSum[right].left) {
				return true;
			} else if (pairSum[left].sum + pairSum[right].sum < target) {
				left++;
			} else {
//				when two pair sums' sum > target, right--
//				when two pair sums' sum == target but not larger pair sum's left index <= smaller
//				pair's right index, we need to do right--;
//				because the only thing we can guarantee is that right now the smaller pair sum's right
//				index is the smallest one among all pairSums with the same sum, and it is possible we
//				can find another pair with smaller right index
				right--;
			}
		}
		return false;
	}
	private Element[] getPairSum(int[] array) {
//		All situations of 2 combinations, C(n,2) n=array.length
		Element[] pairSum = new Element[array.length * (array.length - 1)/2];
		int curIndex = 0;
//		fill the element array.
		for(int i = 1; i < array.length; i++) {
			for(int j = 0; j < i; j++) {
				pairSum[curIndex++] = new Element(j, i, array[i]+array[j]);
			}
		}
		return pairSum;
	}
//	it do not contain de-duplication, if the array contains four unique same digits and 
//	if it is solution as well, it might contain same solution
//	e.g. {1,1,2,2,3,3,4,4,5,5} ,target = 10, the solution might contain two different {1,2,3,4}
}
