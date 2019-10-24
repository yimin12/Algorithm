/**
 * 
 */
package String;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月15日 下午4:16:17
* Description:
* 	Given a string in compressed form, the compress it to the original string. The adjacent
* 	repeated characters in the original string are compressed to have the character followed by the number
* 	of repeated occurance
* Assumption: 
*  	1.The string is not null 2. The characters used in the original string are guaranteed to be 'a'-'z'
*  	3.There are no adjacent repeated characters with length > 9
* Examples:
* 	"a1c0c2" --> "acc"
*/

public class DecompressStringII {
//	Method 1: "in-place"
//	When we say in place, it usually means the input is a long enough char array, and the original string only
//	occupies part of the array starting from index 0, and usually the length to represent the original 
//	string is given
	public String decompressI(String input) {
		if(input.isEmpty()) {
			return input;
		}
		char[] array = input.toCharArray();
//		we need to handle the "a0", "a2"(the decoded string is shorter) and "a3", "a4" ... case (the decode
//		string is longer) in two pass to avoid conflict, since the encoding of the two cases require different
//		directions
		return decodeLong(array, decodeShort(array));
	}
//	return the length of the decoded string, only cares about "a0", "a1", "a2", AKA the decoded is equal or shorter
	private int decodeShort(char[] input) {
//		since the decoded string is shorter, we should do the decoding work from left to right direction
		int end = 0;
		for(int i = 0; i < input.length; i++) {
			int digit = getDigit(input[i+1]);
			if(digit >= 0 && digit <= 2) {
				for(int j = 0; i < digit; j++) {
					input[end++] = input[i];
				}
			} else {
//				we don't handle the longer decoded string here
				input[end++] = input[i];
				input[end++] = input[i+1];
			}
		}
		return end;
	}
	private String decodeLong(char[] input, int length) {
//	we need to calculate the required length
		int newLength = length;
		for(int i = 0; i < length; i++) {
			int digit = getDigit(input[i]);
			if(digit > 2 && digit <= 9) {
				newLength += digit - 2;
			}
		}
//		Notice: if it is required to do this in place, usually the input array is a sufficient large one, 
//		you will not need to allocate a new array. This solution only for demonstration
		char[] result = new char[newLength];
		int end = newLength - 1;
		for(int i = length - 1; i >= 0; i--) {
			int digit = getDigit(input[i]);
			if(digit > 2 && digit <= 9) {
				i--;
				for(int j = 0; j < digit; j++) {
					result[end--] = input[i];
				}
			} else {
//				we already take care the shorter cases, "a1" in previous pass. 
//				we can leaveas it is, e.g. the input could be "abc2"
				result[end--] = input[i];
			}
		} 
		return new String(result);
	}
	private int getDigit(char digit) {
		return digit - '0';
	}
	
//	Method 2: using StringBuilder to help
	public String decompressII(String input) {
//		Assumption: input is not null
		char[] array = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < array.length; i++) {
			char ch = array[i++];
			int count = array[i] - '0';
			for(int c = 0; c < count; c++) {
				sb.append(ch);
			}
		}
		return sb.toString();
	}
}
