/**
 * 
 */
package williamsNotebook.common.huffmanTree;

import java.util.PriorityQueue;
import java.util.Scanner;

import williamsNotebook.common.node.HuffmanNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 下午8:18:52
* Description:
* 	https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
* 	check the definition of HuffmanTree by yourself
*/
public class HuffmanTree {

	public static void printCode(HuffmanNode root, String s) {
		if(root.left == null && root.right == null && Character.isLetter(root.c)) {
			System.out.println(root.c + " : " + s);
			return;
		}
		printCode(root.left, s + "0");
		printCode(root.right, "1");
	}
	// main function 
    public static void main(String[] args) 
    { 

        Scanner s = new Scanner(System.in); 

        // number of characters. 
        int n = 6; 
        char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' }; 
        int[] charfreq = { 5, 9, 12, 13, 16, 45 }; 

        // creating a priority queue q. 
        // makes a min-priority queue(min-heap). 
        PriorityQueue<HuffmanNode> q 
            = new PriorityQueue<HuffmanNode>(n, (a,b) -> a.data - b.data); 

        for (int i = 0; i < n; i++) { 

            // creating a huffman node object 
            // and adding it to the priority-queue. 
            HuffmanNode hn = new HuffmanNode(); 

            hn.c = charArray[i]; 
            hn.data = charfreq[i]; 

            hn.left = null; 
            hn.right = null; 

            // add functions adds 
            // the huffman node to the queue. 
            q.add(hn); 
        } 

        // create a root node 
        HuffmanNode root = null; 

        // Here we will extract the two minimum value 
        // from the heap each time until 
        // its size reduces to 1, extract until 
        // all the nodes are extracted. 
        while (q.size() > 1) { 

            // first min extract. 
            HuffmanNode x = q.peek(); 
            q.poll(); 

            // second min extarct. 
            HuffmanNode y = q.peek(); 
            q.poll(); 

            // new node f which is equal 
            HuffmanNode f = new HuffmanNode(); 

            // to the sum of the frequency of the two nodes 
            // assigning values to the f node. 
            f.data = x.data + y.data; 
            f.c = '-'; 

            // first extracted node as left child. 
            f.left = x; 

            // second extracted node as the right child. 
            f.right = y; 

            // marking the f node as the root node. 
            root = f; 

            // add this node to the priority-queue. 
            q.add(f); 
        } 

        // print the codes by traversing the tree 
        printCode(root, ""); 
    } 
}
