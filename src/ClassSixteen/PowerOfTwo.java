package ClassSixteen;

public class PowerOfTwo {
	
	// you might need to concern about b < 0, so the return value is better to be double
	public double power(int a, int b) throws Exception {
		if(a==0) {
			// case 1.0 a = 0 and b <= 0
			if(b<=0) throw new Exception("A should not be 0");
			// case 1.1 a = 0 and b > 0
			return 0;
		}
		// case 2.0 a != 0 and b >= 0
		if(b >= 0) {
			return powerHelper(a, b);
		} else {
			// case 3.0 a != 0 and b < 0
			return 1/(double)powerHelper(a, b);
		}
	}
	// helper function
	public int powerHelper(int a, int b) {
		if(b == 0) return 1;
		int half = powerHelper(a, b/2);
		if(b % 2 == 0) {
			return half * half;
		} else {
			return half * half * a;
		}
	}
	
}
