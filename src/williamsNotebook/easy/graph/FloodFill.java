/**
 * 
 */
package williamsNotebook.easy.graph;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月1日 上午12:40:44
* Description:
* 	Animageis represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
* 	Given a coordinate(sr, sc)representing the starting pixel (row and column) of the flood fill, and a pixel valuenewColor, "flood fill" the image.
* 	To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as 
* 	the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), 
* 	and so on. Replace the color of all of the aforementioned pixels with the newColor.
* 	At the end, return the modified image
* 	Input: image = [[1,1,1],[1,1,0],[1,0,1]] sr = 1, sc = 1, newColor = 2
* 	Output: [[2,2,2],[2,2,0],[2,0,1]]
* 	From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected by a path of the same color as the 
* 	starting pixel are colored with the new color. Note the bottom corner is not colored 2, because it is not 4-directionally 
* 	connected to the starting pixel.
* 	[
	 [1, 1, 1],
	 [1, 1, 0],
	 [1, 0, 1]
	]
*/

public class FloodFill {

	static int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor){
		if(image[sr][sc] != newColor) {
			dfs(image,sr,sc, image[sr][sc], newColor);
		}
		return image;
	}
	private void dfs(int[][] image, int row, int col, int srcColor, int newColor) {
		int m = image.length, n = image.length;
		image[row][col] = newColor;
		for(int[] dir:dirs) {
			int nextRow = row + dir[0];
			int nextCol = col + dir[1];
			if(valid(nextRow, nextCol, m, n, image, srcColor)) {
				dfs(image, nextRow, nextCol, srcColor, newColor);
			}
		}
	}
	private boolean valid(int x, int y, int m, int n, int[][] image, int srcColor) {
		return x >= 0 && x < m && y >= 0 && y < n && image[x][y] == srcColor;
	}
}
