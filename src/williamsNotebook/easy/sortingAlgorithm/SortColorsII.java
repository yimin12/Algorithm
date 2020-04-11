/**
 * 
 */
package williamsNotebook.easy.sortingAlgorithm;

import java.util.Arrays;

import williamsNotebook.common.SWAP;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月8日 下午2:13:16
* Description:
* 	Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same 
* 	color are adjacent, with the colors in the order 1, 2, ... k.
* 
*/

public class SortColorsII {

	 /**
     * Method I: O(k) space, O(n) time; two-pass algorithm, counting sort
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
	// key insight: count the input first and override it based on the counting information
	// Time: O(2n) Space: O(k)
	public void sortKColor(int[] colors, int k) {
		int[] count = new int[k];
		for(int color : colors) {
			count[color - 1]++;
		}
		int index = 0;
		for(int i = 0; i < k; i++) {
			while(count[i] > 0) {
				colors[index++] = i + 1;
				count[i]--;
			}
		}
	}
	// Method 2:
	/**
     * Method II:
     *  Each time sort the array into three parts:
     *  [all min] [all unsorted others] [all max],
     *  then update min and max and sort the [all unsorted others]
     *  with the same method.
     *
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
	// Time: O(kn) Space: O(1) 
	public void rainbowKSort(int[] colors, int k) {
		// do it in inplace swap
		if(colors == null || colors.length <= k) return;
		int left = 0, right = colors.length - 1;
		int i = 0, min = 1, max = k;
		while(min < max) {
			while(i <= right) {
				if(colors[i] == min) {
					SWAP.intSwap(colors, left++, i++);
				} else if(colors[i] == max) {
					SWAP.intSwap(colors, right--, i);
				} else {
					i++;
				}
			}
			// reset i to left
			i = left;
			min++;
			max--;
		}
	}
	public static void main(String[] args) {
		SortColorsII solution = new SortColorsII();
		int[] colors = new int[]{2, 5, 3, 4, 2, 2, 1};
		int k = 5;
		solution.rainbowKSort(colors, k);
		System.out.println(Arrays.toString(colors));
	}
}
