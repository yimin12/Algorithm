/**
 * 
 */
package williamsNotebook.common.node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yimin Huang
 *
 * Algorithm Class
 */
public class UnDirectedGraphNode {

	public int val;
	public List<UnDirectedGraphNode> neighbors;
	public UnDirectedGraphNode(int val) {
		this.val = val;
		neighbors = new ArrayList<UnDirectedGraphNode>();
	}
	
}
