/**
 * 
 */
package williamsNotebook.medium;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��27�� ����1:03:35
* Description:
* 	Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor and wall��s width are 
* 	both 1 cell. For each pair of cells on the corridor, there must exist one and only one path 
* 	between them. (Randomly means that the solution is generated randomly, and whenever the program 
* 	is executed, the solution can be different.). The wall is denoted by 1 in the matrix and corridor 
* 	is denoted by 0.
* Assumptions:
* 	N = 2K + 1 and K >= 0;
* 	the top left corner must be corridor
* 	there should be as many as corridor cells as possible
* 	for each pair of cells on the orridor, there must exist one and only one path between them
* Examples:
* 	N = 5, one possible maze generated is
        
        0  0  0  1  0
        1  1  0  1  0
        0  1  0  0  0
        0  1  1  1  0
        0  0  0  0  0
*/

public class GenrerateRandomMaze {
//	example of using enum in Java, basically enum is good and recommended way for representing 
//	a set of predefined constants. 
	enum Dir{
		NORTH(-1,0), SOUTH(1,0),EAST(0,-1),WEST(0,1);
		
		int deltaX;
		int deltaY;
		
		Dir(int deltaX, int deltaY){
			this.deltaX = deltaX;
			this.deltaY = deltaY;
		}
		
//		move certain times of deltaX
		public int moveX(int x, int times) {
			return x + times * deltaX;
		}
		
//		move certain times of deltaY
		public int moveY(int y, int times) {
			return y + times * deltaY;
		}
	}
//	generating maze
	public int[][] maze(int n){
//		Assumption: n = 2 * k + 1, whrere k >= 0;
		int[][] maze = new int[n][n];
//		initialize the matrix as only (0,0) is corridor, other cells are all walls at the beginning.
//		leter we are trying to break walls to form corridors
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i == 0 && j == 0) {
					maze[i][j] = 0;
				} else {
					maze[i][j] = 1;
				}
			}
		}
		generate(maze, 0, 0);
		return maze;
	}
	private void generate(int[][] maze, int x, int y) {
//		get a random shuffle of all the possible directions
//		and follow the shuffled order to do DFS & backtrack
		Dir[] dirs = Dir.values();
		shuffle(dirs);
		for(Dir dir : dirs) {
//			advance by two steps
			int nextX = dir.moveX(x, 2);
			int nextY = dir.moveY(y, 2);
			if(isValidWall(maze, nextX, nextY)) {
//				only if the cell is a wall(meaning we have not visited before), we break the walls through 
//				to make it corridors/
				maze[dir.moveX(x, 1)][dir.moveY(y, 1)] = 0;
				maze[nextX][nextY] = 0;
				generate(maze, nextX, nextY);
			}
		}
	}
//	Get a random order of the direction
	private void shuffle(Dir[] dirs) {
		for(int i = 0; i < dirs.length; i++) {
			int index = (int)(Math.random() * (dirs.length - i));
			Dir tmp = dirs[i];
			dirs[i] = dirs[i + index];
			dirs[i + index] = tmp;
		}
	}
	
//	check if the position (x, y) is within the maze and it is a wall
	private boolean isValidWall(int[][] maze, int x, int y) {
		return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 1;
	}
}
