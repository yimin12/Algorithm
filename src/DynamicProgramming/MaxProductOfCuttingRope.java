/**
 * 
 */
package DynamicProgramming;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月18日 上午10:24:29
* Description:
* 	Given a rope with positive integer-length n, how to cut the rope into
* 	m integer-length parts with length p[0], p[1], ...,p[m-1], in order to 
* 	get the maximal product of p[0]*p[1]* ... *p[m-1]? m is determined 
* 	by you and must be greater than 0 (at least one cut must be made). 
* 	Return the max product you can have.
* Assumption: n>=2
* Examples:
	n = 12, the max product is 3 * 3 * 3 * 3 = 81
	(cut the rope into 4 pieces with length of each is 3).
*/


public class MaxProductOfCuttingRope {

	public int maxProduct(int length) {
//		Assumption: length >= 2
		if(length == 2) {
			return 1;
		}
		int[] array = new int[length + 1];
		array[1] = 0;
		array[2] = 1;
		for(int i = 3; i < array.length; i++) {
//			At least one of the partitions after cutting is <= i/2, so we can just pick
//			that partition, and find the maximum product
			for(int j = 1; j < i / 2; j++) {
//			for the other partition, we can choose not cutting it or cutting it, so the max
//			number we can get is either i-j or array[i-j].
				array[i] = Math.max(array[i], j * Math.max(i-j, array[i-j]));
//			we should guarantee the material is the same, so that we can compare i-j and array[i-j]
			}
		}
		return array[length];
	}
}
