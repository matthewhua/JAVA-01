package io.matt.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TopicVO {
    private String channel;
    private BigDecimal rate;
    private LocalDateTime createTime;
}
