/**
 * 
 */
package williamsNotebook.medium;

/**
 * @author yimin Huang
 *		
 *		There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 *		You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its 
 *		next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 *		Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 *		The solution is guaranteed to be unique.
 *
 * Algorithm Class
 */
public class GasStation {

	// Greedy Solution: Time ~ O(N), Space ~ O(1)
	// There are two insights: if total gas is not enough for the whole trip, not matter where you start. The result is gg.
	// 		If total gas is enough for the whole trip, you need to care where you start.
	// Basically, we use two pointer
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int prev = 0, total = 0, start = 0;  // two pointers are prev and total, and start is the starting position
		for(int i = 0; i < gas.length; i++) {
			prev += gas[i] - cost[i];
			total += gas[i] - cost[i];
			if(prev < 0) {
				prev = 0;
				start = i + 1;
			}
		}
		if(total < 0) return -1;
		else return start;
	}
	
}
