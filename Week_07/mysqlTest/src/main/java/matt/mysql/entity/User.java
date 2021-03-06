package matt.mysql.entity;

import lombok.Data;

/**
 * @author Matthew
 * @date 2021-03-06 0:31
 */
@Data
public class User {

    Integer userId;
    String nickname;
    String password;
}
