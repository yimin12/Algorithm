/**
 * 
 */
package williamsNotebook.easy.math;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 下午7:08:36
* Description:
* 	Write an algorithm to determine the GCD of N positive numbers.
*/
public class GreatestCommonDivisor {

//	1）令c=gcd(a,b），则设a=mc，b=nc
//	2）r = a mod b，所以r = a - k*b = mc - k*nc = (m - kn) * c。即，r = (m - kn) * c
//	3）由r = (m - kn) * c 可知：c也是r的因数
//	4）可以肯定m - kn与n互质（why？)
//			假设他们不互质，必然存在大于1的整数d，使得m-kn = xd, n = yd。那么，m = xd + kyd = (x + ky)d
//			那么，a = mc = (x + ky)dc , b = nc = ydc 。=> a,b的最大公约数为dc，而不是c。
//	5）既然m - kn与n互质，所以c = gcd(r,b)。
//	结论，gcd(a,b)=gcd(b,r）。
		
	// It will be iterated in O(logN) times, proof by math
	private int gcd(int a, int b) {
		int temp;
		while(b > 0) {
			temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
	// GCD(a, b, c) = GCD(a, GCD(b, c)) = GCD(GCD(a, b), c) = GCD(GCD(a, c), b)
	public int generalizedGCD(int num, int[] array) {
		if(num == 0 || array == null) {
			return 0;
		}
		if(num < 2) {
			return array[0];
		}
		int res = array[0];
		for(int i = 1; i < num; i++) {
			res = gcd(res,  array[i]);
		}
		return res;
	}
}
