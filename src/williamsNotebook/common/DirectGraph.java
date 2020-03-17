/**
 * 
 */
package williamsNotebook.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yimin Huang
 *
 * Algorithm Class
 */
public class DirectGraph {
	public int V; // number of vertices
	public int E; // number of edges
	public List<Integer>[] adj; // adjacency list
	public DirectGraph(int n, int[][] edges) {
		this.V = n;
		this.E = edges.length;
		adj = (List<Integer>[])new List[V];
		for(int i = 0; i < V; i++) {
			adj[i] =  new ArrayList<Integer>();
		}
		for(int i = 0; i < E; i++) {
			int v = edges[i][1];
			int w = edges[i][0];
			adj[v].add(w);
		}
	}
}
