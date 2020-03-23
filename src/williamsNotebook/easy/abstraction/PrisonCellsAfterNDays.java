/**
 * 
 */
package williamsNotebook.easy.abstraction;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月23日 下午12:02:21
* Description:
* 	There are 8 prison cells in a row, and each cell is either occupied or vacant.
* 	Each day, whether the cell is occupied or vacant changes according to the following rules:
* 	If a cell has two adjacent neighbors that are both occupied or both vacant,
* 	then the cell becomes occupied,Otherwise, it becomes vacant.
* 	(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
* 	We describe the current state of the prison in the following way: cells[i] == 1if thei-th cell is occupied, 
* 	elsecells[i] == 0.
* 	Given the initial state of the prison, return the state of the prison afterNdays (andNsuch changes described above.)
*/
public class PrisonCellsAfterNDays {

	// Method 1 :Brute force
	// Time: O(n * m), n - number of cells, m - days
	// Space: Extra Space: O(1) 
	public int[] prisonAfterDays(int[] cells, int days) {
		if(cells.length != 8 || days < 1) {
			return cells;
		}
		int n = cells.length;
		int prevVal, nextVal;
		while(days-- > 0) {
			prevVal = 0;
			nextVal = 0;
			for(int i = 0; i < n; i++) {
				if(i == 0 || i == n - 1) {
					prevVal = cells[i];
					cells[i] = 0;
					continue;
				}
				if(i < n - 1) {
					nextVal = cells[i+1];
				} else if(i == n - 1) {
					nextVal = 0;
				}
				if(prevVal == nextVal) {
					prevVal = cells[i];
					cells[i] = 1;
				} else {
					prevVal = cells[i];
					cells[i] = 0;
				}
			}
			System.out.println(Arrays.toString(cells));
		}
		return cells;
	}
	// Every time you are dealing with changing problem, it might have repeatly pattern
	// In this question, the pattern is that the change of 14 days is the same as the first day
	public int[] prisonAfterNDays(int[] cells, int N) {
		N = (N - 1)%14 + 1;
		for(; N > 0; N--) {
			int[] prison = new int[8];
			for(int i = 1; i < 7; i++) {
				prison[i] = cells[i-1] == cells[i+1] ? 1 : 0;
			}
			cells = prison;
			System.out.println(Arrays.toString(cells));
		}
		return cells;
	}
	public static void main(String[] args) {
		PrisonCellsAfterNDays solution = new PrisonCellsAfterNDays();
		int[] array = {0,1,0,1,1,0,0,1};
		solution.prisonAfterNDays(array,14);
	}
}
