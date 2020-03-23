/**
 * 
 */
package williamsNotebook.easy.array;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月22日 上午10:58:53
* Description:
* 	Given an array of integersnums, write a method that returns the "pivot" index of this array.
* 	We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.
* 	If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.
*/
public class FindPivotIndex {

	public int findPivot(int[] array) {
		if(array == null || array.length <= 2) return -1;
		int[] prefixSum = new int[array.length];
		prefixSum[0] = array[0];
		for(int i = 1; i < array.length; i++) {
			prefixSum[i] = prefixSum[i-1] + array[i];
		}
		for(int i = 0; i < array.length; i++) {
			int leftSum = (i == 0) ? 0 : prefixSum[i-1];
			if((leftSum == 0 && i == array.length - 1) || leftSum == prefixSum[array.length-1] - prefixSum[i]) {
				return i;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		FindPivotIndex solution = new FindPivotIndex();
		int findPivot = solution.findPivot(new int[] {-1,-1,0,1,1,0});
		System.out.println(findPivot);
	}
}