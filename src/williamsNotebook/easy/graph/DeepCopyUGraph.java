/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import williamsNotebook.common.node.GraphNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��23�� ����11:10:09
* Description:
* 	Make a deep copy of an undirected graph, there could be cycles in the original graph.
* Assumption:
* 	The given graph is not null
*/

public class DeepCopyUGraph {
	public List<GraphNode> copy(List<GraphNode> graph){
		if(graph == null) {
			return null;
		}
//		key: original graph, value: copied graph 
		HashMap<GraphNode, GraphNode> map = new HashMap<GraphNode, GraphNode>();
		for(GraphNode node : graph) {
//			copy the graphnode one by one
			if(!map.containsKey(node)) {
				map.put(node, new GraphNode(node.key));
//				copy the graphNode's neighbors
				DFS(node, map);
			}
		}
		return new ArrayList<GraphNode>(map.values());
	}
	private void DFS(GraphNode seed, HashMap<GraphNode, GraphNode> map) {
		GraphNode copy = map.get(seed);
		for(GraphNode nei:seed.neighbours) {
//			cut the branches
			if(!map.containsKey(nei)) {
				map.put(nei, new GraphNode(nei.key));
				DFS(nei,map);
			}
			copy.neighbours.add(map.get(nei));
		}
	}
}
