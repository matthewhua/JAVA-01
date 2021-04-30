package com.matthew.medis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCounter {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisLock redisLock;

    public void initInventory(Integer cnt)
    {
        redisTemplate.opsForValue().set("Inventory", cnt);
    }

    /**
     * 减少库存,从redis中读取当前库存，减一，并修改
     * true  --  减少库存成功
     * false --  已经没有库存
     */
    public boolean decreaseInventory() {
        String lockKey = "stock_key";
        redisLock.tryLock(lockKey);
        try {
            Integer inventory = (Integer) redisTemplate.opsForValue().get("Inventory");
            if (inventory <= 0) {
                return false;
            }
            inventory--;
            redisTemplate.opsForValue().set("Inventory", inventory);
            return true;
        } finally {
            redisLock.unLock(lockKey);
        }
    }
}
