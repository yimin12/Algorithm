/**
 * 
 */
package williamsNotebook.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import williamsNotebook.common.DirectGraph;

/**
 * @author yimin Huang
 *	
 *		There are a total of n courses you have to take, labeled from 0 to n - 1.
 *		Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *		Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *		For example: 2, [[1,0]]  There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 *					 2, [[1,0],[0,1]] There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *		The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
 * Algorithm Class
 */
public class CourseSchedule {

	// implement it by dfs and topological sort
	public boolean canFinishTopological(int numCourses, int[][] prerequisites) {
		DirectGraph G = new DirectGraph(numCourses, prerequisites);
		DirectedCycle finder = new DirectedCycle(G);
		return !finder.hasCycle();
	}
	
	// Method 1: back Tracking
	public boolean canFinishBackTracking(int numCourses, int[][] prerequisites) {
		HashMap<Integer, List<Integer>> courseDict = new HashMap<Integer, List<Integer>>();
		for(int[] relation:prerequisites) {
			if(courseDict.containsKey(relation[1])) {
				courseDict.get(relation[1]).add(relation[0]);
			} else {
				List<Integer> nextCourses = new LinkedList<Integer>();
				nextCourses.add(relation[0]);
				courseDict.put(relation[1], nextCourses);
			}
		}
		boolean[] path = new boolean[numCourses];
		for(int curCourse = 0; curCourse < numCourses; curCourse++) {
			if(this.isCyclic(curCourse, courseDict, path)) {
				return false;
			}
		}
		return true;
	}
	protected boolean isCyclic(Integer curCourse, HashMap<Integer, List<Integer>> courseDict, boolean[] path) {
		if(path[curCourse]) {
			// come across a previously visited node, i.e. detect the cycle
			return true;
		}
		if(!courseDict.containsKey(curCourse)) return false;
		path[curCourse] = true;
		boolean ret = false;
		for(Integer nextCourseInteger : courseDict.get(curCourse)) {
			ret = this.isCyclic(curCourse, courseDict, path);
			if(ret) break;
		}
		path[curCourse] = false;
		return ret;
	}
	
}

class DirectedCycle{
	private Stack<Integer> cycle;
	private boolean[] marked;
	private boolean[] onStack;
	private int[] edgeTo;
	
	public DirectedCycle(DirectGraph G) {
		marked = new boolean[G.V];
		onStack = new boolean[G.V];
		edgeTo = new int[G.V];
		for(int v = 0; v < G.V; v++) {
			if(!marked[v]) dfs(G, v);
		}
	}
	public boolean hasCycle() {
		return cycle != null;
	}
	
	// DFS
	private void dfs(DirectGraph G, int v) {
		marked[v] = true;
		onStack[v] = true;
		for(int w : G.adj[v]) {
			if(cycle != null) return; // stop when directed cycle found
			else if(!marked[w]) { // recursion when new vertex found
					edgeTo[w] = v;
					dfs(G, w);
			} else if(onStack[w]) {
				cycle = new Stack<Integer>();
				for(int x = v; x != w; w = edgeTo[x]) cycle.push(x);
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}
	
}