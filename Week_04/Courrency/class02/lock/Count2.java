package class02.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Matthew
 * @date 2021-02-06 14:21
 */
public class Count2 {

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();


    //读锁
    public void get()
    {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " get end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
    }

    //写锁
    public void put() {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " put begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " put end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

}
