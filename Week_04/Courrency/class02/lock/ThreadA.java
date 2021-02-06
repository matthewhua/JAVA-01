package class02.lock;

/**
 * @author Matthew
 * @date 2021-02-06 14:44
 */
public class ThreadA extends Thread{
    private Count3 count3;

    public ThreadA(Count3 count3) {
        this.count3 = count3;
    }

    @Override
    public void run()
    {
        count3.add();
    }
}
