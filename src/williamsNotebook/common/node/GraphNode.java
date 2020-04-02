/**
 * 
 */
package williamsNotebook.common.node;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��11�� ����10:39:12
* Description:
*/

public class GraphNode {
	public int key;
	public List<GraphNode> neighbours;
	public GraphNode(int key) {
		this.key = key;
		this.neighbours = new ArrayList<GraphNode>();
	}
}
