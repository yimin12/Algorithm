/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yimin Huang
 *	
 *		Given an array of strings, return all groups of strings that are anagrams.
 *		All inputs will be in lower-case.
 *		Input:
 *			s: "cbaebabacd" p: "abc"
 *		Output:
 *			[0, 6]
 *		The substring with start index = 0 is "cba", which is an anagram of "abc".
 *		The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Algorithm Class
 */
public class Anagrams {
	
	// Solution 1: naive solution
	public List<String> anagrams(String[] strings){
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for(String string:strings) {
			String sortedWord = labelStr(string);
			if(!map.containsKey(sortedWord)) map.put(sortedWord, new ArrayList<String>());
			map.get(sortedWord).add(string);
		}
		List<String> list = new ArrayList<String>();
		for(List<String> listWord : map.values()) {
			if(listWord.size() > 1) list.addAll(listWord);
		}
		return list;
	}
	private String labelStr(String s) {
		int[] count = new int[256];
		for(int i = 0; i < s.length(); i++) {
			count[s.charAt(i)]++;
		}
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < 256; i++) {
			if(count[i] > 0) str.append(i).append(count[i]);
		}
		return str.toString();
	}
}
