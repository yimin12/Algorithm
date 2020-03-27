/**
 * 
 */
package williamsNotebook.common.classified;

import java.util.HashMap;
import java.util.TreeMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月26日 下午5:07:02
* Description:
* 	Create a timebased key-value store class TimeMap, that supports two operations.
* 	set(string key, string value, int timestamp)
* 		Stores the key and value, along with the given timestamp.
* 	get(string key, int timestamp)
* 		Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
* 		If there are multiple such values, it returns the one with the largest timestamp_prev
* 		If there are no values, it returns the empty string ("").
* 	
inputs = 
["TimeMap","set","get","get","set","get","get"]
, inputs = 
[[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
Output: 
[null,null,"bar","bar",null,"bar2","bar2"]
Explanation: 

TimeMap kv;   
kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
kv.get("foo", 1);  // output "bar"   
kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
kv.set("foo", "bar2", 4);   
kv.get("foo", 4); // output "bar2"   
kv.get("foo", 5); //output "bar2"
*/
// Method 1: HashMap + TreeMap
// Time Complexity: O(1) for each set operation, and O(logN) for eachgetoperation, where N is the number of entries in theTimeMap.
// Space Complexity: O(N).
public class TimeMap {

	// we will use treeMap to improve efficiency, param1: key, param2: timestamp, param3: value 
	// The reason for designing this is that firstly grouped by key then by time
	HashMap<String, TreeMap<Integer, String>> timeMap;
	public TimeMap() {
		timeMap = new HashMap<String, TreeMap<Integer,String>>();
	}
	
	public void set(String key, String value, int timestamp) {
		if(timeMap.containsKey(key)) {
			timeMap.put(key, new TreeMap<Integer, String>());
		} 
		timeMap.get(key).put(timestamp, value);
	}
	
	public String get(String key, int timestamp) {
		if(!timeMap.containsKey(key)) {
			return "";
		}
		TreeMap<Integer, String> tree = timeMap.get(key);
		// get the greatest no larger than given input
		Integer time = tree.floorKey(timestamp);
		if(time == null) {
			return "";
		}
		return tree.get(time);
	}
}
