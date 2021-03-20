package io.matthew.rpcfx.demo.consumer.controller;

import io.matthew.rpcfx.annotation.Reference;
import io.matthew.rpcfx.demo.api.Order;
import io.matthew.rpcfx.demo.api.OrderService;
import io.matthew.rpcfx.demo.api.User;
import io.matthew.rpcfx.demo.api.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matthew
 * @date 2021/3/20 16:18
 */

@RestController
public class TestController
{


	@Reference(url = "http://localhost:8081/")
	private UserService userService;

	@Reference(url = "http://localhost:8081/")
	private OrderService orderService;

	@RequestMapping("/test")
	public void findTest()
	{
		User user = userService.findById(1);
		System.out.println("find user id=1 from server: " + user.getName());

		Order order = orderService.findOrderById(1992129);
		System.out.println(String.format("find order name=%s, amount=%f", order.getName(), order.getAmount()));
	}
}
