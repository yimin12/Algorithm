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

import williamsNotebook.common.node.UnDirectedGraphNode;


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
	public List<UnDirectedGraphNode> deepCopyGraph(List<UnDirectedGraphNode> graph){
		if(graph == null) {
			return null;
		}
		HashMap<UnDirectedGraphNode,UnDirectedGraphNode> map = new HashMap<UnDirectedGraphNode, UnDirectedGraphNode>();
		for(UnDirectedGraphNode node:graph) {
			if(!map.containsKey(node)) {
				map.put(node, new UnDirectedGraphNode(node.val));
				DFS(node, map);
			}
		}
		return new ArrayList<UnDirectedGraphNode>(map.values());
	}
	private void DFS(UnDirectedGraphNode seed, HashMap<UnDirectedGraphNode, UnDirectedGraphNode> map) {
		UnDirectedGraphNode copy = map.get(seed);
		for(UnDirectedGraphNode nei: seed.neighbors) {
			if(!map.containsKey(nei)) {
				map.put(nei, new UnDirectedGraphNode(nei.val));
				DFS(nei, map);
			}
			copy.neighbors.add(map.get(nei));
		}
	}
	// If given a node and traverse it by DFS
	// DFS: Time ~ O(|V| + |E|), Space ~ O(|V| + |E|)
	private Map<UnDirectedGraphNode, UnDirectedGraphNode> map = new HashMap<UnDirectedGraphNode, UnDirectedGraphNode>();
	public UnDirectedGraphNode cloneGraph(UnDirectedGraphNode node) {
		if(node == null) return null;
		return dfsClone(node);
	}
	private UnDirectedGraphNode dfsClone(UnDirectedGraphNode node) {
		if(map.containsKey(node)) return map.get(node);
		UnDirectedGraphNode copy = new UnDirectedGraphNode(node.val);
		map.put(node, copy);
		for(UnDirectedGraphNode nei:node.neighbors) {
			copy.neighbors.add(nei);
		}
		return copy;
	}
	// BFS: Time ~ O(|V| + |E|), Space ~ O(|V| + |E|)
	public UnDirectedGraphNode cloneGraphBFS(UnDirectedGraphNode node) {
		if(node == null) return null;
		UnDirectedGraphNode copy = new UnDirectedGraphNode(node.val);
		Map<UnDirectedGraphNode, UnDirectedGraphNode> map = new HashMap<UnDirectedGraphNode, UnDirectedGraphNode>();
		map.put(node, copy);
		Queue<UnDirectedGraphNode> q = new LinkedList<UnDirectedGraphNode>();
		q.add(node);
		while(!q.isEmpty()) {
			UnDirectedGraphNode v = q.poll();
			for(UnDirectedGraphNode nei: v.neighbors) {
				if(map.containsKey(nei)) {
					map.get(v).neighbors.add(map.get(nei));
				} else {
					UnDirectedGraphNode wCopy = new UnDirectedGraphNode(nei.val);
					map.get(v).neighbors.add(wCopy);
					map.put(nei, wCopy);
					q.add(nei);
				}
			}
		}
		return copy;
		
	}
}
