package io.matthew.consumer.controller;

import io.matthew.common.entity.Account;
import io.matthew.common.service.TransMoneyService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Matthew
 * @date 2021-03-21 8:25
 */
@RestController
public class DemoController
{
    @DubboReference
    private TransMoneyService transMoneyService;

    @PostMapping("findAccountByUserId")
    public Account findAccountByUserId(@RequestBody Long userId){
        return transMoneyService.findAccountByUserId(userId);
    }


    @PostMapping("transUsd")
    public Boolean transUsd(@RequestBody Map<String, Object> map) {
        long userId = (Integer) map.get("userId");
        BigDecimal money = BigDecimal.valueOf((Double) map.get("money"));
        Account trans = new Account();
        trans.setUsd(money);
        return transMoneyService.transMoney(userId, trans);
    }

    @PostMapping("transCny")
    public Boolean transCny(@RequestBody Map<String, Object> map) {
        long userId = (Integer) map.get("userId");
        BigDecimal money = BigDecimal.valueOf((Double) map.get("money"));
        Account trans = new Account();
        trans.setCny(money);
        return transMoneyService.transMoney(userId, trans);
    }

}
