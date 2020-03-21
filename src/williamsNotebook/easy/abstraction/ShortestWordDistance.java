/**
 * 
 */
package williamsNotebook.easy.abstraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月20日 下午4:36:37
* Description:
* 	Given a list of words and two wordsword1_and_word2, return the shortest distance between these two words in the list.
* Assume that words =["practice", "makes", "perfect", "coding", "makes"].
* Input: word1 = “coding”, word2 = “practice”
* Output: 3
* Input: word1 = "makes", word2 = "coding"
* Output: 1
*/

public class ShortestWordDistance {

	// Follow Up 1: word1 and word2 is not null and guarantee in the word, and there are no duplicate
	public int shorestDistance(String[] words, String word1, String word2) {
		if(words == null || words.length == 0) return 0;
		int left = 0, right = words.length - 1;
		while(left < right) {
			if(words[left] == word1 && words[right] == word2 || words[left] == word2 && words[right] == word1) {
				return right - left;
			} else if(words[left] == word1 || words[left] == word2) {
				right--;
			} else if(words[right] == word1 || words[right] == word2) {
				left++;
			} else {
				left++;
				right--;
			}
		}
		return 0;
	}
	
	
	// Follow Up 2: you might be call multiple times, and there are contains duplicate;
	// Time: O(n)   Extra Space: O(
	private HashMap<String, ArrayList<Integer>> map;
	public ShortestWordDistance(String[] words) {
		this.map = new HashMap<String, ArrayList<Integer>>();
		for(int i = 0; i < words.length; i++) {
			if(!map.containsKey(words[i])) {
				map.put(words[i], new ArrayList<Integer>());
			}
			map.get(words[i]).add(i);
		}
	}
	public int shortest(String one, String two) {
		List<Integer> p1 = map.get(one);
		List<Integer> p2 = map.get(two);
		if(p1 == null || p2 == null) {
			return -1;
		}
		int shortest = Integer.MAX_VALUE;
		int i1 = 0, i2 = 0;
		while(i1 < p1.size() && i2 < p2.size()) {
			shortest = Math.min(Math.abs(p1.get(i1) - p2.get(i2)), shortest);
			if(p1.get(i1) < p2.get(i2)) {
				i1++;
			} else {
				i2++;
			}
		}
		return shortest;
	}

}
