/**
 * 
 */
package williamsNotebook.easy.dynamicP.laioffer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��24�� ����9:14:30
* Description:
* 	Given a dictionary containing many words, find the largest product of 
* 	two words�� lengths, such that the two words do not share any common characters.
* Assumption:
* 	The words only contains character of 'a' to 'z'
* 	The dictionary is not null and does not contains null string, and has at least two strings
* 	If there is no such pair of words, just return 0
* Examples:
* 	dictionary = [��abcde��, ��abcd��, ��ade��, ��xy��], the largest product is 5 * 2 = 10 
* 	(by choosing ��abcde�� and ��xy��)
*/

public class LargestLengthProduct {
	public int largestProduct(String[] dict) {
//		Assumption: dict is not null and has length >= 2
//		Get the bit mask for each of the word in the dictionary, the bit mask is represented by the lowest
//		26 bits of an integer. each of the bit represents one of the characters in 'a' - 'z'
		HashMap<String, Integer> bitMask = getBitMasks(dict);
//		Sort the dictionary by length of the words in descending order.
		Arrays.sort(dict, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if(s1.length() == s2.length()) {
					return 0;
				}
				return s1.length() < s2.length() ? -1: 1;
			}
		});
		int largest = 0;
//		the order of constructing all the pairs, we make our best to try largest product
		for(int i = 1; i < dict.length; i++) {
			for(int j = 0; j < i; j++) {
				int prod = dict[i].length() * dict[j].length();
//				we only update the value that is larger than largest, kind of cutting branches
				if(prod <= largest) {
					break;
				}
				int iMask = bitMask.get(dict[i]);
				int jMask = bitMask.get(dict[j]);
//				if two words do not share any common characters, the bit masks "and" result should be 0
//				since there is not any position such that in the two bit masks there are all 1
				if((iMask&jMask) == 0) {
					largest = prod;
				}
			}
		}
		return largest;
	}
//	Get the bit mask for each of the words
	private HashMap<String, Integer> getBitMasks(String[] dict){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(String str:dict) {
			int bitMask = 0;
			for(int i=0; i < str.length();i++) {
//			the 26 characters 'a' - 'z' are mapped to 0 - 25th bit to determine which bit it is for character x
//			use (x-'a') since their values are in a consecutive range. if character x exists in the word
//			we set the bit at corresponding index to 1
				bitMask |= 1 << (str.charAt(i) - 'a');
			}
			map.put(str, bitMask);
		}
		return map;
	}
	
}
