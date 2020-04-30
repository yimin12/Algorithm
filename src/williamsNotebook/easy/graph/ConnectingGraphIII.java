/**
 * 
 */
package williamsNotebook.easy.graph;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月11日 下午4:20:19
Description:
* 	Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.
* 	You need to support the following method:
* 		connect(a, b), an edge to connect node a and node b
* 		query(a), Returns the number of connected component nodes which include node a.
* 	You need to support the following method:
	* 	connect(a, b), an edge to connect node a and node b
	* 	query(), Returns the number of connected component in the graph	
	* Example:
	*Input:
		ConnectingGraph3(5)
		query()
		connect(1, 2)
		query()
		connect(2, 4)
		query()
		connect(1, 4)
		query()
		
		Output:[5,4,3,3]
*/

public class ConnectingGraphIII {

	private int[] father = null;
	private int count;
	
	private int find(int x) {
		if(father[x] == x) {
			return x;
		}
		return father[x] = find(father[x]);
	}
	public ConnectingGraphIII(int n) {
		father = new int[n+1];
		count = n;
		for(int i = 0; i <= n; i++) {
			father[i] = i;
		}
	}
	public void connect(int a, int b) {
		int root_a = find(a);
		int root_b = find(b);
		if(root_a != root_b) {
			father[root_a] = root_b;
			count--;
		}
	}
	public int query() {
		return count;
	}
}
