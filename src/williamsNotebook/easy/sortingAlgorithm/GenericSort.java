package williamsNotebook.easy.sortingAlgorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��22�� ����7:04:59
* Description:
* 	It would be nice if we could wirte a single sort method that could sort the elements in an Integer array, String array, 
* 	or an array of any type that supports ordering
* Assumption:
* 	no assumption
* Examples:
* 	java generic methods and generic classes enable programmers to specify with a single method declaration, or with a single class
* 	declaration
* Syntax:
* 	public static <E> void printArray(E[] inputArray){}
*/

public class GenericSort {
	// generic method to printArray (different type of array)
	public static <E> void printArray(E[] inputArray) {
		for(E element:inputArray) {
			System.out.printf("%s", element);
		}
		System.out.println();
	}
	public static <A, B, C> void printAll(A[] arr, List<B> list, Queue<C> queue) {
		for(A a : arr) {
			System.out.print(a + " ");
		}
		System.out.println("");
		for(int i = 0; i < list.size(); i++) {
			B b = list.get(i);
			System.out.print(b +" ");
		}
		System.out.println("");
		while(!queue.isEmpty()) {
			C c = queue.poll();
			System.out.print(c+" ");
		}
	}
	public static <E extends Comparable<E>> E getMin(E[] arr) {
		if(arr==null || arr.length == 0) {
			return null;
		}
		E min=arr[0];
		for(int i = 1; i < arr.length; i++) {
			min = arr[i].compareTo(min) < 0 ? arr[i]:min;
		}
		return min;
	}
	public static void main(String args[]) {
		Integer[] intArray = {1,2,3,4,5};
		Double[] doubleArray = {1.1,2.2,3.3,4.4,5.5};
		Character[] charArray = {'W','A','N'};
		System.out.println("intArray:");
		printArray(intArray);
		System.out.println("doubleArray:");
		printArray(doubleArray);
		System.out.println("charArray:");
		printArray(charArray);
		
		System.out.println("********************************");
		Integer[] array  = {1,2,3};
		List<Double> list = new ArrayList<>();
		list.add(1.5);
		list.add(2.5);
		list.add(3.5);
		Queue<String> queue = new ArrayDeque<>();
		queue.offer("f");
		queue.offer("u");
		queue.offer("c");
		queue.offer("k");
		printAll(array, list, queue);
		System.out.println("********************************");
		Integer[] intArray1 = {1,2,3,4,5};
		Integer m1 = getMin(intArray1);
		Double[] doubleArray1 = {1.1,2.2,3.3,4.4,5.5};
		Double m2 = getMin(doubleArray1);
		Character[] charArray1 = {'W','A','N'};
		Character m3 = getMin(charArray);
		System.out.println(m1 + " " + m2 + " " + m3);
	}
}
