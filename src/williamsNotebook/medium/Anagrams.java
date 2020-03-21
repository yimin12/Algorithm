/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	// Follow Up 2: Group Anagrams
	// Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
	// Output:
	// [
	//  ["ate","eat","tea"],
	//  ["nat","tan"],
	//  ["bat"]
	// ]
	// Time Complexity: O(NK \log K)O(NKlogK), where NN is the length of strs, and KK is the maximum 
	// length of a string in strs. The outer loop has complexity O(N)O(N) as we iterate through each 
	// string. Then, we sort each string in O(K \log K)O(KlogK) time.
	public List<List<String>> groupAnagrams(String[] strs){
		if(strs.length == 0) return new ArrayList<List<String>>();
		Map<String, List<String>> res = new HashMap<String, List<String>>();
		// Assume that there only 26 lower case character
		int[] count = new int[26];
		for(String s:strs) {
			// reset the counting board
			Arrays.fill(count, 0);
			for(char c : s.toCharArray()) {
				count[c - 'a']++;
			}
			StringBuilder sb = new StringBuilder("");
			for(int i = 0; i < 26; i++) {
				sb.append("#");
				while(count[i] > 0) {
					sb.append(count[i]--);
				}
			}
			String key = sb.toString();
			if(!res.containsKey(key)) res.put(key, new ArrayList<>());
			res.get(key).add(s);
		}
		return new ArrayList<List<String>>(res.values());
	}
}
