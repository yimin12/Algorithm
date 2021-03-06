/**
 * 
 */
package williamsNotebook.easy.string;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2020��3��20�� ����10:22:49
* Description:
* 	You are given a license key represented as a string S which consists only 
* 	alphanumeric character and dashes. The string is separated into N+1 groups by N dashes.
*   Given a number K, we would want to reformat the strings such that each group containsexactlyK 
*   characters, except for the first group which could be shorter than K, but still must contain 
*   at least one character. Furthermore, there must be a dash inserted between two groups and all 
*   lowercase letters should be converted to uppercase.
*   Given a non-empty string S and a number K, format the string according to the rules described above.
*   Input: S = "5F3Z-2e-9-w", K = 4   Output:  "5F3Z-2E9W"
*   Explanation:
*   	The string S has been split into two parts, each part has 4 characters. Note that the two extra dashes are not needed and can be removed.
*/
public class LicenseKeyFormatting {

	
	// Iterate from the end to the front, in one pass
	// Time: O(n) and Extra Space: O(n) for using string builder
	public String licenseKeyFormatting(String s, int k) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for(int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if(c == '-') continue;
			if(count == k) {
				sb.append("-");
				count = 0;
			}
			sb.append(Character.toUpperCase(c));
			count++;
		}
		return sb.reverse().toString();
	}
}
