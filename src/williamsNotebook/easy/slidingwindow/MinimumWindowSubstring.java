/**
 * 
 */
package williamsNotebook.easy.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yimin Huang
 *		Given a string S and a string T, find the minimum window in S which will contain all the characters in T
 *		Input: S = “ADOBECODEBANC”
 *			   T = “ABC”
 *		Output: “BANC”
 *		
 *		Type: Sliding Window of size unfixed
 * Algorithm Class
 */
public class MinimumWindowSubstring {

	// Method 1: use two pointer to simulate a sliding window 
	// Time: O(n) Space: O(1)
	public String minWindow(String target, String pattern) {
		// sanity check
		if(target == null || pattern == null || target.length() < pattern.length()) return "";
		Map<Character, Integer> dict = constructWordDict(pattern);
		// set the recorder
		int slow = 0, fast = 0, minLen = Integer.MAX_VALUE, matchCount = 0, start = 0;
		for(; fast < target.length(); fast++) {
			// case 1.0: when the composition have not completed
			char c = target.charAt(fast);
			Integer count = dict.get(c);
			if(count == null) {
				continue;
			} else if(count == 1) {
				matchCount++;
				dict.put(c, count - 1);
			} else {
				dict.put(c, count - 1);
			}
			//System.out.println("fast index    " + fast + "  match " + matchCount);
			// case 2.0: when the composition have completed, start to move slow index
			while(matchCount == dict.size()) {
				c = target.charAt(slow);
				count = dict.get(c);
				if(count == null) {
					slow++;
				} else if(count < 0) {
					dict.put(c, count + 1);
					slow++;
				} else if(count == 0){
					dict.put(c, count + 1);
					matchCount--;
					if(minLen > fast - slow + 1) {
						minLen = fast - slow + 1;
						start = slow;
					}
					//System.out.println("slow index  " + slow + "   match " + matchCount + " minLength  " + minLen);
					slow++;
				} 
				
			}
			
		}
		return target.substring(start, start + minLen);
	}
	private HashMap<Character, Integer> constructWordDict(String pattern){
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(char c : pattern.toCharArray()) {
			Integer count = map.get(c);
			if(count == null) {
				map.put(c, 1);
			} else {
				map.put(c, count + 1);
			}
		}
		
		return map;
	}
	
	// FollowUp 2: You are given a string, S, and a list of words, L, that are all of the same length. 
	// Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and 
	// without any intervening characters.
	/*
	 * For example, given: S: "barfoothefoobarman" L: ["foo", "bar"] 
	 * You should return the indices: [0,9]. (order does not matter).
	 */
	// Time ~ O(NM), Space ~ O(M) where N = lenS, M = len of L's word
	public List<Integer> findSubstring(String s, String[] array){
		List<Integer> res = new ArrayList<Integer>();
		int wordLen = array[0].length();
		if(s == null || s.length() == 0 || wordLen == 0 || s.length() < wordLen*array.length) return res;
		Map<String, Integer> dict = wordCount(array);
		// iterate the whole string
		for(int i = 0; i < wordLen; i++) {
			int matchCount = 0, prev = i;
			Map<String, Integer> word = new HashMap<String, Integer>();
			for(int j = i; j <= s.length() - wordLen; j += wordLen) {
				String curWord = s.substring(j, j + wordLen);
				// Case 1.0, if the word does not belong to dictionary
				if(!dict.containsKey(curWord)) {
					// reset to 0 because the concatenation requirement
					matchCount = 0;
					prev = j + wordLen; // move to the next word
					word.clear();
				} else {
					// Case 2.0, if word exist in word Dictionary
					// record the word, handling the multiple same word's problem
					if(!word.containsKey(curWord)) dict.put(curWord, 1);
					else dict.put(curWord, dict.get(curWord) + 1);
					// stop adding length (count) if we have more curword's than dict does
					if(word.get(curWord) <= dict.get(curWord)) matchCount++;
					else {
						while(word.get(curWord) > dict.get(curWord)) {
							String prevWord = s.substring(prev, prev + wordLen);
							word.put(prevWord, word.get(prevWord) - 1);
							if(word.get(prevWord) < dict.get(prevWord)) matchCount--;
							prev += wordLen;
						}
					}
					if(matchCount == array.length) {
						res.add(prev);
						String prevWord = s.substring(prev, prev + wordLen);
						word.put(prevWord, word.get(prevWord) - 1);
						matchCount--;
						prev += wordLen;
					}
				}
			}
		}
		return res;
	}
	private Map<String, Integer> wordCount(String[] array){
		HashMap<String, Integer> wordDict = new HashMap<String, Integer>();
		for(String string : array) {
			Integer count = wordDict.get(string);
			if(count == null) {
				wordDict.put(string, 1);
			} else {
				wordDict.put(string, count + 1);
			}
		}
		return wordDict;
	}
	
	
	public static void main(String[] args) {
		MinimumWindowSubstring solution = new MinimumWindowSubstring();
		String minWindow = solution.minWindow("ZZADOBECODEBANC", "AB");
		System.out.println(minWindow);
		int[] array = new int[] {1,2};
		List<Integer> index = solution.findSubstring("barfoothefoobarman", new String[]{"foo","bar"});
		System.out.println(index.toString());
	}
}
