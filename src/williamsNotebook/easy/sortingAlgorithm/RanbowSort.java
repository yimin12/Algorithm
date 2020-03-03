/**
 * 
 */
package williamsNotebook.easy.sortingAlgorithm;

import java.util.Arrays;

import williamsNotebook.common.SWAP;

/**
 * @author yimin Huang
 *
 *	Sort three different kind of elements, the first type should be placed in the left, the third type should be 
 *	placed in the right, and the second type should be placed in the middle
 *	
 * Algorithm Class
 */
public class RanbowSort {

	// Sort three Colors, r:red, g:green, b:blue, do it inplace
	// Time: O(n), Space: O(1)
	public char[] sortColor(char[] array) {
		if(array == null || array.length <= 1) return array;
		// three bounds :
		// 1. the left side is -1 (exclusive of red)
		// 2. the right side is 1 (exclusive of blue)
		// 3. the part between red and green is 0 (exclusive of green)
		// 4. between green and blue is to be discovered (inclusive of both)
		int red = 0;
		int blue = array.length - 1;
		int green = 0;
		while(green <= blue) {
			if(array[green] == 'r') {
				SWAP.charSwap(array, red++, green++);
			} else if(array[green] == 'g') {
				green++;
			} else {
				SWAP.charSwap(array, green, blue--);
			}
		}
		return array;
	}
	
	public static void main(String[] args) {
		RanbowSort solution = new RanbowSort();
		char[] sortColor = solution.sortColor(new char[] {'g','b','r','g','g','b','b','r'});
		System.out.println(Arrays.toString(sortColor));
	}
}
