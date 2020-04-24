/**
 * 
 */
package practise.IOtest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午1:40:47
* Description:
*/
public class TestSerialization {

	public static void main(String[] args) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try(ObjectOutputStream output = new ObjectOutputStream(buffer)){
        	output.writeInt(12345);
        	output.writeUTF("hello");
        	output.writeObject(Double.valueOf(123.456));
        }
		System.out.println(Arrays.toString(buffer.toByteArray()));
	}
}
