/**
 * 
 */
package williamsNotebook.easy.random;

import java.util.Arrays;
import java.util.Random;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月8日 上午10:20:36
* Description:
* 	Reservoir sampling is a family of randomized algorithms for randomly choosing k samples from a list of n items, 
* 	where n is either a very large or unknown number. Typically n is large enough that the list doesn’t fit into main 
* 	memory.
*/

public class ReservoirSampling {

	// O(n) time solution:
	// Step 1: Create an array reservior[0..k-1] and copy first k items of stream[] to it.
	// Step 2: Now one by one consider all items from (k+1)th item to nth item
	//      2.1: Generate a random number from 0 to i where i is index of current item in stream[]. Let the generated random number is j
	//		2.2: If j is range 0 - k-1, replace reservoir[j] with arr[i]
	// A function to randomly select k items from stream[0..n-1]
	static void selectKItems(int[] streams, int n, int k) {
		int i; // index for elements in stream[];
		int reservoir[] = new int[k];// reservoir[] is the output array. Initialize it with first k elements from stream[];
		for(i = 0; i < k; i++) {
			reservoir[i] = streams[i];
		}
		Random r = new Random();
		// Iterate from the (k+1)th element to nth element
		for(; i < n; i++) {
			// pick a random index from 0 to i
			int j = r.nextInt(i+1);
			// If the randomly pick index is smaller than k, then replace the element present at the index with new element from the stream
			if(j < k) {
				reservoir[j] = streams[i];
			}
		}
		System.out.println("Following are k randomly selected items");
		System.out.println(Arrays.toString(reservoir));
	}
	// To Prove: The probability that any item stream[i] where 0 <= i < n will be in final reservoir[] is k/n.
	// There are two possible case
	// Case 1:	For last n-k stream items, i.e., for stream[i] where k <= i < n
	//			For stream[n-1]: The probability that the last item is in final reservoir 
	// 						   = The probability that one of the first k indexes is picked for last item 
	//						   = k/n (the probability of picking one of the k items from a list of size n)
	// 			For stream[n-2]: The probability that the second last item is in final reservoir[]
	//						   = [Probability that one of the first k indexes is picked in iteration for stream[n-2]] X [Probability that the index picked in iteration for stream[n-1] is not same as index picked for stream[n-2]]
	//						   = [k/(n-1)]*[(n-1)/n] = k/n.
	//	Case 2: For first k stream items, i.e., for stream[i] where 0 <= i < k
	// 			The probability that an item from stream[0..k-1] is in final array 
	//						   = Probability that the item is not picked when items stream[k], stream[k+1], …. stream[n-1] are considered
	//						   = [k/(k+1)] x [(k+1)/(k+2)] x [(k+2)/(k+3)] x … x [(n-1)/n] = k/n
	
}
