/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月31日 下午12:46:39
* Description:
* 	There are a total of n courses you have to take, labeled from0ton-1.
* 	Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair:[0,1]
* 	Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
* Examples:
*	Input: 2, [[1,0]]   Output: true
*	Input: 2, [[1,0],[0,1]]  Output: false
*   You may assume that there are no duplicate edges in the input prerequisites.
*/


public class CourseSchedule {

	// 此题考虑课程依赖关系是否能满足，其实抽象来看，课程的依赖关系其实是有向图的边edge，那么问题的内核其实就是这个有向图是否有环，如果无环，
	// 则依赖关系可以成立，也就是DAG （有向无环图）。判定是否为DAG，则可以用拓扑排序Topological Sorting
	// Method 1: Topological Sorting - DFS - ArrayList
	private ArrayList[] graph;
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		graph = new ArrayList[numCourses];
		// states: 0 = unknown, 1 = visiting, 2 = visited;
		int[] visit = new int[numCourses];
		for(int i = 0; i < numCourses; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		for(int[] p : prerequisites) {
			graph[p[0]].add(graph[p[1]]);
		}
		for(int i = 0; i < numCourses; i++) {
			if(dfsCyclic(i, visit)) return false; // detect cycle
		}
		return true;
	}
	private boolean dfsCyclic(int cur, int[] v) {
		if(v[cur] == 1) return true; // traversing
		if(v[cur] == 2) return false; // has been visited before
		v[cur] = 1;
		for(int i = 0; i < graph[cur].size(); i++) {
			if(dfsCyclic((int) graph[cur].get(i), v)) return true;
		}
		v[cur] = 2;
		return false;
	}
	// Method 2: Topological Sort -- OOD Style - DFS
	private class Course{
		boolean visited = false; // if being visited
		boolean tested = false; // tested if cyclic
		List<Course> pre = new ArrayList<Course>();
		public void add(Course c) {
			pre.add(c);
		}
	}
	public boolean canFinishI(int numCourses, int[][] prerequisites) {
		Course[] courses = new Course[numCourses];
		for(int i = 0; i < numCourses; i++) {
			courses[i] = new Course();
		}
		for(int i = 0; i < prerequisites.length;i++) {
			courses[prerequisites[i][0]].add(courses[prerequisites[i][1]]);
		}
		for(int i = 0; i < numCourses; i++) {
			if(isCyclicI(courses[i])) return false;
		}
		return true;
	}
	private boolean isCyclicI(Course course) {
		if(course.tested) return false;
		if(course.visited) return true;
		course.visited = true;
		for(Course c : course.pre) {
			if(isCyclicI(c)) return true;
		}
		course.tested = true;
		return false;
	}
	// BFS
	public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
		List[] edges = new ArrayList[numCourses];
		// if course A should have prerequisites course B, A's degree will be higher.
		// for this record, we can know where should we start to generate
		int[] degree = new int[numCourses];
		for(int i = 0; i < numCourses; i++) {
			edges[i] = new ArrayList<Integer>(); 
		}
		for(int i = 0; i < prerequisites.length; i++) {
			degree[prerequisites[i][0]]++;
			// the sequence we should follow for the courses based on the degree
			edges[prerequisites[i][1]].add(prerequisites[i][0]);
		}
		Queue queue = new LinkedList();
		for(int i = 0; i < degree.length; i++) {
			if(degree[i] == 0) {
				queue.add(i);
			}
		}
		// all the generating node represent the course about to learn. These courses should be with 0 of degree
		// 0 degree means it is valid to take that course
		int count = 0;
		while(!queue.isEmpty()) {
			int course = (int)queue.poll();
			count++;
			int n = edges[course].size();
			for(int i = 0; i < n; i++) {
				int pointer = (int)edges[course].get(i);
				degree[pointer]--;
				if(degree[pointer] == 0) {
					queue.add(pointer);
				}
			}
		}
		// if there exist cyclic loop, count number should be smaller than numsCourses. And part of degree are not 0
		return count == numCourses;
 	}
}
