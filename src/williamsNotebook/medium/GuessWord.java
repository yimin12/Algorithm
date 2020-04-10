/**
 * 
 */
package williamsNotebook.medium;

import java.util.HashMap;
import java.util.Map;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月9日 下午4:57:11
* Description:
* 	We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
* 	You may callmaster.guess(word) to guess a word. The guessed word should have typestring and must be from the original list with 6 lowercase letters.
* 	This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word. Also, if your guess is not in the given wordlist, it will return-1instead
* 	For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls tomaster.guess and at least one of these guesses was the secret, you pass the testcase.
* 	Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list. The letters of each word in those testcases were chosen independently at random from'a'to'z', 
* 	such that every word in the given word lists is unique.
* Example 1:
  Input:
 	secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]
  Explanation:
	master.guess("aaaaaa")
	 returns -1, because 
	"aaaaaa"
	 is not in wordlist.
	
	master.guess("acckzz") 
	returns 6, because 
	"acckzz"
	 is secret and has all 6 matches.
	
	master.guess("ccbazz")
	 returns 3, because
	 "ccbazz"
	 has 3 matches.
	
	master.guess("eiowzz")
	 returns 2, because 
	"eiowzz"
	 has 2 matches.
	
	master.guess("abcczz")
	 returns 4, because 
	"abcczz"
	 has 4 matches.
	
	We made 5 calls to master.guess and one of them was the secret, so we pass the test case
*/
public class GuessWord {

	// Intuition :
	// Take a word from wordList and guess it. Get the matches of this word. Update our wordList and keep only the same 
	// matches to our guess. Key point is which word should guess first.
	private int match(String a, String b) {
		int matches = 0;
		for(int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == b.charAt(i)) {
				matches++;
			}
		}
		return matches;
	}
}

interface Master{
	
	public int guess(String word);
}
