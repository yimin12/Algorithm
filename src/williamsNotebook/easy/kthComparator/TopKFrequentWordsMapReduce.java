/**
 * 
 */
package williamsNotebook.easy.kthComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月26日 上午12:15:05
* Description:
* 	use the idea of map reduce to find the top k frequent word
*/

public class TopKFrequentWordsMapReduce {

	public static class Map{
		 public void map(String s, Document value,
             OutputCollector<String, Integer> output) {
		     // Write your code here
		     // Output the results into output buffer.
		     // Ps. output.collect(String key, int value);
		     int id = value.id;
		     String content = value.content;
		     String[] words = content.split(" ");
		     for (String word : words)
		         if (word.length() > 0) {
		             output.collect(word, 1);
		         }
		 }
	}
	public static class Reduce{
		private PriorityQueue<Pair> Q = null;
		private int k;
		private Comparator<Pair> pairComparator = new Comparator<Pair>() {
			// first sorted by its value then sorted by lexic
			@Override
			public int compare(Pair o1, Pair o2) {
				if(o1.value != o2.value) {
					return o1.value - o2.value;
				}		
				return o2.key.compareTo(o1.key);
			}
		};
		public void setUp(int k) {
			this.k = k;
			Q = new PriorityQueue<Pair>(k, pairComparator);
		}
		
		public void reduce(String key, Iterator<Integer> values) {
			int sum = 0;
			while(values.hasNext()) {
				sum += values.next();
			}
			Pair pair = new Pair(key, sum);
			if(Q.size() < k) {
				Q.add(pair);
			} else {
				Pair next = Q.peek();
				// you can reuse the comparator 
				if(pairComparator.compare(pair, next) > 0) {
					Q.poll();
					Q.add(pair);
				}
			}
		}
		public void cleanUp(OutputCollector<String ,Integer> output) {
			List<Pair> pairs = new ArrayList<Pair>();
			while(!Q.isEmpty()) {
				pairs.add(Q.poll());
			}
			int n = pairs.size();
			for(int i = n - 1; i >= 0; i--) {
				Pair pair = pairs.get(i);
				output.collect(pair.key, pair.value);
			}
		}
	}
}

class OutputCollector<K, V>{
	public void collect(K key, V value) {
		// sout the message or store it in the buffer
	}
}
class Document{
	public int id;
	public String content;
}
class Pair{
	String key;
	int value;
	Pair(String key, int value){
		this.key = key;
		this.value = value;
	}
}
