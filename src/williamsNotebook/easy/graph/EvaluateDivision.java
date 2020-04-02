/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月1日 下午12:17:06
* Description:
* 	Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real
*   number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
* Example:
* 	Given a / b = 2.0, b / c = 3.0.  queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
* 	return [6.0, 0.5, -1.0, 1.0, -1.0 ].
* 	The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
*   According to the example above:
*   equations = [ ["a", "b"], ["b", "c"] ], values = [2.0, 3.0], queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
*/
public class EvaluateDivision {

	class Edge{
		String from, to;
		double value;
		Edge(String from, String to, double value){
			this.from = from;
			this.to = to;
			this.value = value;
		}
	}
	public double[] calEquation(String[][] equations, double[] values, String[][] queries) {
		// Build graph
		HashMap<String, List<Edge>> map = buildGraph(equations, values);
		double[] result = new double[queries.length];
		int idx = 0;
        // Calculate result for each query by searching in the graph
		for(String[] query : queries) {
			if(!map.containsKey(query[0])) {
				result[idx] = -1;
			} else {
				String n = query[0];
				String d = query[1];
				HashSet<String> visited = new HashSet<String>();
				double val = dfs(map, visited, 1.0, n, d);
				result[idx] = val;
			}
			idx++;
		}
		return result;
	}
	// Build Graph with adjacency list, the map will store the value with double direction
	private HashMap<String, List<Edge>> buildGraph(String[][] equations, double[] values){
		HashMap<String, List<Edge>> map = new HashMap<String, List<Edge>>();
		for(int i = 0; i < equations.length; i++) {
			// convert input equation to edge with value
			if(!map.containsKey(equations[i][0])) {
				map.put(equations[i][0], new ArrayList<Edge>());
			}
			map.get(equations[i][0]).add(new Edge(equations[i][0], equations[i][1],values[i]));
			// reverse order to store inverse value for edge
			if(!map.containsKey(equations[i][1])) {
				map.put(equations[i][1], new ArrayList<Edge>());
			}
			map.get(equations[i][1]).add(new Edge(equations[i][1], equations[i][0], 1.0 / values[i]));
		}
		return map;
	}
	// Recursively search a path from numerator (from) to denominator (to); return -1.0 if not found
	private double dfs(HashMap<String, List<Edge>> map, HashSet<String> visited, double pathValue, String from, String to) {
		if(from.equals(to)) {
			return pathValue;
		}
		visited.add(from);
		List<Edge> edges = map.get(from);
		if(edges != null) {
			for(Edge e:edges) {
				if(visited.contains(e.to)) {
					continue;
				}
				visited.add(e.to);
				double value = dfs(map, visited, pathValue * e.value, e.to, to);
				if(value != -1.0) {
					return value;
				}
			}
		}
		return -1.0;
	}
}
