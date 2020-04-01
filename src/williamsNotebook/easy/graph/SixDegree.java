/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import williamsNotebook.common.node.UnDirectedGraphNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月31日 上午11:16:44
* Description:
* 	Six degrees of separation is the theory that everyone and everything is six or fewer steps away, by way of introduction,
*   from any other person in the world, so that a chain of "a friend of a friend" statements can be made to connect any 
*   two people in a maximum of six steps.
*   Given a friendship relations, find the degrees of two people, return -1 if they can not been connected by friends of friends
*   1------2-----4
	 \          /
	  \        /
	   \--3--/
	The given input would be like {1,2,3#2,1,4#3,1,4#4,2,3} representing the connection status ,and start = 1, to = 4 return 2
	return the min degree's distance between two people
*/

public class SixDegree {

	// Mthod 1: USE BFS with Dijkstra's algorithm 
	// Assumption: There is not circle
	public int sixDegrees(List<UnDirectedGraphNode> graph, UnDirectedGraphNode from, UnDirectedGraphNode to) {
		if(from == to) return 0;
		// key: The specific node, value: min distance from start
		Map<UnDirectedGraphNode, Integer> visited = new HashMap<UnDirectedGraphNode, Integer>();
		Queue<UnDirectedGraphNode> queue = new LinkedList<UnDirectedGraphNode>();
		
		queue.offer(from);
		visited.put(from, 0);
		
		// KeyInsight: Use size to restrict the current level is the minimum distance 
		// Each edge is equal, if it were weighted, should calculate the minimum
		while(!queue.isEmpty()) {
			UnDirectedGraphNode frontier = queue.poll();
			int size = frontier.neighbors.size();
			for(int i = 0; i < size; i++) {
				UnDirectedGraphNode node = frontier.neighbors.get(i);
				if(visited.containsKey(node)) {
					continue;
				}
				if(node == to) {
					return visited.get(frontier) + 1;
				}
				queue.offer(node);
				visited.put(node, visited.get(frontier) + 1);
			}
		}
		return -1;
	}
}
