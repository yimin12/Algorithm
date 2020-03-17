/**
 * 
 */
package williamsNotebook.easy.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yimin Huang
 *		
 *		Given two strings s and t, determine if they are isomorphic.
 *		Two strings are isomorphic if the characters in s can be replaced to get t.
 *		All occurrences of a character must be replaced with another character while preserving 
 *		the order of characters. No two characters may map to the same character but a character may map to itself.
 *		For example,
 *			Given "egg", "add", return true.
 *			Given "foo", "bar", return false.
 *			Given "paper", "title", return true.
 *		You may assume both s and t have the same length.
 *
 * Algorithm Class
 */
public class IsomorphicString {

	// Method 1: Using HashTable 
	// Time O(2N) where N is the length of s, and Extra space is O(26)
	public boolean isIsomorphicString(String s, String t) {
		if(s.length() != t.length()) return false;
		
		Map<Character, Character> map = new HashMap<Character, Character>();
		for(int i = 0; i < s.length(); i++) {
			char sChar = s.charAt(i), tChar = t.charAt(i);
			if(!map.containsKey(sChar)) map.put(sChar, tChar);
			else if(map.get(sChar) != tChar) return false;
		}
		map.clear();
		for(int i = 0; i < t.length(); i++) {
			char sChar = s.charAt(i), tChar = t.charAt(i);
			if(!map.containsKey(tChar)) map.put(tChar, sChar);
			else if(map.get(tChar) != sChar) return false;
		}
		return true;
	}
	
}
