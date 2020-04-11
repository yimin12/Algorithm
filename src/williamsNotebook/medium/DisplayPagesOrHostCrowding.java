/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月10日 下午1:41:48
* Description:
* 	Host Crowding
* 	You’re given an array of CSV strings representing search results. Results are sorted by a score initially.
* 	A given  host may several listings that show up in these results; 
* 	a) Suppose we want to show 12 results per page,
* 	b) We don’t want the same host to dominate the results. 
* 		Write a function that will reorder the list so that 
* 		a host shows up at most once on a page if possible, but otherwise
* 	c) preserves the ordering.
* 	Your program should return the new array and print out the results in blocks representing the pages.
* 
*/
public class DisplayPagesOrHostCrowding {

	public List<String> displayPages(List<String> input, int pageSize){
		List<String> res = new ArrayList<String>();
		if(input == null || input.size() == 0) {
			return res;
		}
		HashSet<String>  visited = new HashSet<String>();
		Iterator<String> iterator = input.iterator();
		boolean reachEnd = false;
		while(iterator.hasNext()) {
			String curr = iterator.next();
			String hostId = curr.split(",")[0];
			if(!visited.contains(hostId) || reachEnd) {
				res.add(curr);
				visited.add(hostId);
				iterator.remove();
			}
			if(visited.size() == pageSize) {
				visited.clear();
				reachEnd = false;
				if(!input.isEmpty()) {
					res.add(" ");
				}
				iterator = input.iterator();
			}
			if(!iterator.hasNext()) {
				iterator = input.iterator();
				reachEnd = true;
			}
		}
		return res;
	}
	
	// Version 2:
	private static final int CAPACITY = 12;
	public void displayPage(List<String> input) {
		if(input == null || input.size() == 0) return;
		Iterator<String> iterator = input.iterator();
		Set<String> set = new HashSet<String>();
		StringBuilder sb = new StringBuilder();
		int pageNum = 1;
		sb.append("page" + pageNum + "\n\n");
		while(iterator.hasNext()) {
			String string = iterator.next();
			String pageId = string.split(",")[0];
			if(!set.contains(pageId)) {
				sb.append(string).append("\n");
				set.add(pageId);
				iterator.remove();
			}
			if(set.size() == CAPACITY || !iterator.hasNext()) {
				set.clear();
				iterator = input.iterator();
				if(iterator.hasNext()) {
					pageNum++;
					sb.append("\nPage" + pageNum + "\n\n");
				}
			}
		}
		System.out.println(sb.toString());
	}
	public static void main(String[] args) {
		DisplayPagesOrHostCrowding solution = new DisplayPagesOrHostCrowding();
		 String[] data = {
		            "host_id,listing_id,score,city",
		            "1,28,300.1,San Francisco",
		            "4,5,209.1,San Francisco",
		            "20,7,208.1,San Francisco",
		            "23,8,207.1,San Francisco",
		            "16,10,206.1,Oakland",
		            "20,16,205.1,San Francisco",
		            "1,31,204.6,San Francisco",
		            "6,29,204.1,San Francisco",
		            "7,20,203.1,San Francisco",
		            "8,21,202.1,San Francisco",
		            "2,18,201.1,San Francisco",
		            "2,30,200.1,San Francisco",
		            "15,27,109.1,Oakland",
		            "10,13,108.1,Oakland",
		            "11,26,107.1,Oakland",
		            "12,9,106.1,Oakland",
		            "13,1,105.1,Oakland",
		            "22,17,104.1,Oakland",
		            "1,2,103.1,Oakland",
		            "28,24,102.1,Oakland",
		            "18,14,11.1,San Jose",
		            "6,25,10.1,Oakland",
		            "19,15,9.1,San Jose",
		            "3,19,8.1,San Jose",
		            "3,11,7.1,Oakland",
		            "27,12,6.1,Oakland",
		            "1,3,5.1,Oakland",
		            "25,4,4.1,San Jose",
		            "5,6,3.1,San Jose",
		            "29,22,2.1,San Jose",
		            "30,23,1.1,San Jose"
		        };
		 List<String> input = new LinkedList<String>(Arrays.asList(data));
		 List<String> res = solution.displayPages(input, 12);
		 for(String r : res) {
			 System.out.println(r);
		 }
	}
}
