/**
 * 
 */
package williamsNotebook.common.node;

import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午2:41:58
* Description:
*/

public class KnaryTreeNode {
	
	public int val;
	public List<KnaryTreeNode> children;
	public KnaryTreeNode(int _val, List<KnaryTreeNode> _children){
		val = _val;
		children = _children;
	}
}
