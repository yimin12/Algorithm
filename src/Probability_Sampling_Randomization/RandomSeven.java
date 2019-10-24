/**
 * 
 */
package Probability_Sampling_Randomization;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月21日 上午11:07:34
* Description:
* 	Given a random generator random5(), the return value of 
* 	random5() is 0 - 4 with equal probability. Use random5() to implement random7().
*/

public class RandomSeven {
//	We should directly call RandomFive.random5() to get a random number in the range of 0-4
	public int random7() {
		for(;;) {
//			to generate a uniformly distributed 0-24 number
			int random = 5 * RandomFive.random5();
//			we only care about the first 21 numbers and should ignore 
//			and try again for the number >= 21
			if(random < 21) {
				return random % 7;
			}
		}
	}
}
