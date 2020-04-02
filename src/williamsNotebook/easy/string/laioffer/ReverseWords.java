/**
 * 
 */
package williamsNotebook.easy.string.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��14�� ����11:03:36
* Description:
* 	Reverse the words in a sentence.
* Assumptions:
	Words are separated by single space
	There are no heading or tailing white spaces
  Examples:
	��I love Google�� �� ��Google love I��
  Corner Cases:
	If the given string is null, we do not need to do anything.
*/
public class ReverseWords {
//	Assumption: 1) The words are separated by one space character
//				2) There are no leading or trailing spaces
//				3) Input is not null
	public String reverseWords(String input) {
//		try to convert it to char array and solve it in-place
		char[] array = input.toCharArray();
//		reverse the whole char array
		reverse(array, 0, array.length - 1);
		int start = 0;
//		reverse each of the word
		for(int i = 0; i < array.length; i++) {
//			the start index of word
			if(array[i] != ' ' && (i==0||array[i - 1] == ' ')) {
				start = i;
			}
//			the end index of word
			if(array[i] != ' ' && (i == array.length - 1 || array[i + 1] == ' ')) {
				reverse(array, start, i);
			}
		}
		return new String(array);
	}
	private void reverse(char[] array, int left, int right) {
		while(left < right) {
			char temp = array[left];
			array[left] = array[right];
			array[right] = temp;
			right--;
			left++;
		}
	}
}
