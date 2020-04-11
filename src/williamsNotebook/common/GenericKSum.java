/**
 * 
 */
package williamsNotebook.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月9日 下午12:10:04
* Description:
* 	K Sum that can be used in duplicate situation and negative situation
*/
public class GenericKSum {

	private class Key{
		int _i;
		int _j;
		int _target;
		public Key(int _i, int _j, int _target) {
			super();
			this._i = _i;
			this._j = _j;
			this._target = _target;
		}
		boolean inValid() {
			return (_i < 1 || _j < 1 || _i < _j);
		}
		Key preWithCurrent(int d) {
			return new Key(_i - 1, _j - 1, _target - d);
		}
		Key preWithoutCurrent() {
			return new Key(_i - 1, _j , _target);
		}
		@Override
		public int hashCode() {
			int result = 17;
			result = result * 31 + _i; // result * 31 + i
			result = result * 31 + _j; // (result * 31 + i) * 31 + j
			result = result * 31 + _target; // ((result * 31 + i) * 31 + j) * 31 + target
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if(this == obj) return true;
			if(obj == null || obj.getClass() != getClass()) return false;
			Key temp = (Key)obj;
			return (this._i == temp._i && this._j == temp._j && this._target == temp._target);
		}
	}
	// Base On dfs and memorization
	// Memoize: <key_i, _j, _target>  ==> Set<Set<Integer>>
	// first i integers select j sum equals target  ==>  positions of selected integers
	// e.g. A[0,1,1] : <3,2,1> ==> { {0, 1}, {0, 2}}
	private HashMap<Key, Set<Set<Integer>>> mem = new HashMap<Key,  Set<Set<Integer>>>();
	/*
	 * 这边直接调用下面的ksum()将结果转换成lintcode的接口KSumII()
	 * @param A: an integer array
	 * @param k: a postive integer <= length(A)
	 * @param target: an integer
	 * @return: A list of lists of integer
	 */
	public List<List<Integer>> kSumII(int[] A, int k, int target){
		Set<Set<Integer>> temp = kSum(A, k, target);
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		for(Set<Integer> set : temp) {
			List<Integer> list = new ArrayList<Integer>();
			for(int i : set) {
				list.add(A[i]);
			}
			res.add(list);
		}
		return res;
	}
	public Set<Set<Integer>> kSum(int[] A, int k, int target) {
	    int len = A.length;
	    //initialize: i (1 ~ len) 之前取1个数等于某个值的情况就是它自己和一切在它之前跟它相等的数！
	    for (int i = 1; i <= len; i++) {
	        for (int p = 0; p < i; p++) {
	            Key key = new Key(i, 1, A[p]);
	            if (mem.isEmpty() || !mem.containsKey(key)) {
	                Set<Set<Integer>> r = new HashSet<>();
	                r.add(new HashSet<>(Arrays.asList(p)));
	                mem.put(key, r);
	            } else {
	                mem.get(key).add(new HashSet<>(Arrays.asList(p)));
	            }
	        }
	    }
	    Key key = new Key(len, k, target);
	    dfs(A, key);
	    return mem.get(key);
	}
	/**
	 * 以 dfs 不断进行前n个数选k个和为t的 操作，并记录中间结果
	 *
	 * @param A : 数组
	 * @Key key:
	 */
	private void dfs(int[] A, Key key) {
		if(key.inValid() || (!mem.isEmpty() && mem.containsKey(key))) return;
		int n = key._i;
		Key key1 = key.preWithCurrent(A[n-1]);
		dfs(A, key1);
		if(mem.containsKey(key1)) memorize(key1, key, true);
		Key key2 = key.preWithoutCurrent();
		dfs(A, key2);
		if(mem.containsKey(key2)) memorize(key2, key, false);
	}
	/**
	 * 根据 是否要包含进当前key所对应的最后一个数而决定如何生成中间结果
	 * @param pre: 前 i-1 选 j-1 和为 t - A[i-1] 所生成的key
	 * @param cur： 前 i-1 选 j 和为 t 所生成的key
	 * @param hasCur
	 */
	private void memorize(Key pre, Key cur, boolean hasCur) {
		Set<Set<Integer>> res = new HashSet<Set<Integer>>();;
		for(Set<Integer> s:mem.get(pre)) {
			Set<Integer> r = new HashSet<Integer>(s); // remember to deep copy here
			if(hasCur) r.add(cur._i - 1);
			if(mem.containsKey(cur)) mem.get(cur).add(r);
			else res.add(r);
		}
		if(mem.containsKey(cur)) mem.put(cur, res);
	}
}
