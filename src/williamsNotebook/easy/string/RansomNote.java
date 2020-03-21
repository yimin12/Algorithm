/**
 * 
 */
package williamsNotebook.easy.string;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月20日 下午10:41:10
* Description:
* 	Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return 
* 	true if the ransom note can be constructed from the magazines ; otherwise, it will return false.
* 	Each letter in the magazine string can only be used once in your ransom note.
* 	canConstruct("a", "b") -> false
* 	canConstruct("aa", "ab") -> false
* 	canConstruct("aa", "aab") -> true
*/

public class RansomNote {

	// use int array to record the character
	public boolean canConstruct(String ransomNote, String magazine) {
		int[] dict = new int[26];
		for(int i = 0; i < magazine.length(); i++) {
			dict[magazine.charAt(i) - 'a']++;
		}
		for(int j = 0; j < ransomNote.length(); j++) {
			dict[ransomNote.charAt(j) - 'a']--;
			if(dict[ransomNote.charAt(j)] < 0) return false;
		}
		return true;
	}
}
