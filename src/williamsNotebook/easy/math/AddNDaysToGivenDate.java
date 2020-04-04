/**
 * 
 */
package williamsNotebook.easy.math;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 下午5:05:24
* Description:
* 	date as string yyyyMMdd, positive integer 'n' delta in days, add n days to the date and return the correct date.
* 	date assume it's valid, n could be arbitrary big number, return date in String yyyyMMdd
* Example:
* 	Input: 20190212, 2
* 	Output: 20190214
*/
public class AddNDaysToGivenDate {

	// Find the values of day and month from offset of result year
	static int m2, d2;
	boolean isLeap(int y) {
		if(y % 100 != 0 && y % 4 == 0 || y % 400 == 0) {
			return true;
		}
		return false;
	}
	// given a date, return number of days elapsed from the beginning of the current
	// year (1st jan)
	int offsetDays(int d, int m, int y){
		int offset = d;
		if(m - 1 == 11) 
            offset += 335; 
        if(m - 1 == 10) 
            offset += 304; 
        if(m - 1 == 9) 
            offset += 273; 
        if(m - 1 == 8) 
            offset += 243; 
        if(m - 1 == 7) 
            offset += 212; 
        if(m - 1 == 6) 
            offset += 181; 
        if(m - 1 == 5) 
            offset += 151; 
        if(m - 1 == 4) 
            offset += 120; 
        if(m - 1 == 3) 
            offset += 90; 
        if(m - 1 == 2) 
            offset += 59; 
        if(m - 1 == 1) 
            offset += 31; 
        if(isLeap(y) && m > 2) {
        	offset += 1;
        }
        return offset;
	}
	
	// Given a year and days elapsed in it, finds date by storing results in d and m
	void revoffsetDays(int offset, int y) {
		int []month={ 0, 31, 28, 31, 30, 31, 30, 
              31, 31, 30, 31, 30, 31 };
		if(isLeap(y)) month[2] = 29;
		int i;
		for(i = 1; i <= 12; i++) {
			if(offset <= month[i]) {
				break;
			}
			offset -= month[i];
		}
		d2 = offset;
		m2 = i;
	}
	void addDays(int d1, int m1, int y1, int x) {
		int offset1 = offsetDays(d1, m1, y1);
		int remDays = isLeap(y1) ? (366 - offset1) : (365 - offset1);
		int y2 , offset2 = 0;
		if(x <= remDays) {
			y2 = y1;
			offset2 += x;
		} else {
			x -= remDays;
			y2 = y1 + 1;
			int y2days = isLeap(y2) ? 366 : 365;
			while(x >= y2days) {
				x -= y2days;
				y2++;
				y2days = isLeap(y2) ? 366 : 365;
			}
			offset2 = x;
		}
		revoffsetDays(offset2, y2);
		System.out.println("d2 = " + d2 + ", m2 " + m2 + ",y2 = " + y2);
	}
	public static void main(String[] args) {
		int d = 14, m = 3, y = 2015;
		int x = 366;
		AddNDaysToGivenDate solution = new AddNDaysToGivenDate();
		solution.addDays(d, m, y, x);
		
	}
}
