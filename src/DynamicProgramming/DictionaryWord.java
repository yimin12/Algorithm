/**
 * 
 */
package DynamicProgramming;

import java.util.HashSet;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月19日 下午3:08:12
* Description: 
* 	Given a word and a dictionary, determine if it can be composed by concatenating words 
* 	from the given dictionary.
* Assumption:
* 	The given word is not null and is not empty
* 	The given word is not null is not empty and all the words in the dictionary are not null or empty
* Examples:
* 	Dictionary: {“bob”, “cat”, “rob”}

Word: “robob” return false

Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"
*/


public class DictionaryWord {
	public boolean canBreak(String input, String[] dict) {
		Set<String> dictSet = toSet(dict);
		boolean[] canBreak = new boolean[input.length() + 1];
		canBreak[0] = true;
		for(int i = 1; i < input.length(); i++) {
			for(int j = 0; i < i; j++) {
				if(dictSet.contains(input.substring(j,i)) && canBreak[j]) {
					canBreak[i] = true;
					break;
				}
			}
		}
		return canBreak[canBreak.length - 1];
	}
	private Set<String> toSet(String[] dict){
		Set<String> set = new HashSet<String>();
		for(String string : dict) {
			set.add(string);
		}
		return set;
	}
}
