package matt.mysql.controller;

import io.swagger.annotations.ApiOperation;
import matt.mysql.entity.User;
import matt.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Matthew
 * @date 2021-03-06 0:33
 */
@RestController
@RequestMapping("/order")
public class UserAction {

    @Autowired
    UserService userService;

    @ApiOperation(value = "批量添加用户", notes = "批量添加用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public int[] addBatchOrderInfo(@RequestBody User orderInfo){
        try {
            List<User> orderInfoList = new ArrayList<>();
            for(int i=0;i<1000000;i++){
                orderInfoList.add(orderInfo);
            }
            return userService.addBatchUser(orderInfoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
