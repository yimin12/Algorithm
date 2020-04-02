/**
 * 
 */
package williamsNotebook.easy.string.laioffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��15�� ����5:08:20
* Description:
* 	Find all occurrence of anagrams of a given string s in a given string l. 
* 	Return the list of starting indices.
  Assumptions:
	s is not null or empty.
	l is not null.
  Examples
	l = "abcbac", s = "ab", return [0, 3] since the substring with length 2 
	starting from index 0/3 are all anagrams of "ab" ("ab", "ba").
*/
public class AllAnagrams {
//	Find all anagrams of string s in string l, return all the starting indices.
//	Assumption: s, l are not null, s is not empty
	public List<Integer> allAnagrams(String s, String l){
		List<Integer> result = new ArrayList<Integer>();
		if(l.length() == 0) {
			return result;
		}
//		when s is longer than l, there is no way any of the substrings of l could be an anagram of s.
		if(s.length() > l.length()) {
			return result;
		}
//		This map records for each of the distinct characters in s, how many characters are needed.
//		e.g. s = "abbc" , map = {'a':1, 'b':2, 'c':1}
//		when we get an instance of 'a' in l, we let count of 'a' decremented by 1, and only when
//		the count is from 1 to 0, we have 'a' totally matched
		Map<Character, Integer> map = countMap(s);
//		Record how many distinct characters have been matched. 
//		only when all the distinct characters are matched, match == map.size(), we find an anagram.
		int match = 0;
//		We have sliding window of size s.length(), and since the size is fixed, we only need to record
//		the end index of the sliding window. Also, when move the sliding window by one step from left
//		to right, what we only need change is
//		1. remove the leftmost character at the previous sliding window
//		2.add the rightmost character at the current sliding window/
		for(int i = 0; i < l.length(); i++) {
//			handle the new added character(rightmost) at the current sliding window
			char temp = l.charAt(i);
			Integer count = map.get(temp);
			if(count!=null) {
//				The number of needed count should be --. and only when the count is from 1 to 0, we 
//				find an additional match of distinct character
				map.put(temp, count - 1);
				if(count==1) {
					match++;
				}
			}
//			handle the leftmost character at the previous sliding window
			if(i >= s.length()) {
				temp = l.charAt(i - s.length());
				count = map.get(temp);
				if(count!=null) {
//					the number of needed count should be ++, and only when the count is from 0 to 1
//					we are short for one match of distinct character
					map.put(temp,count + 1);
					if(count == 0) {
						match--;
					}
				}
			}
//			for the current sliding window, if all the distinct characters are matched (the count are all zero)
			if(match == map.size()) {
				result.add(i - s.length() +1);
			}
		}
		return result;
	}
	private Map<Character, Integer> countMap(String s){
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(char ch : s.toCharArray()) {
			Integer count = map.get(ch);
			if(count == null) {
				map.put(ch, 1);
			} else {
				map.put(ch, count + 1);
			}
		}
		return map;
	}
}
