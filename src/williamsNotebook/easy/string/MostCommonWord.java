/**
 * 
 */
package williamsNotebook.easy.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月20日 下午11:38:06
* Description:
* 	Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words. It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
* 	Words in the list of banned words are given in lowercase, and free of punctuation. Words in the paragraph are not case sensitive. The answer is in lowercase.
* 	Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit." banned = ["hit"]
* 	Output:  "ball"
*/

public class MostCommonWord {

	// Method 1: HashMap and Counter
	// remove all punctuations, change to lowercase
	// words count for each word not in banned set
	// return the most common word
	// Time: O(n) and Space: O(n)
	public String mostCommon(String paragraph, String[] banned) {
		String[] words = paragraph.replaceAll("[^a-zA-Z\\s]", " ").toLowerCase().split("\\s+");
		Map<String, Integer> map = new HashMap<String, Integer>();
		Set<String> set = new HashSet<String>();
		for(String ban:banned) {
			set.add(ban);
		}
		String res = "";
		int maxCount = 0;
		for(String word: words) {
			if(!set.contains(word)) {
				map.put(word, map.getOrDefault(word, 0) + 1);
				if(map.get(word) > maxCount) {
					maxCount = map.get(word);
					res = word;
				}
			}
		}
		return res;
	}
}
