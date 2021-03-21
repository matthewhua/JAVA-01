package io.matthew.common.service;

import io.matthew.common.entity.Account;
import org.dromara.hmily.annotation.Hmily;

/**
 * @author Matthew
 * @date 2021-03-21 8:13
 */
public interface TransMoneyService {

    Account findAccountByUserId(long userId);


    @Hmily
    Boolean transMoney(long userId, Account trans);
}
