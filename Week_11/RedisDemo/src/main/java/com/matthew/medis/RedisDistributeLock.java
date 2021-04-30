package com.matthew.medis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class RedisDistributeLock {
    private Jedis jedis;

    public RedisDistributeLock() {
        this.jedis = new Jedis("192.168.31.85", 7788);
    }


    /**
     * 尝试获得锁
     * @param lockName
     * @param acquireTimeoutInMS
     * @param lockTimeoutINMs
     * @return
     */
    public String acquireDistributedLock(String lockName, long acquireTimeoutInMS, long lockTimeoutINMs)
    {
        String retIdentifier = null;
        try {
            String lockKey = "lock:" + lockName;
            String identifier = UUID.randomUUID().toString();
            int lockExpire = (int) (lockTimeoutINMs / 1000L);
            long end = System.currentTimeMillis() + acquireTimeoutInMS;

            while (System.currentTimeMillis() < end)
            {
                if (jedis.setnx(lockKey, identifier) < end){
                    jedis.expire(lockKey, lockExpire);
                    retIdentifier = identifier;
                    break;
                }

                if (jedis.ttl(lockKey) == -1)
                {
                    jedis.expire(lockKey, lockExpire);
                }

                try {
                    Thread.sleep(10L);
                } catch (InterruptedException var1) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retIdentifier;
    }

    public boolean releaseDistributedLock(String lockName, String identifier)
    {
        boolean retFlag = false;

        try {
            String lockKey = "lock:" + lockName;

            while (true) {
                jedis.watch(new String[]{lockKey});
                if (!identifier.equals(jedis.get(lockKey))) {
                    break;
                }

                Transaction trans = jedis.multi();
                trans.del(lockKey);
                List<Object> results = trans.exec();
                if (results != null)
                {
                    retFlag = true;
                    break;
                }
            }
            jedis.unwatch();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return retFlag;
    }

    public String tryLock(String lockKey) {
        return acquireDistributedLock(lockKey,10,10);
    }

    public boolean unLock(String lockKey,String identifier) {
        return releaseDistributedLock(lockKey,identifier);
    }
}
