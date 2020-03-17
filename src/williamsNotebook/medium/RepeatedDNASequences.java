/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yimin Huang
 *	
 *		All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for 
 *		example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 *		Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule
 *		For example, Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 *			Return:  ["AAAAACCCCC", "CCCCCAAAAA"].
 *
 * Algorithm Class
 */
public class RepeatedDNASequences {

	// Method 1: Suffix sort Time ~ O(NlogN), Space ~ O(N^2) 
	public List<String> findRepeatedDnaSequences(String s){
		List<String> list = new ArrayList<String>();
		if(s.length() < 10) return list;
		int len = 10, n = s.length() - (len - 1);
		String[] suffix = new String[n];
		for(int i = 0; i < n; i++) {
			suffix[i] = s.substring(i);
		}
		Arrays.sort(suffix);
		for(int i = 0; i < n - 1; i++) {
			String curr = suffix[i].substring(0, len);
			String next = suffix[i+1].substring(0,len);
			if(curr.equals(next)) list.add(curr);	
		}
		return list;
	}
	// Method 2: Hash Table and Bit Manipulation
	// Time ~ O(N), Space(2N) can be optimized to O(N) by using HashMap
	public List<String> findRepeatedDnaSequencesII(String s){
		List<String> list = new ArrayList<String>();
		Set<Integer> firstVisted = new HashSet<Integer>();
		Set<Integer> secondVisited = new HashSet<Integer>();
		char[] map = new char[26];
		// for bit, there are three possible situations. 00, 01, 10, 11, so we need to left shift 2 digits every time
		map['A' - 'A'] = 0;
		map['C' - 'A'] = 1;
		map['G' - 'A'] = 2;
		map['T' - 'A'] = 3;
		int len = 10, v = 0;
		for(int i = 0; i < s.length(); i++) {
			// cover all possibilities
			v <<= 2;
			v |= map[s.charAt(i) - 'A']; // add new two bits when a new char coming in
			if(i == len - 1) firstVisted.add(v);
			else if(i > len - 1) {
				// drop 2 bits at the highest place ( the 21st digit and the 22nd digit) because it is useless.
				v &= ~(3 << 2*len);
				if(!firstVisted.add(v) && secondVisited.add(v)) {
					list.add(s.substring(i - len + 1, i + 1));
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		RepeatedDNASequences solution = new RepeatedDNASequences();
		List<String> findRepeatedDnaSequences = solution.findRepeatedDnaSequencesII("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
		System.out.println(findRepeatedDnaSequences.toString());
	}
}
