/**
 * 
 */
package williamsNotebook.easy.dynamicP;

/**
 * @author yimin Huang
 *
 *	Given n non-negative integers representing an elevation map where the width of each bar is 1, compute 
 *	how much water it is able to trap after raining.
 *	For example:
 *		Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 *	
 * Algorithm Class
 */

public class RainTrapper {

	// Method 1: DP optimization with space, Time: O(n), Space: O(1)
	// height: 	{4, 1, 3, 4, 5, 2, 6}
	// leftMax:	{4, 4, 4, 4, 5, 5, 6}
	// rightMax:{6, 6, 6, 6, 6, 6, 6}
	// trapped: {0, 3, 1, 0, 0, 3, 0} pick the lower side to traverse
	// result: 3 + 1 + 3  = 7	
	public int rainTrap(int[] array) {
		if(array == null || array.length <= 1) throw new IllegalArgumentException();
		int rain = 0, left = 0, right = array.length - 1;
		int leftMax = array[left], rightMax = array[right];
		while(left < right) {
			if(array[left] <= array[right]) {
				rain += Math.max(0, leftMax - array[left]);
				leftMax = Math.max(leftMax, array[left]);
				left++;
			} else {
				rain += Math.max(0, rightMax - array[right]);
				rightMax = Math.max(rightMax, array[right]);
				right--;		
			}
		}
		return rain;
	}
	
	// Method 2: same thought with Method 1 but record the water line;
	public int rainTrapI(int[] array) {
		if(array == null || array.length <= 1) throw new IllegalArgumentException();
		int rain = 0, start = 0, end = array.length - 1;
		int waterLine = 0;
		while(start < end) {
			// key insight: maintain the lowest height of boundary between current [start, end]
			int min = Math.min(array[start], array[end]);
			// if the allowed waterLine need to get higher
			if( min > waterLine) {
				waterLine = min;
			}
			if(array[start] < array[end]) {
				rain += waterLine - array[start++];
			} else {
				rain += waterLine - array[end--];
			}
		}
		return rain;
	}
	public static void main(String[] args) {
		RainTrapper solution = new RainTrapper();
		int rainTrap = solution.rainTrap(new int[] {4, 1, 3, 4, 5, 2, 6});
		System.out.println(rainTrap);
	}
}
