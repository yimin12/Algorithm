/**
 * 
 */
package williamsNotebook.common.classified;

import java.util.Iterator;
import java.util.List;

import williamsNotebook.medium.TextJustification;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月26日 下午4:02:46
* Description:
* 	Design and implement an iterator to flatten a 2d vector. It should support the following operations:next and hasNext.
* 	Example:
* 	Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
* 	iterator.next(); // return 1
	iterator.next(); // return 2
	iterator.next(); // return 3
	iterator.hasNext(); // return true
	iterator.hasNext(); // return true
	iterator.next(); // return 4
	iterator.hasNext(); // return false
*/

// Use two index to record the position in matrix
public class Flatten2DVector {

	private int i, j;
	private int[][] v;
	public Flatten2DVector(int[][] v) {
		i = j = 0;
		this.v = v;
	}
	public int next() {
		int n = 0;
		if(i < v.length && j >= v[i].length) {
			while(i < v.length) {
				i++;
				if(j < v[i].length) {
					break;
				}
			}
		}
		n = v[i][j];
		if(j == v[i].length - 1) {
			j = 0;
			i++;
		} else {
			j++;
		}
		return n;
	}
	public boolean hasNext() {
		int ti = i;
		int tj = j;
		if(ti < v.length && tj < v[ti].length) {
			return true;
		} else if(ti < v.length) {
			while(ti < v.length) {
				if(tj < v[ti].length) return true;
				ti++;
			}
		}
		return false;
	}
}
// Method 2: Use Iterator
class Vector2D{
	private Iterator<List<Integer>> i;
	private Iterator<Integer> j;
	
	public Vector2D(List<List<Integer>> vectors) {
		i = vectors.iterator();
	}
	public int next() {
		// another uses of hasNext is to update new row for j;
		hasNext();
		return j.next();
	}
	public boolean hasNext() {
		while((j == null || !j.hasNext() && i.hasNext())) {
			j = i.next().iterator();
		}
		return j != null && j.hasNext();
	}
}
