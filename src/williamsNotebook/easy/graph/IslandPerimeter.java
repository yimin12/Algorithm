/**
 * 
 */
package williamsNotebook.easy.graph;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月1日 上午12:26:55
* Description:
* 	You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
* 	Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
* 	The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height 
* 	don't exceed 100. Determine the perimeter of the island.
* Example:
* 	Input:
		[[0,1,0,0],
		 [1,1,1,0],
		 [0,1,0,0],
		 [1,1,0,0]]
		Output:
		 16
		Explanation:
		 The perimeter is the 16 yellow stripes in the image below:
*/

public class IslandPerimeter {
	
	// Time: O(m * n * 4)
	// Common traverse, Use island * 4 - neighbor * 2;
	public int islandPerimeter(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
		int island = 0, neighbor = 0;
		for(int i= 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] == 1) {
					island++;
					// because it only focus on two directions(down and right), we do not need to record a visited matrix
					if(i < grid.length - 1 && grid[i + 1][j] == 1) {
						neighbor++;
					}
					if(j < grid[0].length - 1 && grid[i][j+1] == 1) {
						neighbor++;
					}
				}
			}
		}
		return island * 4 - neighbor * 2;
	}
}
