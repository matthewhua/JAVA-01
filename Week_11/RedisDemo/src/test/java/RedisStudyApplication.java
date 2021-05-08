import com.matthew.medis.RedisCounter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest(classes = RedisStudyApplication.class)
@Slf4j
public class RedisStudyApplication {
    @Autowired
    private RedisCounter counter;

    @Test
    void test() throws InterruptedException {
        //初始化库存
        int stockAll = 1000;
        log.info("初始化库存数量，{}", stockAll);
        Thread.sleep(1000);
        AtomicInteger cnt = new AtomicInteger(0);//库存减少成功的次数,用来校验库存减少次数是否正确
        counter.initInventory(stockAll);

        //创建多个线程减少库存
        int threadCnt = 10;
        log.info("创建{}个线程，同时减少库存", threadCnt);
        Thread.sleep(1000);
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(threadCnt);
        for(int i=0;i<threadCnt;i++){
            new Thread(() -> {
                try{
                    while (true){
                        boolean b = counter.decreaseInventory();
                        if(!b){//返回false，表示库存已经为0，无法再减少
                            break;
                        }
                        cnt.getAndIncrement();//减少成功一次，+1
                    }
                }finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        //执行结束
        countDownLatch.await();
        if(stockAll==cnt.get()){
            log.info("分布式计数器测试成功,usedTime={}ms", System.currentTimeMillis()-start);
        }else{
            log.info("分布式计数器测试失败,usedTime={}ms", System.currentTimeMillis()-start);
        }
    }

}
