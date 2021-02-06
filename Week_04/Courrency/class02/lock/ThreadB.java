package class02.lock;

/**
 * @author Matthew
 * @date 2021-02-06 14:45
 */
public class ThreadB extends Thread
{
    private Count3 count3;

    public ThreadB(Count3 count3) {
        this.count3 = count3;
    }

    public void run() {
        count3.lockMethod();
    }
}
