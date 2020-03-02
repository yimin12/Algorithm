
package williamsNotebook.easy.conversion;

/**
 * @author yimin Huang
 *	The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 *	(you may want to display this pattern in a fixed font for better legibility)
 *	P     A     H     N
	A  P  L  S  I  I  G
	Y     I     R
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * 	string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * Algorithm Class
 */
public class ZigZagPrint {

	// Time ~ O(N), Space ~ O(1)
	public String zigZag(String s, int rows)  {
		if(rows == 1) return s;
		StringBuilder sb = new StringBuilder();
		int skip_pattern = rows + (rows - 2);
		int skip_diag_pattern = skip_pattern;
//		fill the form row by row
		for(int i = 0; i < rows; i++) {
			if(i != 0 && i != rows - 1) skip_diag_pattern -= 2;
			int index = i;
			while(index < s.length()) {
				sb.append(s.charAt(index));
				if(i != 0 && i != rows - 1 && index + skip_diag_pattern < s.length()) {
					sb.append(s.charAt(index + skip_diag_pattern));
				}
				index += skip_pattern;
			}
		}
		return sb.toString();
 	}
	public static void main(String[] args) {
		ZigZagPrint solution = new ZigZagPrint();
		String zigZag = solution.zigZag("PAYPALISHIRING", 3);
		System.out.println(zigZag);
	}
}
