/**
 * 
 */
package williamsNotebook.common.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yimin Huang
 *
 * Algorithm Class
 */
public class GraphNode {

	public int val;
	public List<GraphNode> neighbors;
	public GraphNode(int val) {
		this.val = val;
		neighbors = new ArrayList<GraphNode>();
	}
	
}
