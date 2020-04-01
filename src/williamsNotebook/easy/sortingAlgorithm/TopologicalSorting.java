/**
 * 
 */
package williamsNotebook.easy.sortingAlgorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月31日 下午11:53:05
* Description:
*/
public class TopologicalSorting {
	
	Graph graph;
	private int V;
	
	void topologicalSort(Graph graph) {
		this.graph = graph;
		this.V = graph.getV();
		Deque stack = new ArrayDeque<>();
		boolean[] visited = new boolean[V];
		for(int i = 0; i < V; i++) {
			visited[i] = false;
		}
		for(int i = 0; i < V; i++) {
			if(visited[i] == false) {
				graph.topologicalSortUtil(i, visited, stack);
			}
		}
		while(stack.isEmpty() == false) {
			System.out.println(stack.pop() + " ");
		}
	}
	 public static void main(String args[]) 
	    { 
	        // Create a graph given in the above diagram 
	        Graph g = new Graph(6); 
	        g.addEdge(5, 2); 
	        g.addEdge(5, 0); 
	        g.addEdge(4, 0); 
	        g.addEdge(4, 1); 
	        g.addEdge(2, 3); 
	        g.addEdge(3, 1); 
	        TopologicalSorting solution = new TopologicalSorting();
	        solution.topologicalSort(g);
	        System.out.println("Following is a Topological " + 
	                        "sort of the given graph"); 
	       
	    } 
}
class Graph{
	
	private int V; // No. of vertices
	private LinkedList<Integer> adj[]; // Adjacency List
	
	// Constructor
	Graph(int v){
		V = v;
		adj = new LinkedList[v];
		for(int i = 0; i < v; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}
	// Add an edge to the graph
	void addEdge(int v, int w) {adj[v].add(w);}
	// A recursive function used by topologicalSort
	void topologicalSortUtil(int v, boolean[] visited, Deque stack) {
		// Mark the current node
		visited[v] = true;
		Integer i;
		Iterator<Integer> iterator = adj[v].iterator();
		while(iterator.hasNext()) {
			i = iterator.next();
			if(!visited[i]) {
				topologicalSortUtil(i, visited, stack);
			}
		}
		// push current vertex to stack which stores result
		stack.push(v);
	}
	public int getV() {
		return this.V;
	}
	
}
