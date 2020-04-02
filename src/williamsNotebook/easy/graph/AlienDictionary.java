/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月1日 下午12:58:49
* Description: Leet Code 269
* 	There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
* 	You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules 
* 	of this new language. Derive the order of letters in this language.
* Example :
* 	Input:
* 	[
	  "wrt",
	  "wrf",
	  "er",
	  "ett",
	  "rftt"
	]
	Output: "wertf"
*/
public class AlienDictionary {
	
	// Topological Sorting - BFS
	public String alienOrdr(String[] words) {
		String result = "";
		if(words == null || words.length == 0) {
			return result;
		}
		HashMap<Character, HashSet<Character>> adj = new HashMap<Character, HashSet<Character>>();
		HashMap<Character, Integer> degree = new HashMap<Character, Integer>();
		// Initiate degree for characters in the alien dict
		for(String word:words) {
			for(char c : word.toCharArray()) {
				degree.put(c, 0);
			}
		}
		// Build graph with adjacency list; update degree
		for(int i = 0; i < words.length - 1; i++) {
			int j = 0;
			while(j < words[i].length() && j < words[i+1].length()) {
				char a = words[i].charAt(j);
				char b = words[i+1].charAt(j);
				if(a != b) {
					HashSet<Character> set = new HashSet<Character>();
					if(adj.containsKey(a)) {
						set = adj.get(a);
					}
					if(!set.contains(b)) {
						set.add(b);
						adj.put(a, set);
						degree.put(b, degree.get(b) + 1);
					}
					break;
				}
				j++;
			}
		}
		// Initiate queue for topological sorting
		Queue<Character> queue = new LinkedList<Character>();
		for(Character c : degree.keySet()) {
			if(degree.get(c) == 0) {
				queue.offer(c);
			}
		}
		// Topological Sorting
		while(!queue.isEmpty()) {
			char ch = queue.poll();
			result += ch;
			if(adj.containsKey(ch)) {
				for(char c : adj.get(ch)) {
					degree.put(c, degree.get(c) - 1);
					if(degree.get(c) == 0) queue.offer(c);
				}
			}
		}
		// No valid topological sorting
		if(result.length() != degree.size()) {
			return "";
		}
		return result;
	}
	// Topological DFS
	// visited[i] = -1 Visited but not exist, visited[i] = 0 Exist. Non-visited, visited[i] = 1 Visiting, visited[i] 2 Visited
	// Assumption: all input char is in lower case
	private final int N = 26;
	public String alienOrder(String[] words) {
		boolean[][] adj = new boolean[N][N];
		int[] visited = new int[N];
		buildGraph(words, adj, visited);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			if(visited[i] == 0) {
				// unvisited
				if(!dfs(adj,visited, sb, i)) return "";
			}
		}
		return sb.reverse().toString();
	}
	public void buildGraph(String[] words, boolean[][] adj, int[] visited) {
		Arrays.fill(visited, -1); // -1 = not even existed
		for(int i = 0; i < words.length; i++) {
			for(char c : words[i].toCharArray()) visited[c - 'a'] = 0;
			if(i > 0) {
				String w1 = words[i - 1], w2 = words[i];
				int len = Math.min(w1.length(), w2.length());
				for(int j = 0; j < len; j++) {
					char c1 = w1.charAt(j), c2 = w2.charAt(j);
					if(c1 != c2) {
						adj[c1 - 'a'][c2 - 'a'] = true;
						break;
					}
				}
			}
		}
	}
	public boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
		visited[i] = 1;
		for(int j = 0; j < N; j++) {
			if(adj[i][j]) {  // 0 - false, 1 - true
				if(visited[j] == 1) return false; // 1 => 1, cycle 
				if(visited[j] == 0) { // 0 = unvisited;
					if(!dfs(adj, visited, sb, j)) return false;
				}
			}
		}
		visited[i] = 2; // mark as visited 
		sb.append((char)(i + 'a'));
		return true;
	}
}
