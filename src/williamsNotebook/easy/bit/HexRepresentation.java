/**
 * 
 */
package williamsNotebook.easy.bit;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��17�� ����2:49:05
* Description: Change Decimal to Hex
*/
public class HexRepresentation {
//	Assumption: number >= 0;
	public String hex(int number) {
		String prefix = "0x";
//		handle the special case of 0 first
		if(number == 0) {
			return prefix + "0";
		}
//		using StringBuilder so append operation is more effiient
		StringBuilder sb = new StringBuilder();
		while(number > 0) {
			int rem = number % 16;
			if(rem < 10) {
				sb.append((char)('0' + rem));
			} else {
				sb.append((char)(rem - 10 + 'A'));
			}
//			divide by 16
			number >>>= 4;
		}
//		reverse it at last so in all complexity is O(n), remember to reverse it 
		return prefix + sb.reverse().toString();
	}
}
