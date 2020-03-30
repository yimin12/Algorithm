/**
 * 
 */
package williamsNotebook.easy.trieTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月29日 下午4:44:24
* Description:
* 	Implement typeahead. Given a string and a dictionary, return all words that contains the string as a substring. 
* 	The dictionary will give at the initialize method and wont be changed. The method to find all words with given 
* 	substring would be called multiple times
* Example:
* 	Given dictionary ={"Jason Zhang", "James Yu", "Bob Zhang", "Larry Shi"}
* 	search"Zhang", return["Jason Zhang", "Bob Zhang"].
* 	search"James", return["James Yu"].
*/

public class TypeHead {

	// You can implement trie here
	// Provide another solution rather than trie
	private HashMap<String, List<String>> map = new HashMap<String, List<String>>();
	public TypeHead(Set<String> dict) {
		for(String string:dict) {
			int len = string.length();
			for(int i = 0; i < len; i++) {
				for(int j = i + 1; j <= len; j++) {
					String temp = string.substring(i,j);
					if(!map.containsKey(temp)) {
						map.put(temp, new ArrayList<String>());
						map.get(temp).add(string);
					} else {
						List<String> index = map.get(temp);
						if(!string.equals(index.get(index.size() - 1))) {
							index.add(string);
						}
					}
				}
			}
		}
	}
	public List<String> search(String string){
		if(!map.containsKey(string)) {
			return new ArrayList<String>();
		} else {
			return map.get(string);
		}
	}
}
