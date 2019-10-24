/**
 * 
 */
package String;

import java.util.HashSet;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月15日 下午4:57:23
* Description:
* 	Given a string, find the longest substring without any repeating characters and return 
* 	the length of it. The input string is guaranteed to be not null.
  For example, the longest substring without repeating letters for "bcdfbd" is "bcdf", 
  	we should return 4 in this case.
*/


public class LongestSubstringNonRepeatingCharacter {
	public int longest(String input) {
//		Assumption: the input string is not null
//		the distinct set contains all distinct characters in the sliding window of (slow, fast);
		Set<Character> distinct = new HashSet<Character>();
		int slow = 0;
		int fast = 0;
		int longest = 0;
		while(fast < input.length()) {
			if(distinct.contains(input.charAt(fast))) {
//				if there is duplicate character, we need to move the slow pointer
				distinct.remove(input.charAt(slow++));
			} else {
//				if there is no duplicate character, we can slide fast pointer and we have a new sliding
//				window of (slow, fast) containing all distinct characters
				distinct.add(input.charAt(fast++));
				longest = Math.max(longest, fast - slow);
			}
		}
		return longest;
	}
}
