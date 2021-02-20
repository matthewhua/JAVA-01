package io.matthew.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author Matthew
 * @date 2021-02-20 23:27
 */
@Data
@ToString
public class UserHolder {


    private User user;

    private String name;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }
    public UserHolder(User user , String name) {
        this.user = user;
        this.name = name;
    }
}
