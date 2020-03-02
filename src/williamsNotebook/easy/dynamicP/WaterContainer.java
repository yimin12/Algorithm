package williamsNotebook.easy.dynamicP;

/**
 * @author yimin Huang
 *
 *	Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 *	n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
 *  which together with x-axis forms a container, such that the container contains the most water. 
 *  You may not slant the containe
 *  
 * Algorithm Class
 */
public class WaterContainer {
	
	// Method 1: Expand from center Time: O(n^2) , Space: O(1);
	public int max(int[] array) {
		if(array == null || array.length <= 1) throw new IllegalArgumentException();
		int maxArea = 0;
		for(int i = 0; i < array.length; i++) {
			for(int j = i + 1; j < array.length; j++) {
				// print out the boundary
				if((j - i) * Math.min(array[i], array[j]) > maxArea) {
					maxArea = (j - i) * Math.min(array[i], array[j]);
					System.out.println("i: " + i + " j: " + j + " Area: " + maxArea);
				}
				
			}
		}
		return maxArea;
	}
	
	// Method 2: Use two Pointers to do linear traversal, Time: O(n); Space: O(1); 
	// should know the difference between max Rectangle in histogram in medium package
	public int maxI(int[] array) {
		if(array == null || array.length <= 1) throw new IllegalArgumentException();
		int maxArea = 0, left = 0, right = array.length - 1;
		while(left < right) {
			maxArea = Math.max(maxArea, (right - left) * Math.min(array[left], array[right]));
			if(array[left] < array[right]) left++;
			else right--;
		}
		return maxArea;
	}
	public static void main(String[] args) {
		WaterContainer solution = new WaterContainer();
		System.out.println("final: " + solution.max(new int[] {2, 1, 3, 4, 5, 2, 6}));
	}
}
