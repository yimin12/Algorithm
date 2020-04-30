/**
 * 
 */
package williamsNotebook.easy.graph;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月11日 下午3:11:47
* Description:
* 	Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.
* 	You need to support the following method:
* 		connect(a, b), add an edge to connect node a and node b`.
* 		query(a, b), check if two nodes are connected
*/
public class ConnectingGraph {

	// Implement of union find
	private int[] father = null;
	private int find(int x) {
		if(father[x] == x) {
			return x;
		}
		return father[x] = find(father[x]);
	}
	// Connect graph
	public ConnectingGraph(int n) {
		father = new int[n+1];
		for(int i = 1; i <= n; i++) {
			father[i] = i;
		}
	}
	public void connect(int a, int b) {
		int root_a = find(a);
		int root_b = find(b);
		if(root_b != root_a) {
			father[root_a] = root_b;
		}
	}
	public boolean query(int a, int b) {
		int root_a = find(a);
		int root_b = find(b);
		return root_a == root_b;
	}
}
