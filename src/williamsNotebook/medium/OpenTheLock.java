/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月23日 下午6:17:49
* Description: LeetCode 752
* 	You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots:'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. 
* 	The wheels can rotate freely and wrap around: for example we can turn'9'to be'0', or'0'to be'9'. Each move consists of turning one 
* 	wheel one slot.
* 	The lock initially starts at'0000', a string representing the state of the 4 wheels.
* 	You are given a list ofdeadendsdead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it
* 	Given atargetrepresenting the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, 
* 		or -1 if it is impossible.
* 
*/
public class OpenTheLock {

	// Analysis: Why not DFS
	// Because the problem here asks for the minimum number of steps to achieve the target state. Using BFS, we can report the answer as long as we reach teh target state
	// But using DFS, we can't guarantee that the initial target state that we reach is the optimal solution. You still have to search the whole search space. 
	
	// Method 1:
	// Time Complexity: O(N^2 * A^N+D) where A is the number of digits in our alphabet, 
	// N is the number of digits in the lock, andDDis the size ofdeadends. We might visit every lock combination, 
	// plus we need to instantiate our setdead. When we visit every lock combination, we spend O(N^2) time enumerating 
	// through and constructing each node.
	public int openLock(String[] deadends, String target) {
		int minNumOfTurns = 0;
		HashSet<String> visited = new HashSet<String>();
		HashSet<String> deadendsSet = new HashSet<String>();
		for(String deadendString : deadends) {
			deadendsSet.add(deadendString);
		}
		// init the lock wheels
		char[] wheels = new char[0];
		for(int i = 0; i < 4; i++) {
			wheels[i] = '0';
		}
		Queue<String> queue = new LinkedList<String>();
		queue.offer("0000");
		while(!queue.isEmpty()) {
			int layerSize = queue.size();
			while(layerSize > 0) {
				String string = queue.poll();
				if(deadendsSet.contains(string)) {
					layerSize--;
					continue;
				}
				if(string.equals(target)) {
					return minNumOfTurns;
				}
				char[] chs = string.toCharArray();
				for(int i = 0; i < chs.length; i++) {
					char temp = chs[i];
					for(int d = -1; d <= 1; d +=2) {
						// because we have operation of +1 and -1, in case of out of bounds, we add 10 for the corner case
						chs[i] = (char)((temp - '0' + d + 10) % 10 + '0');
						String newString = new String(chs);
						// if new states has been visited before, do nothing
						if(!visited.contains(newString) && !deadendsSet.contains(newString)) {
							queue.offer(newString);
							visited.add(newString);
						}
					}
					chs[i] = temp;
				}
				layerSize--;
			}
			// after iterate all the possibilities, we can go deeper for next level
			minNumOfTurns++;
		}
		return -1;
	}
	// Method 2:  Bi-directional (2-end) BFS, this method is suitable for you know exactly position and the end position
	// We dont use Queue because the order does not matter here, HashSet can complete all the requirement. 
	public int openLockII(String[] deadends, String target) {
		Set<String> begin = new HashSet<String>();
		Set<String> end = new HashSet<String>();
		Set<String> deads = new HashSet<String>(Arrays.asList(deadends));
		begin.add("0000");
		end.add(target);
		int level = 0;
		Set<String> temp;
		while(!begin.isEmpty() && !end.isEmpty()) {
			if(begin.size() > end.size()) {
				temp = begin;
				begin = end;
				end = temp;
			}
			temp = new HashSet<String>();
			for(String s : begin) {
				if(end.contains(s)) return level;
				if(deads.contains(s)) continue;
				deads.add(s);
				StringBuilder sb = new StringBuilder(s);
				for(int i = 0; i < 4; i++) {
					char c = sb.charAt(i);
					String s1 = sb.substring(0,i) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(i+1);
					String s2 = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i+1);
					if(!deads.contains(s1)) {
						temp.add(s1);
					}
					if(!deads.contains(s2)) {
						temp.add(s2);
					}
				}
			}
			level++;
			begin = temp;
		}
		return -1;
	}
	public static void main(String[] args) {
		OpenTheLock solution = new OpenTheLock();
		char[] array = new char[4];
		System.out.println(array[1]);
	}
}
