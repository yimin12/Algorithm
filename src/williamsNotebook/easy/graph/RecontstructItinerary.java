/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author yimin Huang
 *
 *	Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 
 *	All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 *	Note:
 *		If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. 
 *			For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 *		All airports are represented by three capital letters (IATA code).
 *		You may assume all tickets form at least one valid itinerary
 *	Example 1:
 *		tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 *		Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 *	Example 2:
 *		tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 *		Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 *		Another possible reconstruction, is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order
 * Algorithm Class
 */
public class RecontstructItinerary {
	
	// Solution 1: Brute Force (naive dfs), Assume we have n tickets
	// Time: O(n) + O(nlogn) + O(n!) Space: O(n)
	public List<String> findItinerary(String[][] tickets){
		List<String> result = new ArrayList<String>();
		if(tickets == null || tickets.length == 0) throw new IllegalArgumentException("input tickets is invalid");
		int totalLen = tickets.length + 1;
		// create an graph to store the map information
		Map<String, List<String>> graph = new HashMap<String, List<String>>();
		for(String[] ticket:tickets) {
			if(!graph.containsKey(ticket[0])) {
				graph.put(ticket[0], new ArrayList<>());
			}
			graph.get(ticket[0]).add(ticket[1]);
		}
		// all the element in list should be sorted in lexical order as requirement
		for(List<String> list:graph.values()) {
			Collections.sort(list);
		}
		// starting point
		String start = "JFK";
		result.add(start);
		if(findItinerary(start, result, graph, totalLen)) {
			return result;
		}
		return null;
	}
	// pure dfs, return whether it has found a complete path
	private boolean findItinerary(String start, List<String> path, Map<String, List<String>> graph, int totalLen) {
		if(path.size() == totalLen) {
			return true;
		}
		// if the graph do not contain departure airport, return false
		if(!graph.containsKey(start)) {
			return false;
		}
		List<String> destinations = graph.get(start);
		// recursion rule, iterate the destination candidate
		for(int i = 0; i < destinations.size(); i++) {
			String dest = destinations.get(i);
			destinations.remove(dest);
			path.add(dest);
			if(findItinerary(dest, path, graph, totalLen)) {
				return true;
			}
			// handle the back tracking problem
			path.remove(path.size() - 1);
			destinations.add(i, dest);
		}
		return false;	
	}
	// Method 2: Eulerian path, In graph theory, an Eulerian trail (or Eulerian path) is trail a finite graph which visits
	// 		every exactly once. 
	// Implementation 1: Hierholzer Algorithm
	// path = []
	// DFS(u):
	// 		while (unvisited e(u, v), mark e and DFS(v))  END
	//		path.pushLeft(u)
	// Time: O(n + nlogn) for constructing graph and O(n) for Hierholizer : O(n + nlogn + n)
	// Space: O(n)
	public List<String> findItineraryHierholizer(String[][] tickets){
		Map<String, PriorityQueue<String>> graph = new HashMap<String, PriorityQueue<String>>();
		LinkedList<String> result = new LinkedList<String>();
		for(String[] ticket : tickets) {
			if(!graph.containsKey(ticket[0])) {
				PriorityQueue<String> queue = new PriorityQueue<String>();
				graph.put(ticket[0], queue);
			}
			graph.get(ticket[0]).add(ticket[1]);
		}
		dfs("JFK", result, graph);
		return result;
	}
	private void dfs(String start, LinkedList<String> path, Map<String, PriorityQueue<String>> graph) {
		PriorityQueue<String> queue = graph.get(start);
		// when there is nothing left, add the result to left side of path
		while(queue != null && !queue.isEmpty()) {
			dfs(queue.poll(), path, graph);
		}
		path.addFirst(start);
	}
	
	public static void main(String[] args) {
		RecontstructItinerary solution = new RecontstructItinerary();
		List<String> findItinerary = solution.findItineraryHierholizer(new String[][] {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}});
		System.out.println(findItinerary.toString());
	}
}
