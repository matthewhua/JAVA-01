
package class02.atomic;

public class Count {

	//线程不安全
    private int num = 0;

    public int add() {
        return num++;
    }

    public int getNum() {
        return num;
    }
}
