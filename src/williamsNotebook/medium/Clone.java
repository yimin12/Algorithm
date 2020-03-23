/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import williamsNotebook.common.graph.GraphNode;

/**
 * @author yimin Huang
 *
 * Algorithm Class
 */
public class Clone {
	
	static class RandomListNode{
		RandomListNode next;
		RandomListNode random;
		int val;
		public RandomListNode(int val) {
			this.val = val;
		}
	}

	// Follow Up 1, Each of the nodes in the linked list has another pointer pointing to a random node in the list or null. Make a deep copy of the original list
	// Method 1: using HashMap to avoid copy multiple times for the same node
	// Time: O(n) and Extra Space is O(n)
	public RandomListNode copyRandomList(RandomListNode head) {
		if(head == null) return null;
		// Sentinel node to help construct the deep copy
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode cur = dummy;
		// Maintain the mapping between the node in the original list and the corresponding node in the new list
		Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		while(head != null) {
			// step 1: copy the current node if necessary
			if(!map.containsKey(head)) {
				map.put(head, new RandomListNode(head.val));
			}
			// step 2: Connect the copied node to the deep copy list
			cur.next = map.get(head);
			if(head.random != null) {
				if(!map.containsKey(head.random)) {
					map.put(head.random, new RandomListNode(head.random.val));
				}
				cur.next.random = map.get(head.random);
			}
			head = head.next;
			cur = cur.next;
		}
		return dummy.next;
	}
	// Method 2: Another three pass Solution, not using hashMap, but changing the original list structure during the copy ( it will be changed back at the end)
	// Time: O(n) and Extra Space: O(n)
	public RandomListNode copyRandomListII(RandomListNode head) {
		if(head == null) {
			return null;
		}
		RandomListNode cur = head;
		// first pass, duplicate the RandomLinkedlist
		while(cur != null) {
			RandomListNode copy = new RandomListNode(cur.val);
			copy.next = cur.next;
			cur.next = copy;
			cur = cur.next.next;
		}
		cur = head;
		// second pass, linked the random pointer
		while(cur != null) {
			if(cur.random != null) {
				cur.next.random = cur.random.next;
			}
			cur = cur.next.next;
		}
		// third pass, extract the copied node
		cur = head;
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode copyPre = dummy;
		while(cur != null) {
			copyPre.next = cur.next;
			cur.next = cur.next.next;
			copyPre = copyPre.next;
			cur = cur.next;
		}
		return dummy.next;
	}
	
	// Follow Up 2: Make a deep copy of undirected graph, there could be cycles in the original graph
	// DFS: Time ~ O(|V| + |E|), Space ~ O(|V| + |E|), with given a list of graph
	public List<GraphNode> deepCopyGraph(List<GraphNode> graph){
		if(graph == null) {
			return null;
		}
		HashMap<GraphNode,GraphNode> map = new HashMap<GraphNode, GraphNode>();
		for(GraphNode node:graph) {
			if(!map.containsKey(node)) {
				map.put(node, new GraphNode(node.val));
				DFS(node, map);
			}
		}
		return new ArrayList<GraphNode>(map.values());
	}
	private void DFS(GraphNode seed, HashMap<GraphNode, GraphNode> map) {
		GraphNode copy = map.get(seed);
		for(GraphNode nei: seed.neighbors) {
			if(!map.containsKey(nei)) {
				map.put(nei, new GraphNode(nei.val));
				DFS(nei, map);
			}
			copy.neighbors.add(map.get(nei));
		}
	}
	// If given a node and traverse it by DFS
	// DFS: Time ~ O(|V| + |E|), Space ~ O(|V| + |E|)
	private Map<GraphNode, GraphNode> map = new HashMap<GraphNode, GraphNode>();
	public GraphNode cloneGraph(GraphNode node) {
		if(node == null) return null;
		return dfsClone(node);
	}
	private GraphNode dfsClone(GraphNode node) {
		if(map.containsKey(node)) return map.get(node);
		GraphNode copy = new GraphNode(node.val);
		map.put(node, copy);
		for(GraphNode nei:node.neighbors) {
			copy.neighbors.add(nei);
		}
		return copy;
	}
	// BFS: Time ~ O(|V| + |E|), Space ~ O(|V| + |E|)
	public GraphNode cloneGraphBFS(GraphNode node) {
		if(node == null) return null;
		GraphNode copy = new GraphNode(node.val);
		Map<GraphNode, GraphNode> map = new HashMap<GraphNode, GraphNode>();
		map.put(node, copy);
		Queue<GraphNode> q = new LinkedList<GraphNode>();
		q.add(node);
		while(!q.isEmpty()) {
			GraphNode v = q.poll();
			for(GraphNode nei: v.neighbors) {
				if(map.containsKey(nei)) {
					map.get(v).neighbors.add(map.get(nei));
				} else {
					GraphNode wCopy = new GraphNode(nei.val);
					map.get(v).neighbors.add(wCopy);
					map.put(nei, wCopy);
					q.add(nei);
				}
			}
		}
		return copy;
		
	}
}
