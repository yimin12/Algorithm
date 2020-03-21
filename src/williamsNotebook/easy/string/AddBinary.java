/**
 * 
 */
package williamsNotebook.easy.string;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月20日 下午5:38:26
* Description:
* 	Given two binary strings, return their sum (also a binary string).
*	The input strings are both non-empty and contains only characters1or 0.
*	Input: a = "11", b = "1" Output: "100"
*/
public class AddBinary {

	
	// Method 1: Use basic manipulation and string builder, simulate decimal operation
	// Time: O(n) And Extra Space for O(n)
	public String addBinary(String a, String b) {
		if(a == null || b == null) return a == null ? b : a;
		if(a.isEmpty() || b.isEmpty()) return a.isEmpty() ? b : a;
		StringBuilder sb = new StringBuilder();
		int one = a.length() - 1, two = b.length() - 1; 
		int carry = 0;
		while(one >= 0 || two >= 0) {
			int x = (one < 0 ? 0 : a.charAt(one) - '0');
			int y = (two < 0 ? 0 : b.charAt(two) - '0');
			int sum = x + y + carry;
			sb.append(sum % 2);
			carry = sum / 2;
			one--;
			two--;
		}
		if(carry > 0) {
			sb.append(carry);
		}
		return sb.reverse().toString();
	}
	
	// Method 2: Use Bit manipulation for binary operation, using Xor	
	public String addBinaryI(String a, String b) {
		if(a == null || b == null) return a == null ? b : a;
		if(a.isEmpty() || b.isEmpty()) return a.isEmpty() ? b : a;
		StringBuilder sb = new StringBuilder();
		int one = a.length() - 1, two = b.length() - 1; 
		int carry = 0;
		while(one >= 0 || two >= 0) {
			int x = (one < 0 ? 0 : a.charAt(one) - '0');
			int y = (two < 0 ? 0 : b.charAt(two) - '0');
			sb.append(x^y^carry);
			carry = (x + y + carry >= 2 ? 1 : 0);
			one--;
			two--;
		}
		return sb.reverse().toString();
	}
	
	public static void main(String[] args) {
		AddBinary solution = new AddBinary();
		String addBinary = solution.addBinaryI("1010", "1");
		System.out.println(addBinary);
	}
}
