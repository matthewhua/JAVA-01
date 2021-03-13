package io.matthew.controller;

import io.matthew.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Matthew
 * @date 2021-03-13 21:14
 */
@RestController
public class OrderController {

    @Autowired
    OrderDao orderDao;

    @RequestMapping("addOrder")
    public String addOrder(@RequestBody Map<String, Object> reqMap)
    {
        orderDao.insertOrder(reqMap);
        return "SUCCESS";
    }


    @RequestMapping("getOrderList")
    public List<Map<String, Object>>  getOrderList()
    {
        return orderDao.selectAll();
    }
}
