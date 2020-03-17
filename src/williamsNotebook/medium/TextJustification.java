/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yimin Huang
 *		
 *	Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
 *	You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so 
 *	that each line has exactly L characters
 *	Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the
 *  empty slots on the left will be assigned more spaces than the slots on the right.	
 *	For the last line of text, it should be left justified and no extra space is inserted between words
 *	For example,
 *	words: ["This", "is", "an", "example", "of", "text", "justification."]
 *	L: 16.
 *	Return the formatted lines as:
 *	["This      is      an","example  of text","justification.       "]
 *		Assumptiom: Each word is guaranteed not to exceed L in length.
 *	
 * Algorithm Class
 */
public class TextJustification {
	
	// Method 1: Linear Scan 
	// Time ~ O(N), Space ~ O(1)
	public List<String> fullJustify(String[] words, int maxWidth){
		List<String> res = new ArrayList<String>();
		int strLen = 0;
		int start = 0, end = 0;
		for(int i = 0; i < words.length; i++) {
			if(strLen == 0 || strLen + words[i].length() < maxWidth) {
				if(strLen > 0) strLen++; // before adding a new word, you should place an empty space
				strLen += words[i].length();
				end = i;
			} else {
				res.add(formLine(words, start, end, strLen, maxWidth));
				strLen = words[i].length();
				start = i;
				end = i;
			}
		}
		res.add(formLine(words, start, end, strLen, maxWidth));
		return res;
	}
	private String formLine(String[] words, int start,int end, int strLen, int maxWidth) {
		int N = end - start; // number of intervals
		int M = maxWidth - strLen; // extra space
		StringBuilder sb = new StringBuilder();
		if(N == 0 || end == words.length - 1) { 
			// left justify for one word or last line
			for(int i = start; i <= end; i++) {
				if(i > start) sb.append(" ");
				sb.append(words[i]);
			}
			for(int i = 0; i < M; i++) {
				sb.append(" ");
			}
		} else {
			// distributed words evenly
			for(int i = start; i <= end; i++) {
				if(i == start) sb.append(words[i]);
				else {
					// add empty space before adding each word
					for(int j = 0; j < 1 + M / N; j++) {
						// evenly distributed space
						sb.append(" ");
						// add extra space in the first intervals
						if(i <= start + M % N) sb.append(" ");
						sb.append(words[i]);
					}
				}
			}
		}
		return sb.toString();
	}
}
