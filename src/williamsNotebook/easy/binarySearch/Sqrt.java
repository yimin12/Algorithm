/**
 * 
 */
package williamsNotebook.easy.binarySearch;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 下午10:24:32
* Description:
* 	Implement double sqrt(double x) and x >= 0.
* 	Compute and return the square root of x.
* Example:
* 	Given n = 2 return 1.41421356
*/

public class Sqrt {

	// Assume that all the given double is greater than 1
	// binary search
	public double sqrt(double x) {
		double left = 0.0;
		double right = Math.max(x, 1.0);
		double eps = 1e-12;
		while(right - left > eps) {
			double mid = left + (right - left)/2;
			if(mid * mid < x) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return left;
	}
	// Newton Method
	public double sqrtII(double x) {
		double res = 1.0;
		double eps = 1e-12;
		while(Math.abs(res * res - x) > eps) {
			res = (res + x/res) / 2;
		}
		return res;
	}
}
