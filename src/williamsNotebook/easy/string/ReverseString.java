/**
 * 
 */
package williamsNotebook.easy.string;

import java.util.Deque;
import java.util.LinkedList;

import williamsNotebook.common.SWAP;

/**
 * @author yimin Huang
 *
 *	Given an input string, reverse the string word by word.
 *	For example,
 *		Given s = "the sky is blue",
 *		return "blue is sky the".
 *
 * Algorithm Class
 */
public class ReverseString {
	
	
	// Situation 1 : Reverse Single words
	// Method 1: Iterative way
	public String reverseSingleIterative(String input) {
		if(input == null || input.length() <= 0) return input;
		char[] array = input.toCharArray();
		for(int left = 0, right = array.length - 1; left < right; left++, right--) {
			SWAP.charSwap(array, left, right);
		}
		return new String(array);
	}
	// Method 2: Recursion 
	public String reverseSingleRecursion(String input) {
		if(input == null || input.length() <= 1) {
			return input;
		}
		char[] array = input.toCharArray();
		reverseHelper(array, 0, array.length - 1);
		return new String(array);
	}
	private void reverseHelper(char[] array, int left, int right) {
		if(left >= right) return;
		SWAP.charSwap(array, left, right);
		reverseHelper(array, left+1, right-1);
	}

	public String reverseWords(String s) {
		Deque<String> set = new LinkedList<String>();
		int start = 0;
		boolean prevIsChar = false;
		for(int i = 0; i < s.length(); i++) {
			if(!prevIsChar && s.charAt(i) != ' ') {
				prevIsChar = true;
				start = i;
			}
			if(prevIsChar && s.charAt(i) == ' ') {
				prevIsChar = false;
				set.add(s.substring(start, i));
			}
		}
		if(prevIsChar && s.charAt(s.length()-1) != ' ') {
			set.add(s.substring(start));
		}
		StringBuilder rev = new StringBuilder();
		while(!set.isEmpty()) {
			rev.append(set.pollLast());
			if(!set.isEmpty()) rev.append(" ");
		}
		return rev.toString();
	}
	
	// Method 2: reverse words in sentence 
	// step 1: the words are separated by one space character
	// step 2: There are no leading or trailing spaces
	// step 3: input is not null
	public String reverseWordsII(String input) {
		// try to convert it to char array and to solve the problem
		char[] array = input.toCharArray();
		reverse(array, 0, array.length - 1);
		int start = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] != ' ' && (i == 0 || array[i - 1] == ' ')) {
				start = i;
			}
			if(array[i] != ' ' && (i == array.length - 1 || array[i+1] == ' ')) {
				reverse(array, start, i);
			}
		}
		return new String(array);
	}
	private void reverse(char[] array, int left, int right) {
		while(left < right) {
			char temp = array[left];
			array[left++] = array[right];
			array[right++] = temp;
		}
	}
	
	// Follow Up 3: Right shift By N characters
	// Assume that the input is not null and n >= 0
	public String rightShift(String input, int n) {
		if(input.length() <= 1) return input;
		char[] array = input.toCharArray();
		n %= input.length();
		reverse(array, array.length - n, array.length - 1);
		reverse(array, 0, array.length - n - 1);
		reverse(array, 0, array.length);
		return new String(array);
	}
}
