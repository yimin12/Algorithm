/**
 * 
 */
package williamsNotebook.easy.kthComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
/**
 * @author yimin Huang
 *
 *	Given a non-empty list of words, return the k most frequent elements
 *	You answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with 
 *  the lower alphabetical order comes first
 *  
 *  AssumptionsÔº?
 *  	the composition is not null and is not guaranteed to be sorted
 *  	K >= 1 and K could be larger than the number of distinct words in the composition, in this case, just return all
 *      	the distinct words
 *  ReturnÔº?
 *  	a list of words ordered from most frequent one to least frequent one (the list could be of size K or smaller than K)
 *  Example:
 *  	Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 2 frequent words are [‚Äúb‚Ä?, ‚Äúc‚Äù]
 *  	Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 4 frequent words are [‚Äúb‚Ä?, ‚Äúc‚Ä?, "a", "d"]
 *  	Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 5 frequent words are [‚Äúb‚Ä?, ‚Äúc‚Ä?, "a", "d"]
 * Algorithm Class
 */
public abstract class TopKFrequency {

	// Method 1: Sort and use hashMap
	// Step 1: Count the frequency of each word using a hash map. Time: O(n), Extra Space: O(n)
	// Step 2: Flatten the hash map to an array of word counts. Time: O(n), Extra Space: O(n)
	// Step 3: Sort the word counts by descending frequency. Time: O(nlogn), Extra Space: O(1) (by using merge sort default)
	// Step 4: Output the first k words. Time: O(k), Extra Space: O(k)
	// Time: O(nlogn), Extra Space: O(n)
	public List<String> topKFrequentWords(String[] words, int k){
		// sanity check 
		if(words == null || k <= 0) throw new IllegalArgumentException("The words can not be null or k can not be negative");
		// Count the frequency of each word
		Map<String, Integer> wordCounts = countWords(words);
		return topKFrequentWordsFromCounts(wordCounts, Math.min(k, wordCounts.size()));
	}
	private Map<String, Integer> countWords(String[] words){
		Map<String, Integer> wordCounts = new HashMap<String, Integer>();
		for(String word:words) {
			// you can get count first and find whether it is null
			int count = wordCounts.getOrDefault(word, 0); // get the value, if it is null, return 0.
			wordCounts.put(word, count+1);
		}
		return wordCounts;
	}
	protected abstract List<String> topKFrequentWordsFromCounts(Map<String, Integer> wordCounts, int k);
	// helper function for sort the element came out from wordsCount
	protected static Comparator<Map.Entry<String, Integer>> byDescendingFrequency = Comparator.comparing(Map.Entry<String, Integer>::getValue)
			.reversed().thenComparing(Map.Entry<String, Integer>::getKey);
	
	// Method 2: Similar with Method 1 but applying the maxHeap 
	// Step 1: Count the frequency of each word. 	Time: O(n), Extra Space: O(n)
	// Step 2: Insert all word counts into max heap.	Time: O(nlogn) or O(n) with heapification, Extra Space: O(n)
	// Step 3: Pop from the max heap k times to the result.	Time: O(klogn), Extra Space: O(k)
	// Time: O(nlogn) or O(n + klogn) with heapification, Extra Space: O(n)
	
	// Method 3: Similar with Method 2 but applying the minHeap
	// Step 1: Count the frequency of each word. 	Time: O(n), Extra Space: O(n)
	// Step 2: Create min heap of capacity k that maintains the top k frequent words observed so far.	Time: O(n), Extra Space: O(n)
	// step 3: Try inserting all word counts into the min heap.	Time: O(nlogk), Extra Space: O(k)
	//  	a. if the min heap is not full (size < k), insert
	// 		b. otherwise, pop the min and insert the current
	// step 4: Pop all words in the min heap and output them in reverse order.	Time: O(klogk), Extra Space: O(k)
	// Time: O(nlogk), Extra Space: O(n)
	
	// good optimization
	// Method 4: Partition, thought is similar with quick sort, assuming you are searching top k elements
	// step 1: Randomly select a pivot and partition the array
	// step 2: Either find the answer or continue partitioning.
	// 		a. k = index(pivot) : the left k words are the answer
	// 		b. k > index(pivot)	: continue partitioning the right half
	//		c. k < index(pivot) : continue partitioning the left half
	// step 3: Repeat 1 and 2 until we reach k = index(pivot)
	// step 4: Sort the leftmost k word counts
	// Time space Complexity: Time: O(n), Extra Space: O(n) word counting
	// partitioning: Average-case O(n) + O(n/2) + O(n/4).... = O(n) time
	//				 Worst-case  O(n) + O(n-1) + O(n-2).... = O(n^2) time	
	// Sort the first k and output : O(klogk) time and O(k) extra space
	// Time: O(n + klogk) and Space: O(n) extra space 
	
	public static void main(String[] args) {
		// Test the method 1
		TopFrequentWordsSolverSorting solution = new TopFrequentWordsSolverSorting();
		List<String> topKFrequentWords = solution.topKFrequentWords(new String[] {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"}, 5);
		System.out.println("solution 1: " + topKFrequentWords.toString());
		
		// Test the method 2
		TopFrequentWordsSolverUsingMaxHeap solution2 = new TopFrequentWordsSolverUsingMaxHeap();
		List<String> topKFrequentWords2 = solution2.topKFrequentWords(new String[] {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"}, 5);
		System.out.println("solution 2: " + topKFrequentWords2.toString());
		
		// Test the method 3
		TopFrequentWordsSolverUsingMinHeap solution3 = new TopFrequentWordsSolverUsingMinHeap();
		List<String> topKFrequentWords3 = solution3.topKFrequentWords(new String[] {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"}, 5);
		System.out.println("solution 3: " + topKFrequentWords3.toString());
		
		// Test the method 4
		TopFrequentWordsSolverByPartition solution4 = new TopFrequentWordsSolverByPartition();
		List<String> topKFrequentWords4 = solution4.topKFrequentWords(new String[] {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"}, 5);
		System.out.println("solution 4: " + topKFrequentWords4.toString());
	}
}
// Method 1's helper class
class TopFrequentWordsSolverSorting extends TopKFrequency{
	
	@Override
	protected List<String> topKFrequentWordsFromCounts(Map<String, Integer> wordCounts, int k){
		// step 2, flatten hash map to an array of words counts by using comparator
		List<Map.Entry<String, Integer>> flatWordCounts = new ArrayList<Map.Entry<String,Integer>>(
				wordCounts.entrySet()); 
		// Step 3: Sort the word counts by descending frequency.
		Collections.sort(flatWordCounts, byDescendingFrequency); // sort the flatten words by value first (descending) then key (ascending).
		List<String> result =  new ArrayList<String>();
		// Step 4: Output the first k words
		for(int i = 0; i < k; i++) {
			result.add(flatWordCounts.get(i).getKey());
		}
		return result;
	}
}
// Method 2's helper class (step 1 is the same as Method 1)
class TopFrequentWordsSolverUsingMaxHeap extends TopKFrequency{

	@Override
	protected List<String> topKFrequentWordsFromCounts(Map<String, Integer> wordCounts, int k) {
		List<String> res = new ArrayList<String>();
		// step 2.Insert all word counts into max heap.
		PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<Map.Entry<String,Integer>>(wordCounts.size(), byDescendingFrequency);
		for(Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
			maxHeap.add(entry);
		}
		// Step 3: Pop from the max heap k times to the result.
		for(int i = 0; i < k; i++) {
			res.add(maxHeap.poll().getKey());
		}
		return res;
	}
}
// Method 3's helper class (step 1 is same as Method 1)
class TopFrequentWordsSolverUsingMinHeap extends TopKFrequency{

	@Override
	protected List<String> topKFrequentWordsFromCounts(Map<String, Integer> wordCounts, int k) {
		// Create min heap of capacity k that maintains the top k frequent words observed so far
		PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<Map.Entry<String,Integer>>(k, byDescendingFrequency.reversed());
		Iterator<Map.Entry<String, Integer>> iterator = wordCounts.entrySet().iterator();
		// step 3
		for(int i = 0; i < k; i++) {
			minHeap.add(iterator.next());
		}
		for(int i = k; i < wordCounts.size(); i++) {
			Map.Entry<String, Integer> wordCount = iterator.next();
			// means that wordCount's frequency is larger than peeking value
			if(byDescendingFrequency.compare(wordCount, minHeap.peek()) < 0) {
				minHeap.poll();
				minHeap.add(wordCount);
			}
		}
		// step 4
		List<String> result = new ArrayList<String>();
		while(!minHeap.isEmpty()) {
			result.add(minHeap.remove().getKey());
		}
		// post processing: reverse the order to meet the requirement
		Collections.reverse(result);
		return result;
	}
}
// Method 4's helper class
class TopFrequentWordsSolverByPartition extends TopKFrequency{

	@Override
	protected List<String> topKFrequentWordsFromCounts(Map<String, Integer> wordCounts, int k) {
		ArrayList<Map.Entry<String, Integer>> flatWordCount = new ArrayList<Map.Entry<String,Integer>>(wordCounts.entrySet());
		int left = 0;
		int right = flatWordCount.size() - 1;
		while(left < right) {
			int pivotIndex = partition(flatWordCount, left, right);
			// 2.a find the answer
			if(pivotIndex == k - 1) {
				break;
			}
			if(pivotIndex < k - 1) {
				left = pivotIndex + 1;
			} else {
				right = pivotIndex - 1;
			}
		}
		Collections.sort(flatWordCount.subList(0, k), byDescendingFrequency);
		List<String> res = new ArrayList<String>();
		for(int i = 0; i < k; i++) {
			res.add(flatWordCount.get(i).getKey());
		}
		return res;
	}
	private int partition(ArrayList<Map.Entry<String, Integer>> word, int left, int right) {
		int pivotIndex = pivotIndex(left, right);
		int pivot = word.get(pivotIndex).getValue();
		swap(word, pivotIndex, right);
		int rightBound = right - 1;
		while(left <= rightBound) {
			if(word.get(left).getValue() < pivot) {
				left++;
			} else if(word.get(rightBound).getValue() > pivot) {
				rightBound--;
			} else {
				swap(word, left++,rightBound--);
			}
		}
		// swap pivot back to original place
		swap(word, left, right);
		return left;
	}
	private void swap(ArrayList<Map.Entry<String, Integer>> word, int left, int right) {
		Map.Entry<String, Integer> temp = word.get(left);
		word.add(left, word.get(right));
		word.remove(left+1);
		word.add(right, temp);
		word.remove(right+1);
	}
	private int pivotIndex(int left, int right) {
		return left + (int)(Math.random()*(right - left + 1));
	}
}
