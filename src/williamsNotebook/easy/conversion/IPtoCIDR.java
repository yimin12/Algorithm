/**
 * 
 */
package williamsNotebook.easy.conversion;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午11:01:17
* Description:
* 	Given a start IP addressipand a number of ips we need to covern, return a representation 
*   of the range as a list (of smallest possible length) of CIDR blocks.
* 	A CIDR block is a string consisting of an IP, followed by a slash, and then the prefix length. For example: "123.45.67.89/20". 
* 	That prefix length "20" represents the number of common prefix bits in the specified range.
* 	Input:
	 ip = "255.0.0.7", n = 10
	
	Output:
	 ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
	
	Explanation:
	
	The initial ip address, when converted to binary, looks like this (spaces added for clarity):
	255.0.0.7 -
	>
	 11111111 00000000 00000000 00000111
	The address "255.0.0.7/32" specifies all addresses with a common prefix of 32 bits to the given address,
	ie. just this one address.
	
	The address "255.0.0.8/29" specifies all addresses with a common prefix of 29 bits to the given address:
	255.0.0.8 -
	>
	 11111111 00000000 00000000 00001000
	Addresses with common prefix of 29 bits are:
	11111111 00000000 00000000 00001000
	11111111 00000000 00000000 00001001
	11111111 00000000 00000000 00001010
	11111111 00000000 00000000 00001011
	11111111 00000000 00000000 00001100
	11111111 00000000 00000000 00001101
	11111111 00000000 00000000 00001110
	11111111 00000000 00000000 00001111
	
	The address "255.0.0.16/32" specifies all addresses with a common prefix of 32 bits to the given address,
	ie. just 11111111 00000000 00000000 00010000.
	
	In total, the answer specifies the range of 10 ips starting with the address 255.0.0.7 .
	
	There were other representations, such as:
	["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
	but our answer was the shortest possible.
	
	Also note that a representation beginning with say, "255.0.0.7/30" would be incorrect,
	because it includes addresses like 255.0.0.4 = 11111111 00000000 00000000 00000100 
	that are outside the specified range.
*/
public class IPtoCIDR {
	
	
//	This question was not easy for me even though it's labeled as easy. The solution may be straightforward 
//	(just try values in order), but the implementation has multiple parts and a lot of places to get stuck. 
//	There are a lot of ways to implement it, but these are the steps I used.
//
//	The problem is asking to get IP Addresses starting with the given start IP Address that cover n IPs. 
//	So we will take all the ips we can get at each IP Address (at each step this is either the maximum possible 
//	(2^number of trailing bits) or if 2^number of trailing bits will make us pick more than n it is the largest 
//	(2^amount) that won't pick over n.
//
//	We get our next IP by adding the value of 2^bits we took in the result for the current IP Address value. 
//	The reason this works, is that we advance out of the amount of values we added to the result's range. 
//	(for example we found 2^3 values in the iteration so we add 8 because that's the next ip address that we didn't proccess).
//
//	For the problems example:
//	We start at
//	255.0.0.7 -> 11111111 00000000 00000000 00000111 (IP form -> Integer form) n=10
//	We get "255.0.0.7/32" because there are 0 leading zeroes, the range is 32- 0 bit shifted=32. We used 1 so n = 9
//	255.0.0.7 + 2^0 = 255.0.0.8 255.0.0.8 -> 11111111 00000000 00000000 00001000
//	We get "255.0.0.8/29". The number of leading zeros is 3 so we can use 2^3 digits from this range. We shifted 1 -> 2 -> 4 -> 8 three times so the range is 32-3 bits shifted=29. We used 2^3 =8 so n = 1.
//	255.0.0.8 + 2^3 = 255.0.0.16
//	255.0.0.16->11111111 00000000 00000000 00010000
//	We get "255.0.0.16/32".Theres 4 leading zero's but we only have n=1 remaining so we can only take 2^0 (maximum power of two less than n and less than 2^leading zeroes). the range is 32- 0 bit shifted =32. We used 2^0 so n=0.
//	255.0.0.16+2^0 = 255.0.0.17 but n=0 so we're done
	public List<String> ipToCIDR(String ip, int n){
		int cur = toInt(ip);
		List<String> res = new ArrayList<String>();
		while(n > 0) {
			int maxBits = Integer.numberOfTrailingZeros(cur);
			int maxAmount = 1 << maxBits;
			int bitVal = 1;
			int count = 0;
			while(bitVal < n && count < maxBits) {
				bitVal <<= 1;
				count++;
			}
			if(bitVal > n) {
				bitVal >>= 1;
				count--;
			}
			res.add(toString(cur, 32 - count));
			n -= bitVal;
			cur += bitVal;
		}
		return res;
	}
	private String toString(int number, int range) {
		final int WORD_SIZE = 8; // convert 8 digits into an integer
		StringBuilder sb = new StringBuilder();
		for(int i = 3; i >= 0; i--) {
			sb.append(Integer.toString(((number >> (i*WORD_SIZE))&255)));
			sb.append(",");
		}
		sb.setLength(sb.length() - 1);
		sb.append("/");
		sb.append(Integer.toString(range));
		return sb.toString();
	}
	private int toInt(String ip) {
		String[] sep = ip.split("\\.");
		int sum = 0;
		for(int i = 0; i <sep.length; i++) {
			sum *= 256;
			sum += Integer.parseInt(sep[i]);
		}
		return sum;
	}
}
