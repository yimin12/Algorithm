/**
 * 
 */
package DynamicProgramming;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月19日 下午11:48:00
* Description:
* 	Given two strings of alphanumeric characters, determine the minimum number of 
* 	Replace, Delete, and Insert operations needed to transform one string into the other.
* Assumption:
* 	Both string are not null
* Examples:
	string one: “sigh”, string two : “asith”
	the edit distance between one and two is 2 
	(one insert “a” at front then replace “g” with “t”).
*/
public class EditDistance {
	public int editDistance(String one, String two) {
//		Assumption: one, two are not null
//		Again, using distance[i][j] to represent substring(0,i) in one and substring(0,j) in two
		int[][] distance = new int[one.length() + 1][two.length() + 1];
		for(int i = 0; i <= one.length(); i++) {
			for(int j = 0; j <= two.length();j++) {
				if(i==0) {
//					it means that from an empty space comes to j characters's word
//					e.g. from '' to 'abcd', assume j = 4, it should insert 4 times
					distance[i][j] = j;
				} else if (j == 0) {
//					e.g. from 'abcd' to '', assume i = 4; it should delete 4 times
					distance[i][j] = i;
				} else if (one.charAt(i-1) == two.charAt(j-1)) {
//					if the corresponding char are same, do nothing in this step
					distance[i][j] = distance[i-1][j-1];
				} else {
					// the Math.min interface could not compare three values in the same time
					distance[i][j] = Math.min(distance[i-1][j]+1, distance[i][j-1]+1);
//					if this step's value is greater, it is meaning less, we should discard it 
					distance[i][j] = Math.min(distance[i][j], distance[i-1][j-1]);
				}
			}
		}
		return distance[one.length()][two.length()];
	}
}
