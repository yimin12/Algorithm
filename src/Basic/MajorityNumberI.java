/**
 * 
 */
package Basic;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��25�� ����12:37:29
* Description:
* 	Given an integer array of length L, find the number that occurs more than 0.5 * L times.
* Assumption:
* 	The given array is not null or empty
* 	It is guaranteed three exists such a majority number
* Examples:
* 	A = {1, 2, 1, 2, 1}, return 1
*/
// the key insight of majority number is that the number should appear most times compare with the others.
// so it could be classified as group "you" and group "not you", when you meet "you", count++; otherwise, count--;
public class MajorityNumberI {
	public int majority(int[] array) {
//		Assumption: array is not null and is not empty
//		majority number guarantees to exist
		int candicate = array[0];
		int count = 1;
		for(int i = 1; i < array.length;i++) {
			if(count == 0) {
				count++;
				candicate = array[i];
			} else if (candicate == array[i]) {
				count++;
			} else {
				count--;
			}
		}
		return candicate;
	}
}
