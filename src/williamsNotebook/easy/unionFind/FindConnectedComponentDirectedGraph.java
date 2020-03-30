/**
 * 
 */
package williamsNotebook.easy.unionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import williamsNotebook.common.unionFind.UnionFind;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月27日 上午11:32:27
* Description: 
* 	Find the number Weak Connected Component in the directed graph. Each node in the graph contains a label and a list of its neighbors. 
* 	(a connected set of a directed graph is a subgraph in which any two vertices are connected by direct edge path.)
* 	Each connected component should sort by label
* 	A----->B  C
	 \     |  |
	  \    |  |
	   \   |  |
	    \  v  v
	     ->D  E <- F
	Return {A,B,D}, {C,E,F}. Since there are two connected component which are {A,B,D} and {C,E,F}
*/

public class FindConnectedComponentDirectedGraph {
	
	 class DirectedGraphNode {
	      int label;
	      ArrayList<DirectedGraphNode> neighbors;
	      DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
	 };

	// Method 1: Regular BFS
	
	// Method 2: Union Finds
	private List<List<Integer>> print(HashSet<Integer> set, UnionFind uf, int n){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for(int i : set) {
			int father = uf.find(i);
			if(!map.containsKey(father)) {
				map.put(father, new ArrayList<Integer>());
			}
			List<Integer> cur = map.get(father);
			cur.add(i);
			// update the result list
			map.put(father, cur);
		}
		for(List<Integer> list : map.values()) {
			Collections.sort(list);
			res.add(list);
		}
		return res;
	}
	public List<List<Integer>> connectSet(ArrayList<DirectedGraphNode> nodes){
		HashSet<Integer> set = new HashSet<Integer>();
		for(DirectedGraphNode cur : nodes) {
			set.add(cur.label);
			for(DirectedGraphNode neighbor : cur.neighbors) {
				set.add(neighbor.label);
			}
		}
		UnionFind uf = new UnionFind(set);
		for(DirectedGraphNode cur : nodes) {
			for(DirectedGraphNode neighbor : cur.neighbors) {
				int curFather = uf.find(cur.label);
				int neiFather = uf.find(neighbor.label);
				if(curFather != neiFather) {
					uf.union(cur.label, neighbor.label);
				}
			}
		}
		return print(set, uf, nodes.size());
	}
}
