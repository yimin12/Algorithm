/**
 * 
 */
package williamsNotebook.medium;

import java.io.IOException;
import java.io.Reader;

/**
 * @author yimin Huang
 *	
 *	The API: int read4(char *buf) reads 4 characters at a time from a file.
 *	The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 *	By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 *
 * Algorithm Class
 */
public class ReadNCharacter extends Reader{

	@Override
	public int read(char[] cbuf, int off, int n) throws IOException {
		char[] buffer = new char[4];
		int numRead = 0;
		boolean eof = false;
		while(!eof && numRead < n) {
			int size = read(buffer);
			if(size < 4) eof = true;
			int bytes = Math.min(n - numRead, size);
			System.arraycopy(buffer, 0, buffer, numRead, bytes);
			numRead += bytes;
		}
		return numRead;
	}

	private char[] buffer = new char[4];
	private int offset = 0, bufsize = 0;
	public int readMultiple(char[] buf, int n) throws IOException {
		int numRead = 0;
		boolean eof = false;
		while(!eof && numRead < n) {
			if(bufsize == 0) {
				bufsize = read(buffer);
				eof = bufsize < 4;
			}
			int bytes = Math.min(n - numRead, bufsize);
			System.arraycopy(buffer, offset, buf, numRead, bytes);
			offset = (offset + bytes) % 4;
			bufsize -= bytes;
			numRead += bytes;
		}
		return numRead;
	}
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	private int read4(char[] buffer) {
		return 0;
	}

}
