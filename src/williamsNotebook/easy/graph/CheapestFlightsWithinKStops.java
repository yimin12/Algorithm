/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月1日 上午11:07:22
* Description:
*	There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w
*	Now given all the cities and flights, together with starting city src and the destination dst, 
*	your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output-1
* Examples:
* 	Input: n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
* 	Output: 200
* 	  0
   /    \
 100    500
 /         \
1 ---100--- 2
	The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
	The size of flights will be in range [0, n * (n - 1) / 2].
	The format of each flight will be (src, dst, price).
	The price of each flight will be in the range [1, 10000].	
	There will not be any duplicated flights or self cycles.
*/


public class CheapestFlightsWithinKStops {

	// Dijkstra's Algorithm (BFS) with PriorityQueue Implementation
	// The key difference with the classic Dijkstra algorithm is, we don't maintain the global optimal 
	// distance to each node, i.e. ignore below optimization:
	// alt ← dist[u] + length(u, v), if alt < dist[v]:
	// Time: O(E + nlogn) where E is the total number of flights
	// Space: O(n), extra space for heap
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		if(flights == null || n <= 0 || k <= 0) return -1;
		// key: Src of Flight   Value: List of Dst can go to
		HashMap<Integer, List<Flight>> map = new HashMap<Integer, List<Flight>>();
		for(int[] flight: flights) {
			if(!map.containsKey(flight[0])) {
				map.put(flight[0], new ArrayList<Flight>());
			}
			map.get(flight[0]).add(new Flight(flight[0], flight[1], flight[2]));
		}
		// Sorted by cost
		Comparator<Stop> cmp = new Comparator<Stop>() {
			@Override
			public int compare(Stop o1, Stop o2) {
				return o1.cost < o2.cost ? -1:1;	
			}
		};
		PriorityQueue<Stop> q = new PriorityQueue<Stop>(cmp);
		q.offer(new Stop(src, 0, k));
		while(!q.isEmpty()) {
			Stop cur = q.poll();
			if(cur.id == dst) {
				return cur.cost;
			}
			if(cur.count >= 0) {
				List<Flight> list = map.get(cur.id);
				if(list == null) {
					continue;
				}
				for(Flight  f:list) {
					q.offer(new Stop(f.dst, f.price + cur.cost, cur.count - 1));
				}
			}
		}
		return - 1;
	}
	
	// Method 2: DFS with pruning
	// Time: O(n ^ (k + 1))
	// Space: O(k+1) where k is number of flights and n is number of cities
	private static int cheapest;
	private static boolean routeFound;
	public int findCheapestPriceDFS(int n, int[][] flights, int src, int dst, int K) {
		cheapest = Integer.MAX_VALUE;
		routeFound = false;
		HashMap<Integer, List<Flight>> map = new HashMap<Integer, List<Flight>>();
		for(int[] flight: flights) {
			if(!map.containsKey(flight[0])) {
				map.put(flight[0], new ArrayList<Flight>());
			}
			map.get(flight[0]).add(new Flight(flight[0], flight[1], flight[2]));
		}
		boolean[] visited = new boolean[n];
		dfs(map, visited, src, dst, 0, K);
		if(!routeFound) {
			return -1;
		}
		return cheapest;
	}
	private void dfs(HashMap<Integer, List<Flight>> map, boolean[] visited, int src, int dst, int cost, int k) {
		if(src == dst) {
			routeFound = true;
			cheapest = Math.min(cheapest, cost);
			return;
		}
		if(k < 0) {
			return;
		}
		List<Flight> flights = map.get(src);
		if(flights != null) {
			for(Flight flight : flights) {
				// do not visited the same city twice
				if(visited[flight.dst]) {
					continue;
				}
				// Pruning: reduce the computation
				if(flight.price + cost > cheapest) {
					continue;
				}
				visited[flight.dst] = true;
				dfs(map, visited, flight.dst, dst, cost + flight.price, k - 1);
				visited[flight.dst] = false;
			}
		}
	}
	class Flight{
		int src, dst, price;
		Flight(int _src, int _dst, int _price){
			src = _src;
			dst = _dst;
			price = _price;
		}
	}
	class Stop{
		int id, cost, count;
		Stop(int _id, int _cost, int _count){
			id = _id;
			cost = _cost;
			count = _count;
		}
	}
	
}
