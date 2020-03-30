/**
 * 
 */
package williamsNotebook.common.hash;

import williamsNotebook.common.node.ListNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2020��3��26�� ����2:01:21
* Description:
* 	The size of the hash table is not determinate at the very beginning. If the total size of keys is too large 
*   (e.g. size >= capacity / 10), we should double the size of the hash table and rehash every keys. Say you have a 
*   hash table looks like below:
*   size=3, capacity=4
*   The hash function is:
*   int hashcode(int key, int capacity) { return key % capacity; }
*   here we have three numbers, 9, 14 and 21, where 21 and 9 share the same position as they all have the same 
*   hashcode 1 (21 % 4 = 9 % 4 = 1). We store them in the hash table by linked list.
*   rehashing this hash table, double the capacity, you will get:
*   size=3, capacity=8
*   index:   0    1    2    3     4    5    6   7
    hash : [null, 9, null, null, null, 21, 14, null]
    Given the original hash table, return the new hash table after rehashing .
*/

public class MyRehashing {
	
	/**
     * @param hashTable: A list of The first node of linked list
     * @return: A list of The first node of linked list which have twice size
     */
	public ListNode[] rehashing(ListNode[] hashTable) {
		if(hashTable == null || hashTable.length == 0) {
			return hashTable;
		}
		int capacity = hashTable.length;
		int newCapacity = 2 * capacity;
		ListNode[] newHashTable = new ListNode[newCapacity];
		for(int i = 0; i < capacity; i++) {
			ListNode node = hashTable[i];
			while(node != null) {
				int code = hashCode(node.key, newCapacity);
				insertToHashTable(newHashTable, code, node.key);
				node = node.next;
			}
		}
		return newHashTable;
	}
	public int hashCode(int key, int capacity) {
		int hash;
		// case 1.0 if the key is the negative value
		if(key < 0) {
			hash = (key % capacity + capacity) % capacity;
		} else {
			hash = key % capacity;
		}
		return hash;
	}
	private void insertToHashTable(ListNode[] hashTable, int code, int value) {
		if(code < hashTable.length) {
			ListNode node = hashTable[code];
			if(node == null) hashTable[code] = node = new ListNode(value);
			else {
				while(node.next != null) {
					node = node.next;
				}
				node.next = new ListNode(value);
			}
		}
	}
	public static void main(String[] args) {
		MyRehashing s = new MyRehashing();
        ListNode[] lsn = new ListNode[3];
        lsn[0] = null;
        lsn[1] = null;
        lsn[2] = new ListNode(29);
        lsn[2].next = new ListNode(5);
        ListNode[] newLsn = s.rehashing(lsn);
	}
}
