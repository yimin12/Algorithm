/**
 * 
 */
package williamsNotebook.easy.conversion;

/**
 * @author yimin Huang
 *		
 *		Given a positive integer, return its corresponding column title as appear in an Excel sheet.
	 *For example:
	    1 -> A
	    2 -> B
	    3 -> C
	    ...
	    26 -> Z
	    27 -> AA
	    28 -> AB 

 *
 * Algorithm Class	
 */
public class ExcelSheet {

	public String convertToTitle(int n) {
		StringBuilder sb = new StringBuilder();
		while(n > 0) {
			n--;
			sb.append((char)(n % 26 + 'A'));
			n = n / 26;
		}
		return sb.reverse().toString();
	}
	
	public int titleToNumber(String s) {
		int num = 0;
		for(int i = 0; i < s.length(); i++) {
			num = num * 26 + (int)(s.charAt(i) - 'A' + 1);
		}
		return num;
	}
	public static void main(String[] args) {
		ExcelSheet solution = new ExcelSheet();
		String convertToTitle = solution.convertToTitle(28);
		System.out.println(convertToTitle);
	}
}
