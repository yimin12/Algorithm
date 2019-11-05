/**
 * 
 */
package Common;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月28日 上午10:34:57
* Description:
* 	The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
* 	(you may want to display this pattern in a fixed font for better legibility)
* 	P   A   H   N
	A P L S I I G
	Y   I   R
* 	And then read line by line:"PAHNAPLSIIGYIR"
* Assumptions:
* 	The given string is not null and rows > 0
* Examples:
* 	Input: s = "PAYPALISHIRING", numRows = 3
	Output: "PAHNAPLSIIGYIR"
	
	Input: s = "PAYPALISHIRING", numRows = 4
	Output: "PINALSIGYAHRPI"
	Explanation:

	P     I    N
	A   L S  I G
	Y A   H R
	P     I
*/

public class ZigZagConversion {
	public String convert(String s, int numRows) {
		if(numRows == 1 || s.length() < numRows) {
			return s;
		}
		int k = s.length();
		char[] result = new char[k];
		char[] input = s.toCharArray();
		int interval = 2 * (numRows - 1);
		int row = 0;
		int step = interval == 0?0:((interval - 2*row)%interval);
		int nextIndex = 0;
		result[0] = input[nextIndex];
		nextIndex += interval;
		if(nextIndex >= k) {
			row++;
			step = (interval - 2 * row)%interval;
			nextIndex = row;
		}
		boolean stepAdded  = false;
		for(int i = 1 ; i < k; i++) {
			result[i] = input[nextIndex];
			if(!stepAdded) {
				nextIndex += (step == 0) ? interval:step;
			} else {
				nextIndex += (interval - step);
				stepAdded = false;
			}
			if(nextIndex >= k) {
				row++;
				step = (interval - 2 * row) % interval;
				stepAdded = false;
				nextIndex = row;
			}
		}
		return String.valueOf(result);
	}
//	Method 2: Using the StringBuffer and keep collecting characters from original string to corresponding
//			  StringBuffer
	public String convertI(String s, int rows) {
		char[] c = s.toCharArray();
		int len = c.length;
		StringBuffer[] sb = new StringBuffer[rows];
		for(int i = 0; i < sb.length;i++) {
			sb[i]=new StringBuffer();
		}
		int i = 0;
		while(i < len) {
			for(int j = 0; j < rows && i < len; j++) {
//				This step is vertically down
				sb[j].append(c[i++]);
			}
			for(int j = rows - 2; j >= 1 && i < len; j--) {
//				this step is obliquely up
				sb[j].append(c[i++]);
			}
		}
		for(int k = 1; i < sb.length;i++) {
			sb[0].append(sb[k]);
		}
		return sb[0].toString();
	}
}
