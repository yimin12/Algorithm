/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月11日 下午9:36:56
* Description:
* 	Given a list of Connections, which is the Connection class (the city name at both ends of the edge and a 
* 	cost between them), find edges that can connect all the cities and spend the least amount.
* 	Return the connects if can connect all the cities, otherwise return empty list.
* Example:
* 	Input:
	["Acity","Bcity",1]
	["Acity","Ccity",2]
	["Bcity","Ccity",3]
	Output:
	["Acity","Bcity",1]
	["Acity","Ccity",2]
*/

public class MinimumSpanningTree {

	static class Connection{
		public String city1, city2;
		public int cost;
		public Connection(String city1, String city2, int cost) {
			this.city1 = city1;
			this.city2 = city2;
			this.cost = cost;
		}
	}
	// Version 1 of Union Find
	public List<Connection> lowestCostI(List<Connection> connections){
		// if you use lambda expression, you do not need to new Comparator
		Collections.sort(connections, (a, b) -> a.cost != b.cost ? a.cost - b.cost : (a.city1.equals(b.city1) ? a.city2.compareTo(b.city2) : a.city1.compareTo(b.city1)));
		Map<String, Integer> hash = new HashMap<String, Integer>();
		int n = 0;
		for(Connection connection : connections) {
			if(!hash.containsKey(connection.city1)) {
				hash.put(connection.city1, n++);
			}
			if(!hash.containsKey(connection.city2)) {
				hash.put(connection.city2, n++);
			}
		}
		int[] father = new int[n+1];
		List<Connection> results = new ArrayList<Connection>();
		// Connect the node 
		for(Connection connection: connections) {
			int num1 = hash.get(connection.city1);
			int num2 = hash.get(connection.city2);
			
			int root1 = find(num1,father);
			int root2 = find(num2,father);
			if(root1 != root2) {
				father[root1] = root2;
				results.add(connection);
			}
		}
		if(results.size() != n - 1) {
			return new ArrayList<Connection>();
		}
		return results;
	}
	public int find(int num, int[] father) {
		if(father[num] == 0) {
			return num;
		}
		return father[num] = find(father[num], father);
	}
	
	// Version 2, split the Union Find class
	int n = 0;
	public List<Connection> lowestCost(List<Connection> connections){
		List<Connection> res = new ArrayList<Connection>();
		UFS ufs = new UFS(connections.size() * 2);
		Collections.sort(connections, new Comparator<Connection>() {

			@Override
			public int compare(Connection o1, Connection o2) {
				if(o1.cost != o2.cost) {
					return o1.cost - o2.cost;
				} 
				if(o1.city1.equals(o2.city1)) {
					return o1.city2.compareTo(o2.city2);
				}
				return o1.city1.compareTo(o2.city1);
			}
		});
		for(Connection item : connections) {
			int c1 = getID(item.city1);
			int c2 = getID(item.city2);
			if(ufs.find(c1) != ufs.find(c2)) {
				res.add(item);
				ufs.union(c1, c2);
			}
		}
		if(res.size() == n - 1) {
			return res;
		} else {
			return new ArrayList<>();
		}
	}
	Map<String, Integer> names = new HashMap<String, Integer>();
	public int getID(String name) {
		if(names.containsKey(name)) {
			return names.get(name);
		} else {
			names.put(name, n++);
			return n - 1;
		}
	}
	// Union Find class
	public class UFS {
		int[] father = null;
		public UFS(int n) {
			father = new int[n+1];
			for(int i = 0; i <= n; i++) {
				father[i] = -1;
			}
		}
		public void union(int a, int b) {
			a = find(a);
			b = find(b);
			if(father[a] < father[b]) {
				father[a] += father[b];
				father[b] = a;
			} else {
				father[b] += father[a];
				father[a] = b;
			}
		}
		
		public int find(int x) {
			if(father[x] < 0) {
				return x;
			}
			father[x] = find(father[x]);
			return father[x];
		}
	}
}
