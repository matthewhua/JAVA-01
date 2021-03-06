package io.matthew.common.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Matthew
 * @date 2021-03-21 8:01
 */
@Data
@ToString
public class FreezeAccount implements Serializable {
    private long user_id;

    private BigDecimal usd;

    private BigDecimal cny;
}
