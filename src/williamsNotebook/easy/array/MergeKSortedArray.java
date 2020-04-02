/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��23�� ����11:27:47
* Description:
* 	Merge K sorted array into one big sorted array in ascending order.
* Assumption:
* 	The input arrayOf Arrays is not null, none of the arrays is null either.
* 	Each of the sub array is sorting in ascending order
*/

public class MergeKSortedArray {
	public int[] merge(int[][] arrayOfArrays) {
//		Assumptions: arrayOfArrays is not null, none of the array is null either;
//		In the following step, we only need poll one element out and offer one element in, 
//		we just need to set the default of minHeap value as arrayOfArrays.length	
		PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(11, new MyComparator());
		int length = 0;
		for(int i=0; i < arrayOfArrays.length; i++) {
			int[] array = arrayOfArrays[i];
			length += array.length;
			if(array.length != 0) {
//				we use two index to record the position of each element:
//				the index of the array in the arrayOfArrays
//				the index of the element in the array
				minHeap.offer(new Entry(i, 0, array[0]));
			}
		}
		int[] result = new int[length];
		int cur = 0;
//		poll one element out and
		while(!minHeap.isEmpty()) {
			Entry tmp = minHeap.poll();
			result[cur++] = tmp.value;
			if(tmp.y + 1 < arrayOfArrays[tmp.x].length) {
//				reuse the same Entry object but advance the index by 1
				tmp.y++;
				tmp.value = arrayOfArrays[tmp.x][tmp.y];
//				everyTime you put an need tep in minHeap, you need logn to compare, n is the size of minHeap 
				minHeap.offer(tmp);
			}
		}
		return result;
	}
	static class Entry{
//		The row number
		int x;
//		The column number
		int y;
//		The corresponding value
		int value;
		Entry(int x, int y, int value){
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
	static class MyComparator implements Comparator<Entry>{

		@Override
		public int compare(Entry o1, Entry o2) {
			if(o1.value == o2.value) {
				return 0;
			}
			return o1.value < o2.value ? -1 : 1;
		}
		
	}
}
