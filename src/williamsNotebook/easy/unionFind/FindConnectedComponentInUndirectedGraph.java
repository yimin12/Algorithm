/**
 * 
 */
package williamsNotebook.easy.unionFind;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import williamsNotebook.common.UnionFind;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月27日 上午10:44:41
* Description:
* 	Find the number connected component in the undirected graph. Each node in the graph contains a label and a list of its neighbors. 
*   (a connected component (or just component) of an undirected graph is a subgraph in which any two vertices are connected to each other by paths, 
*    and which is connected to no additional vertices in the supergraph.)
*    Each connected component should sort by label.
*   Learn more about representation of graphs 
*   A------B  C
	 \     |  |
	  \    |  |
	   \   |  |
	    \  |  |
	      D   E
	Return {A,B,D}, {C,E}. Since there are two connected component which is {A,B,D}, {C,E}
*/
public class FindConnectedComponentInUndirectedGraph {

	// representation of graph, and the label is int
	static class UndirectedGraph{
		int label;
		ArrayList<UndirectedGraph> neighbors;
		public UndirectedGraph(int label) {
			this.label = label;
			this.neighbors = new ArrayList<UndirectedGraph>();
		}
	}
	private List<List<Integer>> print(HashSet<Integer> hashSet, UnionFind uf, int n){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		HashMap<Integer, List<Integer>> hashMap = new HashMap<Integer, List<Integer>>();
		// Step 1: find the groups by Union find
		// Step 2: add the numbers to gourp's list
		for(Integer i : hashSet) {
			int father = uf.find(i);
			if(!hashMap.containsKey(father)) {
				hashMap.put(father, new ArrayList<Integer>());
			}
			List<Integer> now = hashMap.get(father);
			now.add(i);
			hashMap.put(father, now);
		}
		// return the value by required sorting
		for(List<Integer> now : hashMap.values()) {
			Collections.sort(now);
			res.add(now);
		}
		return res;
	}
	public List<List<Integer>> connectedComponent(ArrayList<UndirectedGraph> nodes){
		HashSet<Integer> set = new HashSet<Integer>();
		for(UndirectedGraph node : nodes) {
			set.add(node.label);
			for(UndirectedGraph neighbor : node.neighbors) {
				set.add(neighbor.label);
			}
		}
		UnionFind uf = new UnionFind(set);
		for(UndirectedGraph node:nodes) {
			for(UndirectedGraph neighbor : node.neighbors) {
				int curFather = uf.find(node.label);
				int neiFather = uf.find(neighbor.label);
				if(curFather != neiFather) {
					uf.union(neiFather, curFather);
				}
			}
		}
		return print(set, uf, nodes.size());
	}
}
