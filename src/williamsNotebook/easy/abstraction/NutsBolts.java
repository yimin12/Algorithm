/**
 * 
 */
package williamsNotebook.easy.abstraction;

import java.util.Comparator;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月1日 下午11:57:56
* Description:
* 	Given a set of n nuts of different sizes and n bolts of different sizes. There is a one-one mapping between nuts and bolts.
* 	Comparison of a nut to another nut or a bolt to another bolt is not allowed. It means nut can only be compared with bolt 
* 	and bolt can only be compared with nut to see which one is bigger/smaller.
* 	We will give you a compare function to compare nut with bolt.
* Example:
* 	Given nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC'].
* 	Your code should find the matching bolts and nuts.
* 	one of the possible return:
* 	nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG'].
* 	we will tell you the match compare function. If we give you another compare function.
* 	the possible return is the following:
* 	nuts = ['ab','bc','dd','gg'], bolts = ['BC','AA','DD','GG'].
* 	So you must use the compare function that we give to do the sorting.
* 	The order of the nuts or bolts does not matter. You just need to find the matching bolt for each nut.
* 	
*/

public class NutsBolts {

	class NBComparator implements Comparator<String>{

		private NBComparator cmp;
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
		
		public int cmp(String o1, String o2) {
			return 0;
		}
	}
	// Use two partition
	public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
		if(nuts == null || bolts == null) return;
		if(nuts.length != bolts.length) return;
		qsort(nuts, bolts, compare, 0, nuts.length - 1);
	}
	private void qsort(String[] nuts, String[] bolts, NBComparator compare, int left, int right) {
		if(left >= right) return;
		int part_index = partition(nuts, bolts[left], compare, left, right);
		partition(bolts, nuts[part_index], compare, left, right);
		qsort(nuts, bolts, compare, left, part_index - 1);
		qsort(nuts, bolts, compare, part_index+1, right);
		
	}
	private int partition(String[] str, String pivot, NBComparator compare,int l, int u) {
		for (int i = l; i <= u; i++) {
			if (compare.cmp(str[i], pivot) == 0 ||
			  compare.cmp(pivot, str[i]) == 0) {
			  swap(str, i, l);
			  break;
			}
		}
		String now = str[l];
		int left = l;
		int right = u;
		while (left < right) {
			while (left < right && (compare.cmp(str[right], pivot) == -1 ||compare.cmp(pivot, str[right]) == 1)) {
			  right--;
			}
			str[left] = str[right];
		
			while (left < right && (compare.cmp(str[left], pivot) == 1 ||compare.cmp(pivot, str[left]) == -1)) {
			  left++;
			}
			str[right] = str[left];
			}
		str[left] = now;
		return left;
	}
	private void swap(String[] str, int l, int r) {
		String temp = str[l];
		str[l] = str[r];
		str[r] = temp;
	}
}
