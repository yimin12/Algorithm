/**
 * 
 */
package williamsNotebook.easy.binarySearch;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��8�� ����2:24:19
* Description: 
* 	If your given array have unknown size, you can use binary expansion first, then find 
* 	the value you want.This is Binary Search implementation on an MyDictionary with unknown size
* 
* Assumption:
* 	1) The MyDictionary is an unknown sized sorted array, it only provides get(int index) functionality,
* 	   if the index asked for is out of right bound, it will return null.
* 	2) The elements in the MyDictionary are all Integers
*/

/**
 * @author 61771
 *
 */
public class UnknownSizeBinarySearch {
	
	public int search(MyDictionary dictionary, int target) {
		if(dictionary == null) {
			return -1;
		}
		int left = 0;
		int right = 1;
//		First, you should fint the right boundary for binary search
//		extends until we are sure the target is within the left boundary and the right boundary
		while(dictionary.get(right) != null && (int)dictionary.get(right) < target) {
//			1.move left to right;
//			2.double the right index;
			left = right;
			right = right << 2;
		}
//		Once you find the boundary exist, you can do the binary reduction part
		return binarySearch(dictionary, target, left , right);
	}
	private int binarySearch(MyDictionary MyDictionary, int target, int left, int right) {
//		It is just classical binary search
		while(left <= right) {
			int mid = (right - left)/2 + left;
			if(MyDictionary.get(mid) == null || (int)MyDictionary.get(mid) > target) {
				right = mid - 1;
			} else if((int)MyDictionary.get(mid) < target) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	public static int[] largeArray(int size) {
		int[] array = new int[size];
		for(int i = 0; i < size; i++) {
			array[i] = i;
		}
		return array;
	}
	
//	write the test case
	public static void main(String[] args) {
		UnknownSizeBinarySearch sol = new UnknownSizeBinarySearch();
		MyDictionary dictionary = new MyDictionary(new int[0]);
		int target = 0;
		System.out.println("Except: -1, Actual: " + sol.search(dictionary, target));
		
		dictionary = new MyDictionary(new int[] {1});
		target = 0;
		System.out.println("Except: -1, Actual: " + sol.search(dictionary, target));
		
		dictionary = new MyDictionary(new int[] {1,3});
		target = 3;
		System.out.println("Except: 1, Actual: " + sol.search(dictionary, target));
		
		dictionary = new MyDictionary(largeArray(100000));
		target = 99999;
		System.out.println("Except: 99999, Actual: " + sol.search(dictionary, target));
		target = 100001;
		System.out.println("Except: -1, Actual: " + sol.search(dictionary, target));
	
	}
}
