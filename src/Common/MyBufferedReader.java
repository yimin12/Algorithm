package Common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月22日 下午4:26:36
* Description:
* 	Implement your own way to implement the bufferedReader, must contain basic function like nextLine()
  Assumption: no assumption
*/
public class MyBufferedReader {
	private FileInputStream in;
	private StringBuffer buffer;
	public MyBufferedReader(FileInputStream in) {
		this.in = in;
		buffer = new StringBuffer();
	}
	public String nextLine() throws IOException{
		while(true) {
			int c = in.read();
			if(c==-1||c=='\n') {
				break;
			}
			buffer.append((char)c);
		}
		String output = buffer.toString();
		return output;
	}
	public static void main(String args[]) throws IOException {
//		FileInputStream input = null;
//		String addr = "/Users/huangyimin/Downloads/mid.docx";
//		try {
//			input = new FileInputStream(addr);
//			MyBufferedReader myBufferedReader = new MyBufferedReader(input);
//			String line = myBufferedReader.nextLine();
//			System.out.println(line);
//		} finally {
//			input.close();
//		}
		ArrayList<Integer> a1 = new ArrayList<Integer>();
		ArrayList<Integer> a2 = new ArrayList<Integer>();
		Class clz1 = a1.getClass();
		Class clz2 = a2.getClass();
		// In jvm, it use singleton to design the container
		System.out.println(clz1==clz2);
		System.out.println("class of a1: " + clz1 + "\nclass of a2: " + clz2);
		// use reflection to inject a character to the corresponding integer arraylist (it will come up with risk)
		// this way you would not encounter compile or runtime problem
		try {
			a2.getClass().getMethod("add", Object.class).invoke(a2, "a");
			System.out.println(a2.toString());
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}
}
