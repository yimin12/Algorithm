/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


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
	
	Master master = new Master() {
		
		@Override
		public int guess(String word) {
			// TODO Auto-generated method stub
			return 0;
		}
	};

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
	// All words are generated randomly, So why not we also guess a random word and let it be whatever will be
	// Time: O(n) and Space: O(n)
	public void findSecretWord(String[] wordList, Master master) {
		for(int i = 0, x = 0; i < 10 && x < 6; i++) {
			String guess = wordList[new Random().nextInt(wordList.length)];
			x = master.guess(guess);
			List<String> wordList2 = new ArrayList<String>();
			// add all candidate(have same number of matches) to the list
			for(String w : wordList) {
				if(match(guess, w) == x) wordList2.add(w);
			}
			wordList = wordList2.toArray(new String[wordList2.size()]);
		}
	}
	
	// Method 3: Minimax
	// Time: O(n^2) and Space: O(n)
	public void findSecretWordMiniMax(String[] wordList, Master master) {
		for(int i = 0, x = 0; i < 10 && x < 6; i++) {
			HashMap<String, Integer> count = new HashMap<String, Integer>();
			//	计算每个单词之间可能的相关度，match == 0 则，重合度最低。 记录重合度。
			//  核心思想： 猜重合度高得词，最有可能获得你想要的信息。
			for(String w1 : wordList) {
				for(String w2 : wordList) {
					if(match(w1, w2) == 0) {
						count.put(w1, count.getOrDefault(w1, 0) + 1);
					}
				}
			}
			String guess = "";
			int min0 = 100;
			for(String w : wordList) {
				// 猜重合度最高的词，也就是map中Value最小的。
				if(count.getOrDefault(w, 0) < min0) {
					guess = w;
					min0 = count.getOrDefault(w, 0);
				}
			}
			x = master.guess(guess);
			List<String> wordList2 = new ArrayList<String>();
			// 在原来的字典中，排除错误答案，保留secret信息，提高下一轮的正确率
			for(String w : wordList) {
				if(match(guess, w) == x) {
					wordList2.add(w);
				}
			}
			wordList = wordList2.toArray(new String[0]);
		}
	}
	// Method 3: Count Occurrence of Characters 
	// Time: O(n) and Space: O(n)
	public void findSecretWordIII(String[] wordList, Master master) {
		for(int t = 0, x = 0; t < 10 && x < 6; t++) {
			int count[][] = new int[6][26], best = 0;
			// 那个单词出现的多，就选谁
			for(String w : wordList) {
				for(int i = 0; i < 6; i++) {
					count[i][w.charAt(i) - 'a']++;
				}
			}
			String guess = wordList[0];
			for(String w : wordList) {
				int score = 0;
				for(int i = 0; i < 6; i++) {
					score += count[i][w.charAt(i) - 'a'];
				}
				if(score > best) {
					guess = w;
					best = score;
				}
			}
			x = master.guess(guess);
			List<String> wordList2 = new ArrayList<String>();
			for(String w : wordList) {
				if(match(guess, w) == x) {
					wordList2.add(w);
				}
			}
			wordList = wordList2.toArray(new String[0]);
		}
	}
}

interface Master{
	
	public int guess(String word);
}
