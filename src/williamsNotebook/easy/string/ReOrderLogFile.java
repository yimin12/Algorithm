/**
 * 
 */
package williamsNotebook.easy.string;

import java.util.Arrays;
import java.util.Comparator;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月20日 下午10:52:19
* Description:
* 	You have an array of logs. Each log is a space delimited string of words.
* 	For each log, the first word in each log is an alphanumericidentifier. Then, either:
* 	Each word after the identifier will consist only of lowercase letters, or;
* 	Each word after the identifier will consist only of digits.
* 	We will call these two varieties of logsletter-logs_and_digit-logs. 
* 	It is guaranteed that each log has at least one word after its identifier.
* 	Reorder the logs so that all of the letter-logs come before any digit-log. 
* 	The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case 
* 		of ties. The digit-logs should be put in their original order.
*/

public class ReOrderLogFile {

	public String[] reorderLogFile(String[] logs) {
		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String log1, String log2) {
				int i1 = log1.indexOf(" ");
				int i2 = log2.indexOf(" ");
				char ch1 = log1.charAt(i1 + 1);
				char ch2 = log2.charAt(i2 + 1);
				if(Character.isDigit(ch1)) {
					if(Character.isDigit(ch2)) {
						return 0;
					} else {
						return 1;
					}
				}
				if(Character.isDigit(ch2)) {
					return -1;
				}
				int comp = log1.substring(i1 + 1).compareTo(log2.substring(i2 + 1));
				if(comp == 0) {
					return log1.substring(0, i1).compareTo(log2.substring(0, i2));
				}
				return comp;
			}
		};
		Arrays.sort(logs, comparator);
		return logs;
	}
}
