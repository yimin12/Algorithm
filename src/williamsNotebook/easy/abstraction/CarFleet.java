/**
 * 
 */
package williamsNotebook.easy.abstraction;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.TreeMap;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月24日 下午10:25:04
* Description:
* 	N cars are going to the same destination along a one lane road. The destination is target miles away.
* 	Each car i has a constant speed speed[i] (in miles per hour), and initial positionposition[i] miles towards the target along the road.
* 	A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.
* 	The distance between these two cars is ignored - they are assumed to have the same position.
* 	A_car fleet_is some non-empty set of cars driving at the same position and same speed. Note that a single car is also a car fleet.
* 	If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
* 	How many car fleets will arrive at the destination?
*/

public class CarFleet {

	// Method 1: Sorting and Using Stack
	// Time: O(n + nlogn) Space: O(n)
	static class Car{
		int position;
		double time;
		public Car(int position, double time) {
			this.position = position;
			this.time = time;
		}
		@Override
		public String toString() {
			return this.position + " : " + this.time;
		}
	}
	// Sorted by starting position, and determined by the approaching time
	public int carFleet(int target, int[] pos, int[] speed) {
		int n = pos.length;
		Car[] cars = new Car[n];
		for(int i = 0; i < pos.length; i++) {
			cars[i] = new Car(pos[i], 1.0*(target - pos[i])/speed[i]);
		}
		Arrays.sort(cars, (c1, c2) -> Integer.compare(c1.position, c2.position));
		Deque<Car> stack = new ArrayDeque<Car>();
		for(Car c:cars) {
			// the stack will become m ascending order with time's value
			while(!stack.isEmpty() && stack.peek().time <= c.time) {
				stack.pop();
			}
			stack.push(c);
		}
		return stack.size();
	}
	// Method 2: TreeMap, idea is the same as the former solution
	public int carFleetII(int target, int[] pos, int[] speed) {
		TreeMap<Integer, Double> map = new TreeMap<Integer, Double>();
		for(int i = 0; i < pos.length; i++) {
			map.put(-pos[i], (double)(target-pos[i])/speed[i]);
		}
		int res = 0; double cur = 0;
		// simulate the process of m stack, update the current max every when found
		for(double time:map.values()) {
			if(time > cur) {
				cur = time;
				res++;
			}
		}
		return res;
	}
}
