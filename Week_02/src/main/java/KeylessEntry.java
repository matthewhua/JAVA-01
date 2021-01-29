import java.util.HashMap;
import java.util.Map;

/**
 * @author Matthew
 * @date 2021-01-22 19:56
 */
public class KeylessEntry {
    static class Key{
        Integer id;
        Key(Integer id){
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }


        //以hashCode 为值来判断是不是同一个对象
        @Override
        public boolean equals(Object obj) {
            boolean response = false;
            if (obj instanceof Key){
                response = (((Key)obj).id).equals(this.id);
            }
            return response;
        }
    }

    public static void main(String[] args) {
        Map m = new HashMap();
        while (true){
            for (int i = 0; i < 100000; i++) {
                if (!m.containsKey(new Key(i)))
                {
                    m.put(new Key(i), "Number:" + i);
                }
            }
            System.out.println("m.size : " + m.size());
        }
    }
}
