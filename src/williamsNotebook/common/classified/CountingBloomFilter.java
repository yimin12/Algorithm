/**
 * 
 */
package williamsNotebook.common.classified;

import java.util.ArrayList;
import java.util.List;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月8日 上午11:15:39
* Description:
* 	Implement a counting bloom filter. Support the following method:
* 		1.add(string). Add a string into bloom filter.
* 		2.contains(string). Check a string whether exists in bloom filter.
* 		3.remove(string). Remove a string from bloom filter.
* Example:
* 	 Input:
		CountingBloomFilter(3)
		add("lint")
		add("code")
		contains("lint") 
		remove("lint")
		contains("lint") 
	Output: 
		[true,false]
*/

public class CountingBloomFilter {

	static class HashFunction{
		public int cap, seed;
		public HashFunction(int cap, int seed) {
			this.cap = cap;
			this.seed = seed;
		}
		public int hash(String value) {
			int ret = 0;
			int n = value.length();
			for(int i = 0; i < n; i++) {
				// seed : e.g. 31 .. 47 ... or others
				ret += seed * ret + value.charAt(i);
				ret %= cap;
			}
			return ret;
		}
	}
	public int[] bits;
	public int k;
	public List<HashFunction> hashFunc;
	static final int LARGE_NUM = 100000;
	
	public CountingBloomFilter(int k) {
		// Initialize the CountingBloomFilter with capacity
		this.k = k;
		hashFunc = new ArrayList<HashFunction>();
		for(int i = 0; i < k; i++) {
			hashFunc.add(new HashFunction(LARGE_NUM + i, 2 * i + 3));
		}
		bits = new int[LARGE_NUM + k];
	}
	public void add(String word) {
		for(int i = 0; i < k; i++) {
			int position = hashFunc.get(i).hash(word);
			bits[position] += 1;
		}
	}
	public void remove(String word) {
		for(int i = 0; i < k; i++) {
			int position = hashFunc.get(i).hash(word);
			bits[position] -= 1;
		}
	}
	public boolean contains(String word) {
		for(int i = 0; i < k; i++) {
			int position = hashFunc.get(i).hash(word);
			if(bits[position] <= 0) {
				return false;
			}
		}
		return true;
	}
}
