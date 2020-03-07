/**
 * 
 */
package williamsNotebook.common;

/**
 * @author yimin Huang
 *
 * Algorithm Class
 */
public class SWAP {

	public static void charSwap(char[] array, int left, int right) {
		char temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	
	public static void intSwap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
}
