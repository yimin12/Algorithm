/**
 * 
 */
package williamsNotebook.easy.trieTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yimin Huang
 *	
 *		Given a 2D board and a word, find if the word exists in the grid.
 *		The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells
 *		are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *	Given board =			word = "ABCCED", -> returns true,	word = "SEE", -> returns true,
		[
		  ["ABCE"],
		  ["SFCS"],
		  ["ADEE"]
		]
 * Algorithm Class
 */
public class WordSearch {

	// DFS to traverse all the situations
	// k is the length of pattern, n is the number of chars in the board
	// Time: O(m*n*4^k)  Extra space: O(m*n)
	public boolean searchWord(char[][] board, String pattern) {
		// Assumption
		if(board == null || board.length == 0 || board[0].length == 0) throw new IllegalArgumentException("invalid input board");
		if(pattern == null || pattern.length() == 0) return true;
		int m = board.length, n = board[0].length;
		boolean[][] visisted = new boolean[m][n];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(dfs(board, visisted, i, j, 0, pattern)) {
					return true;
				}
			}
		}
		return false;
	}
	private boolean dfs(char[][] board, boolean[][] visited, int x, int y, int index, String pattern) {
		// base case: if the search is finish
		if(index == pattern.length()) {
			return true;
		}
		// if the coordinate is out of boundary, or the char does not match or it has been visited before
		if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != pattern.charAt(index) || visited[x][y]) return false;
		visited[x][y] = true;
		// recursion rules
		boolean match = dfs(board, visited, x + 1, y, index + 1, pattern) || dfs(board, visited, x - 1, y, index + 1, pattern) || dfs(board, visited, x, y + 1, index + 1, pattern)
				|| dfs(board, visited, x, y - 1, index + 1, pattern);
		// handle the back tracking
		visited[x][y] = false;
		return match;
	}
	
	// Follow Up : Given a 2D board and a list of words from the dictionary, find all words in the board.
	// Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally 
	// or vertically neighboring. The same letter cell may not be used more than once in a word.
	// For example: Given words = ["oath","pea","eat","rain"] and board = [['o','a','a','n'],['e','t','a','e'],['i','h','k','r'],['i','f','l','v']]
	// Return ["eat","oath"].
	// You may assume that all inputs are consist of lowercase letters a-z.
	// Use data structure Trie to solve the problem
	private static final int R = 26; // assume all the given value is lower case
	private Node root;
	private static class Node{
		private String val;
		private Node[] next = new Node[R];
	}
	// insert key-value pair into trie
	private void insert(String key, String val) {
	    root = put(root, key, val, 0);
	}
	private Node put(Node x, String key, String val, int d) {
	    Node xCopy = x;
	    if (xCopy == null)  xCopy = new Node();
	    if (d == key.length()) {
	        xCopy.val = val;
	        return xCopy;
	    }
	    int c = key.charAt(d) - 'a';
	    xCopy.next[c] = put(xCopy.next[c], key, val, d + 1);
	    return xCopy;
	}
	// Time: Worst case : O(m*n*4^(26*t)) Average case : O(m*n*4^(t)),  Extra space: O(k * t) worst case , k is average length of word, t is the number of words
	public List<String> findWords(char[][] board, String[] words){
		List<String> list = new ArrayList<String>();
		if(board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) return list;
		int M = board.length, N = board[0].length;
		int dictSize = words.length;
		// add the word in words to Trie Tree
		for(int i = 0; i < dictSize; i++) {
			String word = words[i];
			insert(word, word);
		}
		// use DFS to search all valid words
		boolean[][] visited = new boolean[M][N];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				char letter = board[i][j];
				int c = letter - 'a';
				helper(board, visited, i, j, root.next[c], list);
			}
		}
		return list;
	}
	private void helper(char[][] board, boolean[][] visited, int i, int j, Node x, List<String> list) {
		if(x == null) return;
		if(x.val != null && !list.contains(x.val)) {
			list.add(x.val);
		}
		visited[i][j] = true;
		if(i > 0 && !visited[i-1][j])  helper(board, visited, i - 1, j, x.next[board[i - 1][j] - 'a'], list);
		if (i < board.length - 1 && !visited[i + 1][j]) helper(board, visited, i + 1, j, x.next[board[i + 1][j] - 'a'], list);
		if (j > 0 && !visited[i][j - 1]) helper(board, visited, i, j - 1, x.next[board[i][j - 1] - 'a'], list);
		if (j < board[0].length - 1 && !visited[i][j + 1])  helper(board, visited, i, j + 1, x.next[board[i][j + 1] - 'a'], list);  
		visited[i][j] = false;
	}
	public static void main(String[] args) {
		WordSearch solution = new WordSearch();
		char[][] board = new char[][] {{'a','b','c','e'},{'s','f','c','s'},{'a','d','e','e'},{'k','f','c','s'}};
		boolean searchWord = solution.searchWord(board, null);
		System.out.println(searchWord);
	}
}
// Version 2 of Follow Up 2
class WordSearchII{
	class TrieNode{
		TrieNode[] children = new TrieNode[26];
		String word; // This field should classfied for your own requirement
		TrieNode get(char ch) {
			return children[ch - 'a'];
		} 
		void put(char ch, TrieNode node) {
			children[ch - 'a'] = node;
		}
		void setWord(String s) {
			word = s;
		}
		String getWord() {
			return this.word;
		}
	}
	private TrieNode buildTrie(String[] words) {
		TrieNode root = new TrieNode();
		for(String word:words) {
			TrieNode node = root;
			char[] charArray = word.toCharArray();
			for(char ch : charArray) {
				if(node.get(ch) == null) {
					node.put(ch, node);
				}
				node = node.get(ch);
			}
			node.setWord(word);
		}
		return root;
	}
	public List<String> findWords(char[][] board, String[] words){
		TrieNode root = buildTrie(words);
		System.out.println(root.word);
		List<String> res = new ArrayList<String>();
		int M = board.length;
		int N = board[0].length;
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				dfs(board, i, j, root, res);
			}
		}
		return res;
	}
	// two version of dfs
	public void dfs(char[][] board, int i, int j, TrieNode node, List<String> res) {
		// base case:
		if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || node == null || board[i][j] == '#') {
			return;
		}
		char ch = board[i][j];
		node = node.get(ch);
		if(node != null && node.getWord() != null && !res.contains(node.getWord())) {
			res.add(node.getWord());
		}
		int[] dx = new int[]{0,1,0,-1};
		int[] dy = new int[]{1,0,-1,0};
		// place marker
		board[i][j] = '#';
		for(int k = 0; i < 4; i++) {
			int nextX = i + dx[k];
			int nextY = j + dy[k];
			if(nextX >= 0 && nextY >= 0 && nextX < board.length && nextY < board[0].length && board[nextX][nextY] != '#') {
				dfs(board, nextX, nextY, node, res);
			}
		}
		// deal the back tracking problem
		board[i][j] = ch;
	}
}
