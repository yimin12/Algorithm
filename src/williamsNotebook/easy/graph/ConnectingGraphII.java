/**
 * 
 */
package williamsNotebook.easy.graph;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月11日 下午4:12:46
* Description:
* 	Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.
* 	You need to support the following method:
* 		connect(a, b), an edge to connect node a and node b
* 		query(a), Returns the number of connected component nodes which include node a.
* Example:
* 	Input:
		ConnectingGraph2(5)
		query(1)
		connect(1, 2)
		query(1)
		connect(2, 4)
		query(1)
		connect(1, 4)
		query(1)
	Output:[1,2,3,3]
*/

public class ConnectingGraphII {

	// classified Union Find
	private int[] father = null;
	private int[] size = null;
	
	// iterative find the target
	private int find(int x) {
		int j,fx;
		j = x;
		while(father[j] != j) {
			j = father[j];
		}
		// point all the element to father, compress the set
		while(x != j) {
			fx = father[x];
			father[x] = j;
			x = fx;
		}
		return j;
	}
	
	public void connect(int a, int b) {
		int root_a = find(a);
		int root_b = find(b);
		if(root_a != root_b) {
			father[root_a] = root_b;
			size[root_b] += size[root_a];
		}
	}
	public int query(int a) {
		int root_a = find(a);
		return size[root_a];
	}
}
