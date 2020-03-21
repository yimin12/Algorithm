/**
 * 
 */
package williamsNotebook.easy.string;

import java.util.HashSet;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月20日 下午8:41:28
* Description:
* 	Every email consists of a local name and a domain name, separated by the @ sign.
* 	For example, inalice@leetcode.com, aliceis the local name, andleetcode.comis the domain name.
* 	Besides lowercase letters, these emails may contain'.'s or'+'s.
* 	If you add periods ('.') between some characters in thelocal namepart of an email address, mail 
* 	sent there will be forwarded to the same address without dots in the local name. For example,
* 	"alice.z@leetcode.com"and"alicez@leetcode.com"forward to the same email address. 
* 	(Note that this rule does not apply for domain names.)
* 	If you add a plus ('+') in thelocal name, everything after the first plus sign will be ignored.
*   This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to 
*   my@email.com. (Again, this rule does not apply for domain names.)
*   It is possible to use both of these rules at the same time.
*   
*/
public class UniqueEmailAddress {

	// Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
	// Output: 2
	// Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" 
	public int numUniqueEmails(String[] emails) {
		if(emails == null) {
			return 0;
		}
		HashSet<String> set = new HashSet<String>();
		for(String email : emails) {
			set.add(convertEmailAddress(email));
		}
		return set.size();
	}
	private String convertEmailAddress(String email) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		boolean beforeAtSign = true;
		while(i < email.length()) {
			if(email.charAt(i) == '@') {
				i++;	
				beforeAtSign = false;
			} else if(beforeAtSign && email.charAt(i) == '.') {
				i++;
			} else if(beforeAtSign && email.charAt(i) == '+') {
				while(i < email.length()) {
					i++;
					if(email.charAt(i) == '@') {
						beforeAtSign = false;
						break;
					}
				}
			}
			sb.append(email.charAt(i++));	
		}
		return sb.toString();
	}
}
