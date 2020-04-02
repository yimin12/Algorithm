/**
 * 
 */
package williamsNotebook.common.classified;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��21�� ����10:47:05
* Description:
* 	Consider an unlimited flow of data elements. How do you sample k element from this flow, 
* 	such that at any point during the processing of the flow, you can return a random set of 
* 	k elements from the n elements read so far. 
* Assumption:
* 	k >= 1
* You will implement two methods for sampling class:
* 	read(int value) - read one number from the flow
* 	sample() - return at any time the k samples as a list, return the list of all values read 
* 			   when the number of values read so fas <= k.
* You may need to add more fields for the class.
*/

public class GeneralizedReservoirSampling {
	private final int k;
	private int count;
	private List<Integer> sample;
	
	public GeneralizedReservoirSampling(int k) {
//		we need to sample k elements instead of just one element.
//		usually we will need this validation in the constructor
		if(k <= 0) {
			throw new IllegalArgumentException("k must be > 0");
		}
		this.k = k;
		this.count = 0;
		sample = new ArrayList<Integer>();
	}
	public void read(int value) {
		count++;
		if(count <= k) {
//			for the first k elements, we just add them into the sample list
			sample.add(value);
		} else {
			int random = (int)(Math.random() * count);
//			for the recent read element, it should have the probability of k/count to be as one of samples
			if(random < k) {
//				replace the sample at the corresponding position
				sample.set(random, value);
			}
		}
	}
}
