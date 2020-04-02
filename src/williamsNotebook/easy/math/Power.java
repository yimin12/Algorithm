package williamsNotebook.easy.math;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��8�� ����8:01:46
* Description: 
* 	Calculate a to the power of b(Simple way) assume that a is greater than 0 
* 
*/

public class Power {
	// Assume that b >= 0 and a is greater than 0
	public long power(int a, int b) {
		if(b==0) {
			return 1;
		}
		if(a==0) {
			return 0;
		}
		long half = power(a, b / 2);
		return b % 2 == 0? half * half : half * half * a;
		
	}
}
