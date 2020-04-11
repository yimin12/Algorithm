/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月10日 上午10:44:38
* Description:
* 	Model raindrops falling on a sidewalk (sidewalk is 1m and raindrops are 1cm). How could we know when the sidewalk is 
* 	completely wet.
* 	Google Interview
* 
*/

public class RaindropSidewalk {
	// Method 1: Use TreeMap 
	private TreeSet<double[]> sideWalk = new TreeSet<double[]>(new Comparator<double[]>() {
		@Override
		public int compare(double[] o1, double[] o2) {
			return Double.compare(o1[0], o2[0]);
		}
	});
	public void addRainDrop(double center) {
		double[] range = new double[] {center - 0.5, center + 0.5}; // modeling it as square 
		double[] left = sideWalk.floor(range); // binary search tree, left sub tree of root, greatest value smaller than value 
		if(left != null && left[1] == range[0]) {
			range[0] = left[0];
			sideWalk.remove(left);
		}
		double[] right = sideWalk.ceiling(range); // binary search, right sub tree of root, least value grater than value
		if(right != null && right[0] < range[1]) {
			range[1] = right[1];
			sideWalk.remove(right);
		}
		sideWalk.add(range);
	}
	public boolean isCovered() {
		if(sideWalk.size() == 1) {
			if(sideWalk.first()[0] <= 0 && sideWalk.first()[1] >= 100) {
				return true;
			}
		}
		return false;
	}
	public void printSideWalk() {
		System.out.println("--" + sideWalk.size());
		for(double[] rain:sideWalk) {
			System.out.println("::" + Arrays.toString(rain));
		}
	}
	public void print() {
		System.out.println("size :: " + sideWalk.size());
	}
	
	public static void main(String[] args) {
		Random rand = new Random();
		RaindropSidewalk s = new RaindropSidewalk();
		int count = 0;
		for(int rain = 0; rain <= 100; rain++) {
			s.addRainDrop(rain);
			s.printSideWalk();
		}
		while(!s.isCovered()) {
			double rain = (double)rand.nextInt(101) + rand.nextDouble();
			s.addRainDrop(rain);
			count++;
			if(count % 1e5 == 0) {
				s.print();
			}
			if(count % 1e7 == 0) {
				s.printSideWalk();
			}
			if(count > 1e9) {
				break;
			}
		}
		System.out.println("count: " + count);
	}
}
// Method 2: Use interval rather than treeMap split
class RainDrop{
	class Interval{
		public double start;
		public double end;
		public boolean isCoverd;
		public Interval() {
			this.start = 0;
			this.end = 0;
			this.isCoverd = false;
		}
	}
	public class SideWalk{
		private int curOccupiedCount;
		public int count;
		public Interval[] intervals;
		public SideWalk(int count) {
			this.curOccupiedCount = 0;
			this.count = count;
			this.intervals = new Interval[count];
			for(int i = 0; i < this.count; i++) {
				this.intervals[i] = new Interval();
			}
		}
		public void DropRain(double start, double end) {
			int index = (int)Math.floor(start);
			if(index >= 0) {
				if(Math.abs(start - index) < 0.0001) {
					if(!this.intervals[index].isCoverd) {
						this.curOccupiedCount++;
						this.intervals[index].isCoverd = true;
					}
					return;
				}
				if(this.intervals[index].isCoverd) {
					this.intervals[index].end = Math.max(this.intervals[index].end, index + 1 - start);
					if(this.intervals[index].start + this.intervals[index].end >= 1) {
						this.intervals[index].isCoverd = true;
						this.curOccupiedCount++;
					}
				}
			}
		}
		public boolean hasOccupied() {
			return this.curOccupiedCount == this.count;
		}
		public void simulateRainDrop() {
			SideWalk sideWalk = new SideWalk(100);
			while(!sideWalk.hasOccupied()) {
				Random rand = new Random();
				double start = rand.nextInt(101) - 1 + rand.nextDouble();
				double end = start + 1;
				sideWalk.DropRain(start, end);
				System.out.println(start + ", " + end);
			}
			System.out.println("occupied");
		}
	}
}

