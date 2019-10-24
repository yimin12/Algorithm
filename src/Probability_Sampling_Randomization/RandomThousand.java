/**
 * 
 */
package Probability_Sampling_Randomization;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月21日 上午11:49:38
* Description:
* 	Given a random generator random5(), the return value of random5() is 
* 	0 - 4 with equal probability. Use random5() to implement random1000()
*/

public class RandomThousand {
	public int random1000() {
		while(true) {
//			The following 4 lines of code is usually used to compute 
//			a0*x^0 + a1*x^1 + a2*x^2 + a3*x^3 + ... ak*x^k
			int num = 0;
			for(int i = 0; i < 5; i++) {
				num = num * 5 + RandomFive.random5();
			}
//			choose 3000 intead of 1000 to reduce the # of expected random5() calls;
			if(num < 3000) {
				return num % 3000;
			}
		}
	}
}
