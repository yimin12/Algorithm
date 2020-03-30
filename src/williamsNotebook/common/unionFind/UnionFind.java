/**
 * 
 */
package williamsNotebook.common.unionFind;

import java.util.HashMap;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月27日 上午10:58:40
* Description:
* 
*/
// method for union find, implemented by hashmap or array
// The type of Storing element is integer and assume there is no duplicate
public class UnionFind{
	// param1: node, param2: father's node
	public HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();
	public UnionFind(Set<Integer> hashSet) {
		// the father of themselves are themselves
		for(Integer now : hashSet) {
			this.father.put(now, now);
		}
	}
	// find the groups where the x belongs to
	public int find(int x) {
		int parent = father.get(x);
		while(parent != father.get(parent)) {
			parent = father.get(parent);
		}
		return parent;
	}
	public int compress_find(int x) {
		int parent = father.get(x);
		while(parent != father.get(parent)) {
			parent = father.get(parent);
		}
		int temp = -1;
		int next = father.get(x);
		while(next != father.get(next)) {
			temp = father.get(next);
			father.put(next, parent);
			next = temp;
		}
		return parent;
	}
	// Who is father do not matter here.
	public void union(int x, int y) {
		int father_X = find(x);
		int father_Y = find(y);
		if(father_X != father_Y) {
			father.put(father_X, father_Y);
		}
	}
}

