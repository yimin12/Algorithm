/**
 * 
 */
package BitOperation;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月17日 下午3:04:05
* Description:
* 	Reverse bits of a 32-bit integer. 
* Note: 
* 	In different programming languages, integers might be implemented differently in terms 
* 	of number of bits, signed, unsigned, etc. However it should not affect your implementation on this problem. 
* 	In java, the type of input is long, but you just need to work on the last 32-bit and consider 
* 	it as an unsigned 32-bit integer.
* Example 1:
	Input: 1234 (0b'00000000000000000000010011010010) 
	Output: 1260388352 (0b'01001011001000000000000000000000)
*/

public class ReverseBits {
	public int reverseI(int num) {
		for(int offset = 0; offset < 16; offset++) {
			int right = (num >> offset) & 1;
			int left = (num >> (31 - offset)) & 1;
			if(left != right) {
//				turn 1 to 0 or 0 to 1 in the corresponding position
				num ^= (1 << offset);
				num ^= (1 << (31 - offset));
			}
		}
		return num;
	}
//	merge sort way of reversing all the bits
	public int reverseII(int num) {
		num = ((num & 0xFFFF0000) >>> 16 | (num & 0x0000FFFF) << 16);
		num = ((num & 0xFF00FF00) >>> 8 | (num & 0x00FF00FF) << 8);
		num = ((num & 0xF0F0F0F0) >>> 4 | (num & 0x0F0F0F0F) << 4);
		num = ((num & 0xCCCCCCCC) >>> 2 | (num & 0x33333333) << 2);
		num = ((num & 0xAAAAAAAA) >>> 1 | (num & 0x55555555) << 1);
		return num;
	}
}
