/**
 * 
 */
package williamsNotebook.easy.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月20日 下午1:22:07
* Description:
* 	A website domain like "discuss.leetcode.com" consists of various subdomains. 
* 	At the top level, we have "com", at the next level, we have "leetcode.com", 
* 	and at the lowest level, "discuss.leetcode.com". When we visit a domain like 
* 	"discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.
* 	Now, call a "count-paired domain" to be a count (representing the number of visits this domain received), 
* 	followed by a space, followed by the address. An example of a count-paired domain might be "9001 discuss
* 	.leetcode.com".
*	 We are given a listcpdomainsof count-paired domains. We would like a list of count-paired domains, 
*	(in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.
*	Input: ["9001 discuss.leetcode.com"] Output: ["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
*	Explanation: We only have one website domain: "discuss.leetcode.com". As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.
*/

public class SubdomainVisitCount {

	// split the cpdomains and add it with the help of HashMap
	public List<String> subdomainVisits(String[] cpdomains){
		Map<String, Integer> counts = new HashMap<String, Integer>();
		for(String domain:cpdomains) {
			// split by the first string
			String[] cpinfo = domain.split("\\s");
			System.out.println(Arrays.toString(cpinfo));
			// split by the "."
			String[] frags = cpinfo[1].split("\\.");
			int count = Integer.valueOf(cpinfo[0]);
			String cur = "";
			for(int i = frags.length - 1; i >= 0; i--) {
				// String concatenation
				cur = frags[i] + (i < frags.length - 1 ? "." : "") + cur;
				counts.put(cur, counts.getOrDefault(cur, 0) + count);
			}
		}
		List<String> ans = new ArrayList<String>();
		for(String dom: counts.keySet()) {
			ans.add("" + counts.get(dom) + " " + dom);
		}
		return ans;
	}
	public static void main(String[] args) {
		SubdomainVisitCount solution = new SubdomainVisitCount();
		String[] cpdomains ={"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
		List<String> subdomainVisits = solution.subdomainVisits(cpdomains);
		System.out.println(subdomainVisits.toString());
	}
}
