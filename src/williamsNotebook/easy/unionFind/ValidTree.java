/**
 * 
 */
package williamsNotebook.easy.unionFind;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import williamsNotebook.common.unionFind.UnionFind;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月27日 上午11:55:06
* Description:
* 	Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
* 	You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
* Example:
* 	Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
* 	Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
* Depth First Search Facebook Zenefits Union Find Breadth First Search Google
* 
*/
public class ValidTree {

	// Method 1: DFS Solution and convert matrix to adjacent list
	// Let E be the number of edges and N be the number of nodes
	// Time : O(N + E) Creating the adjacency list requires initialising a list of length NN, with a cost of O(N)O(N), and then iterating over and inserting EE edges, for a cost of O(E)O(E). This gives us O(E) + O(N) = O(N + E)O(E)+O(N)=O(N+E)
	// Each node is added to the data structure once. This means that the outer loop will run NN times. For each of the NN nodes, its adjacent edges is iterated over once. In total, this means that all EE edges are iterated over once by the inner loop. This, therefore, gives a total time complexity of O(N + E)O(N+E)
	// Space Complexity : O(N + E)O(N+E).
	public boolean validTreeDFSI(int n, int[][] edges) {
		// initialize adjacency list
		List<List<Integer>> adjList = new ArrayList<List<Integer>>();
		// initialize vertices
		for(int i = 0; i < n; i++) {
			adjList.add(i, new ArrayList<Integer>());
		}
		// add edge with undirected vertices
		for(int i = 0; i < edges.length; i++) {
			int u = edges[i][0], v = edges[i][1];
			adjList.get(u).add(v);
			adjList.get(v).add(u);
		}
		boolean[] visited = new boolean[n];
		if(hasCycle(adjList, 0, visited, -1)) return false;
		for(int i = 0; i < n; i++) {
			// if there isolated island
			if(!visited[i]) {
				return false;
			}
		}
		return true;
	}
	// two ways to use dfs find cycle
	private boolean hasCycle(List<List<Integer>> adjList, int u, boolean[] visited, int parent) {
		visited[u] = true;
		for(int i = 0; i < adjList.get(u).size(); i++) {
			int v = adjList.get(u).get(i);
			if((visited[v] && parent != v) || (!visited[v] && hasCycle(adjList, v, visited, u))){
				return true;
			}
		}
		return false;
	}
	private boolean hasCycleII(List<List<Integer>> adjList, int u, boolean[] visited, int parent) {
		// base case
		if(visited[u]) {
			return true;
		}
		for(int v : adjList.get(u)) {
			if(v != parent && hasCycleII(adjList, v, visited, u)) {
				return true;
			}
		}
		return false;
	}
	
	// Method 2: BFS 
	public boolean validTreeBFS(int n, int[][] edges) {
		// method of converting matrix to adjacent list
		List<Set<Integer>> graph = new ArrayList<Set<Integer>>();
		for(int i = 0; i < n; i++) graph.add(new HashSet<Integer>());
		for(int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		boolean[] visited = new boolean[n];
		Deque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(0);
		while(!queue.isEmpty()) {
			int node = queue.poll();
			// cycle detected
			if(visited[node]) {
				return false;
			}
			for(int neighbor : graph.get(node)) {
				queue.offer(neighbor);
				graph.get(neighbor).remove((Integer)node);
			}
		}
		// check whether it contains isolated island
		for(boolean result : visited) {
			if(!result) {
				return false;
			}
		}
		return true;
	}
	// Method 3: Union Find
	public boolean validTreeUnionFind(int n, int[][] edges) {
		if(edges.length != n-1) return false;
		int[] parent = new int[n];
		Arrays.fill(parent, -1);
		
		for(int[] edge : edges) {
			int x = find(edge[0],parent);
			int y = find(edge[1], parent);
			if(x == y) return false;
			parent[x] = y;
		}
		return true;
	}
	private int find(int node, int[] parent) {
		if(parent[node] == -1) return node;
		return parent[node] = find(parent[0], parent);
	}
}
