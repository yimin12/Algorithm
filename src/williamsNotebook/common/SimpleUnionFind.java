/**
 * 
 */
package williamsNotebook.common;

import java.util.HashMap;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月27日 上午10:58:40
* Description:
* 
*/
public class SimpleUnionFind{
	// Implemented with array instead of HashMap for simplicity
	public int[] parent;
	public int[] size;
	
	public SimpleUnionFind() {}
	SimpleUnionFind(int n){
		parent = new int[n];
		for(int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}
	public int find(int x) {
		return compress_find(x);
	}
	public int compress_find(int x) {
		int x_parent = parent[x];
		while(x_parent != parent[x_parent]) {
			x = parent[x_parent];
		}
		int temp = -1;
		int cur_parent = parent[x];
		while(cur_parent != parent[cur_parent]) {
			temp = parent[cur_parent];
			parent[cur_parent] = x_parent;
			cur_parent = temp;
		}
		return x_parent;
	}
	public void union(int x, int y) {
		int x_parent = find(x);
		int y_parent = find(y);
		if(x_parent == y_parent) {
			return;
		}
		if(this.size[x_parent] < this.size[y_parent]) {
			this.size[y_parent] += this.size[x_parent];
			this.parent[x_parent] = y_parent;
		} else {
			this.size[x_parent] += this.size[y_parent];
			this.parent[y_parent] = x_parent;
		}
	}
}

