/**
 * 
 */
package williamsNotebook.common.node;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午2:41:08
* Description:
*/

public class SegmentTreeNode {
	
	public int start, end;
	public SegmentTreeNode left,right;
	public SegmentTreeNode(int start, int end) {
		this.start = start; this.end = end;
		this.left = this.right = null;
	}
	
}
