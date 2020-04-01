/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月31日 下午2:01:42
* Description:
* 	There are a total of _n _courses you have to take, labeled from0ton-1.
* 	Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair:[0,1]
* 	Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses
* 	There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
* Example 1:
* 	Input: 2, [[1,0]]  Output: [0,1]
* 	Input: 4,  [[1,0],[2,0],[3,1],[3,2]] Output: [0,1,2,3] or [0,2,1,3]
* The input prerequisites is a graph represented by a list of edges
* You may assume that there are no duplicate edges in the input prerequisites.	
*/
public class CourseScheduleII {

	// Similar to Course Schedule, this problem just needs to return the topologically sorted results.
	// DFS - Topological Sorting
	private ArrayList[] graph;
	public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
		graph = new ArrayList[numCourses];
		int[] visited = new int[numCourses];
		// states: 0 = unknown, 1 = visiting, 2 = visited
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 0; i < numCourses; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		for(int[] p : prerequisites) {
			graph[p[0]].add(p[1]);
		}
		for(int i = 0; i < numCourses; i++) {
			// if return true, means that you can not finish these courses and return new int[0]
			if(dfsCyclic(i, visited, queue)) {
				return new int[0];
			}
		}
		int i = 0;
		int[] result = new int[numCourses];
		while(!queue.isEmpty()) {
			result[i++] = queue.poll();
		}
		return result;
	}
	private boolean dfsCyclic(int cur, int[] v, Queue<Integer> queue) {
		if(v[cur] == 1) return true;
		if(v[cur] == 2) return false;
		v[cur] = 1;
		for(int i = 0; i < graph[cur].size(); i++) {
			if(dfsCyclic((int)graph[cur].get(i), v, queue)) return true;
		}
		v[cur] = 2;
		// if there is no cycle
		queue.offer(cur);
		return false;
	}
	// Method 2: BFS
	public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
		int[] degree = new int[numCourses];
		List<List<Integer>> adjs = new ArrayList<List<Integer>>();
		initialiseGraph(degree, adjs, prerequisites);
		return BFS(degree, adjs);
	}
	private void initialiseGraph(int[] degree, List<List<Integer>> adjs, int[][] prerequistes) {
		int n = degree.length;
		while(n-- > 0) {
			adjs.add(new ArrayList<Integer>());
		}
		for(int[] edge:prerequistes) {
			degree[edge[0]]++;
			adjs.get(edge[1]).add(edge[0]);
		}
	}
	private int[] BFS(int[] degree, List<List<Integer>> adjs) {
		int[] order = new int[degree.length];
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for(int i = 0; i < degree.length; i++) {
			if(degree[i] == 0) queue.offer(i);
		}
		int visited = 0;
		while(!queue.isEmpty()) {
			int from = queue.poll();
			order[visited++] = from;
			for(int to:adjs.get(from)) {
				degree[to]--;
				if(degree[to] == 0) queue.offer(to);
			}
		}
		return visited == degree.length ? order:new int[0];
	}
}
