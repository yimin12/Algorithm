/**
 * 
 */
package williamsNotebook.easy.slidingwindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yimin Huang
 *
 *	Given a string, find the length of the longest substring T that contains at most 2 distinct characters
 *	For example, Given s = “eceba�?,
 *	T is "ece" which its length is 3.
 *
 * Algorithm Class
 */
public class LongestSubString {

	// Follow Up 1: at most 2 distinct characters
	// Sliding window by using three pointers
	public int lengthOfTwoDistinct(String s) {
		if(s == null || s.length() == 0) return 0;
		int n = s.length();
		int start = 0, last = -1, max = 0;
		for(int cur = 1; cur < n; cur++) {
			if(s.charAt(cur) != s.charAt(cur-1)) {
				if(last > 0 && s.charAt(cur) != s.charAt(last)) {
					max = Math.max(max, cur - start);
					start = last + 1;
				}
			}
		}
		return Math.max(max,  n - start);
	}
	
	// Follow Up 2, k distinct character.
	// Two Pointers and HashTable Time: O(2N) and Extra Space O:(1)
	public int lengthOfKDistinctSubString(String s, int k) {
		int[] count = new int[256];
		int start = 0, numDistinct = 0, maxLen = 0;
		for(int i = 0; i < s.length(); i++) {
			if(count[s.charAt(i)] == 0) numDistinct++;
			count[s.charAt(i)]++;
			while(numDistinct > k) {
				count[s.charAt(i)]--;
				if(count[s.charAt(start)] == 0) numDistinct--;
				start++;
			}
			maxLen = Math.max(i - start + 1, maxLen);
		}
		return maxLen;
	}
	
	// Follow Up 3: All Distinct without repeating
	// Method 1: Two pointer and HashTable , Time: O(2N) and Extra Space: O(1)
	public int lengthOfLongest(String s) {
		boolean[] exist= new boolean[256];
		int start = 0, maxLen = 0;
		for(int i = 0; i < s.length(); i++) {
			while(exist[s.charAt(i)]) {
				exist[s.charAt(start++)] = false;
			}
			exist[s.charAt(i)] = true;
			maxLen = Math.max(i-start+1, maxLen);
		}
		return maxLen;
	}
	// Method 2: 
	public int lengthOfLongestII(String s) {
		int[] charMap = new int[256];
		Arrays.fill(charMap, -1);
		int start = 0, maxLen = 0;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(charMap[c] >= start) start = charMap[c]+1;
			charMap[c] = i;
			maxLen = Math.max(maxLen, i - start + 1);
		}
		return maxLen;
	}
	// Follow Up 4:
	public int longestSubstring(String s, int k) {
        // write your code here
        if(s == null || s.length() < k) {
            return 0;
        }
        
        int[] cntS = new int[26];
        
        char[] ca = s.toCharArray();
        int len = s.length();
        
        for(int i = 0; i < len; i++) {
            cntS[ca[i] - 'a']++;
        }
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < 26; i++) {
            if(cntS[i] < k && cntS[i] > 0) {
                set.add((char)('a' + i));
            }
        }
        if(set.size() ==0) {
            return s.length();
        }
        int l = 0; 
        int r = 0;
        
        int maxLen = 0;
        while(l < len && r < len) {
            while(r < len && !set.contains(ca[r])) {
                r++;
            }
            
            maxLen = Math.max(maxLen, longestSubstring(s.substring(l, r), k));
            if(r == len) {
                break;
            }
            r++;
            l = r;
        }
        
        return maxLen;
    }
}
