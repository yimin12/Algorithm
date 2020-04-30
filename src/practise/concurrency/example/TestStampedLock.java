/**
 * 
 */
package practise.concurrency.example;

import java.util.concurrent.locks.StampedLock;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月21日 下午3:10:42
* Description:
*  要进一步提升并发执行效率，Java 8引入了新的读写锁：StampedLock。 
*  StampedLock和ReadWriteLock相比，改进之处在于：读的过程中也允许获取写锁后写入！这样一来，我们读的数据就可能不一致，所以，需要一点额外的代码来判断读的过程中是否有写入，这种读锁是一种乐观锁。
*/
public class TestStampedLock {

	private final StampedLock stampedLock = new StampedLock();
	private double x, y;
	
	public void move(double deltaX, double deltaY) {
		long stamp = stampedLock.writeLock(); // get the writing lock
		try {
			x += deltaX;
			y += deltaY;
		} finally {
			stampedLock.unlockWrite(stamp); // release lock
		}
	}
	public double distanceFromOriginal() {
		long stamp = stampedLock.tryOptimisticRead(); // get an optimized reading lock
		// 注意下面两行代码不是原子操作
        // 假设x,y = (100,200)
		double currentX = x;
		// 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
		double currentY = y;
		// 此处已读取到y，如果没有写入，读取是正确的(100,200)
        // 如果有写入，读取是错误的, e.g.(100,400)
		if(!stampedLock.validate(stamp)) { // 检查乐观读锁后是否有其他写锁发生
			stamp = stampedLock.readLock();
			try {
				// updated
				currentX = x;
				currentY = y;
			} finally {
				stampedLock.unlockRead(stamp); // 释放悲观读锁
			}
		}
		return Math.sqrt(currentX * currentX + currentY * currentY);
	}
}
