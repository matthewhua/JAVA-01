package io.matt.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QueueVO {
    private String code;
    private int cnt;
    private LocalDateTime localDateTime;
}
