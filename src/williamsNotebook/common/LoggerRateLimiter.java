/**
 * 
 */
package williamsNotebook.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;

import williamsNotebook.common.linkedList.LinkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月20日 下午2:14:09
* Description:
* 	Design a logger system that receive stream of messages along with its timestamps, each message should 
* 	be printed if and only if it isnot printed in the last 10 seconds.
* 	Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, 
* 	otherwise returns false.
* Logger logger = new Logger();
* // logging string "foo" at timestamp 1
	logger.shouldPrintMessage(1, "foo"); returns true; 
	
	// logging string "bar" at timestamp 2
	logger.shouldPrintMessage(2,"bar"); returns true;
	
	// logging string "foo" at timestamp 3
	logger.shouldPrintMessage(3,"foo"); returns false;
	
	// logging string "bar" at timestamp 8
	logger.shouldPrintMessage(8,"bar"); returns false;
	
	// logging string "foo" at timestamp 10
	logger.shouldPrintMessage(10,"foo"); returns false;
	
	// logging string "foo" at timestamp 11
	logger.shouldPrintMessage(11,"foo"); returns true;
* 
*/
public class LoggerRateLimiter {

	// Naive implementation would be using HashMap, with key of log message and value of timestamp. It would pass the OJ, 
	// there are concerns on the capacity, thinking of it as a cache, then certain eviction policy needed to remove obsolete record
	// to avoid exploding the data structure in memory
	// We recommend to use LinkedHashMap, it is good for the eviction policy, actually, LinkedHashMap can be used in LRU	
	
	public LoggerRateLimiter() {
		LoggerNaive logger1 = new LoggerNaive();
		LoggerLinkedHashMap logger2 = new LoggerLinkedHashMap();
		LoggerQueue logger3 = new LoggerQueue();
	}
	public static void main(String[] args) {
		LoggerRateLimiter solution = new LoggerRateLimiter();
	}
	
}
//Method 1: Naive Solution, assume that the volumn of message is not large, if the numbers of different message is large, it will break
class LoggerNaive{
	
	Map<String, Integer> logs;
	public LoggerNaive() {
		this.logs = new HashMap<String, Integer>();
	}
	// return true if the message should be printed in the given timestamp, otherwise return false;
	public boolean shoudlPrintMessage(int timestamp, String message) {
		if(logs.containsKey(message)) {
			if(timestamp - logs.get(message) >= 10) {
				logs.put(message, timestamp);
				return true;
			} else {
				return false;
			}
		} else {
			logs.put(message, timestamp);
			return true;
		}
	}
}
// Method 2: Use LinkedHashMap
class LoggerLinkedHashMap{
	
	public Map<String, Integer> map;
	int lastSecond = 0;
	public LoggerLinkedHashMap() {
		// default capacity is 100 and the load factor is 0.6f, and choose the access order
		this.map = new LinkedHashMap<String, Integer>(100, 0.6f, true){
			private static final long serialVersionUID = 1L;
			// eviction policy
			protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
				return lastSecond - eldest.getValue() > 10;
			}
		};
	}
	public boolean shouldPrintMessage(int timestamp, String message) {
		lastSecond = timestamp;
		if(!map.containsKey(message) || timestamp - map.get(message) >= 10) {
			map.put(message, timestamp);
			return true;
		}
		return false;
	}
}
// Method 3: Use Queue
class LoggerQueue{
	// time : O(n)
	class TimeMsg{
		int timestamp;
		String msg;
		public TimeMsg(int timestamp, String msg) {
			this.timestamp = timestamp;
			this.msg = msg;
		}
	}
	// Initialize the data structure 
	private static final int MAX_TIME_WINDOW = 10;
	Queue<TimeMsg> msgQueue;
	@SuppressWarnings("unchecked")
	public LoggerQueue(){
		this.msgQueue = (Queue<TimeMsg>) new LinkedList();
	}
	public boolean shoudPrintMessage(int timestamp, String msg) {
		// step 1: evict all the valid message, all the invalid message remain in the queue
		while(!msgQueue.isEmpty() && timestamp - msgQueue.peek().timestamp >= MAX_TIME_WINDOW) {
			msgQueue.poll();
		}
		// step 2: search the name in black list, if detect, return false;
		Iterator<TimeMsg> iterator = msgQueue.iterator();
		while(iterator.hasNext()) {
			TimeMsg tm = iterator.next();
			if(tm.msg.equals(msg)) return false;
		}
		msgQueue.offer(new TimeMsg(timestamp, msg));
		return true;
	}
}

