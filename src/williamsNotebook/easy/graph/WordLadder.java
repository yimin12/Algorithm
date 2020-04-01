package williamsNotebook.easy.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author yimin Huang
 *
 *	Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that
 *	Rules:	
 *		Only one letter can be changed at a time
 *		Each intermediate word must exist in the dictionary
 *	For example,
 *		start = "hit"   end = "cog"		dict = ["hot","dot","dog","lot","log"]
 *		As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *	Return 0 if there is no such transformation sequence.
 *	All words have the same length.
 *	All words contain only lowercase alphabetic characters.
 *
 * Algorithm Class
 */
public class WordLadder {

	// Follow Up 1: Minimum Steps of Transformation Following the dictionary
	// BFS: Time ~ O(26MN) where M is the min steps and N is the word length, Space ~ O(26MN)
	public int ladderLength(String start, String end, Set<String> dict) {
		// Assume that the given dict and start and end are all not null, and the elements of dict are valid
		Set<String> map = new HashSet<String>(); // record visited words in dict so as not to modify dict
		Queue<String> word = new LinkedList<String>();
		Queue<Integer> depth = new LinkedList<Integer>();
		word.add(start);
		depth.add(1);
		// using bfs
		while(!word.isEmpty()) {
			String curWord = word.poll();
			int currDepth = depth.poll();
			// return the depth if a match is found
			if(curWord.equals(end)) return currDepth;
			// change each letter of current Word
			for(int i = 0; i < curWord.length(); i++) {
				char[] curWordArray = curWord.toCharArray();
				// try every possible char and check if there's a match in dict
				for(char c = 'a'; c <= 'z'; c++) {
					curWordArray[i] = c;
					String newWord = new String(curWordArray);
					if(dict.contains(newWord) && !map.contains(newWord)) {
						word.add(newWord);
						depth.add(currDepth + 1);
						map.add(newWord);
					}
				}
			}
		}
		return 0; // no match is found in dict
	}
	
	// Follow Up 2: Return All Shortest Transformation
	// Input is the same as the last question, but return the process detail
	// Return [["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"]]
	public List<List<String>> findLadders(String start, String end, Set<String> dict){
		Queue<String> word = new LinkedList<String>();
		Map<String, Integer> map = new HashMap<String, Integer>(); // hashmap<word, depth>
		word.add(start);
		map.put(start, 1);
		// BFS: find all the paths of transformation and store the depths in a hashmap
		int minDepth = Integer.MAX_VALUE;
		while(!word.isEmpty()) {
			String curWord = word.poll();
			int curDepth = map.get(curWord);
			if(curDepth >= minDepth) continue; // pruning because it no longer can be the minimum step
			if(curWord.equals(end)) {
				minDepth = Math.min(minDepth, curDepth);
				continue;
			}
			for(int i = 0; i < curWord.length(); i++) {
				char[] curWordArray = curWord.toCharArray();
				for(char c = 'a'; c <= 'z'; c++) {
					curWordArray[i] = c;
					String newWord = new String(curWordArray);
					if(dict.contains(newWord) && !map.containsKey(newWord)) {
						word.add(newWord);
						map.put(newWord, curDepth + 1);
					}
				}
			}
		}
		// Step 2: DFS, Using back tracking from the start to put all the paths into list
		List<List<String>> pathSet = new ArrayList<List<String>>();
	    List<String> path = new ArrayList<>();        
	    path.add(start);
	    dfs(start, end, map, path, pathSet);
	    return pathSet;
	}
	private void dfs(String start, String end, Map<String, Integer> map, List<String> path, List<List<String>> pathSet) {
	    if (start.equals(end)) {
	        pathSet.add(new ArrayList<String>(path));   // need to initialize a new ArrayList!!
	        return;
	    }
	    int currDepth = map.get(start);
	    for (int i = 0; i < start.length(); i++) {
	        char[] currWordArr = start.toCharArray();
	        for (char c = 'a'; c <= 'z'; c++) {
	            currWordArr[i] = c;
	            String newWord = new String(currWordArr);
	            if (map.containsKey(newWord) && map.get(newWord) == currDepth + 1) {
	                int size = path.size();
	                path.add(newWord);
	                dfs(newWord, end, map, path, pathSet);
	                path.remove(size);
	            }
	        }
	    }
	}
	
	public static void main(String[] args) {
		WordLadder solution = new WordLadder();
		Set<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("cog");
		dict.add("log");

		int ladderLength = solution.ladderLength("hit", "log", dict);
		System.out.println(ladderLength);
		
		List<List<String>> findLadders = solution.findLadders("hit", "cog", dict);
		for(List<String> list : findLadders) {
			System.out.println(list.toString());
		}
	}
}
