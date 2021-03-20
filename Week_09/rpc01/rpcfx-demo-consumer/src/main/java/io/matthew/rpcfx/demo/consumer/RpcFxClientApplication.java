package io.matthew.rpcfx.demo.consumer;

import io.matthew.rpcfx.annotation.Reference;
import io.matthew.rpcfx.api.Filter;
import io.matthew.rpcfx.api.LoadBalancer;
import io.matthew.rpcfx.api.Router;
import io.matthew.rpcfx.api.RpcfxRequest;
import io.matthew.rpcfx.demo.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * @author Matthew
 * @date 2021/3/20 16:38
 */
@SpringBootApplication
public class RpcFxClientApplication
{
	// 二方库
	// 三方库 lib
	// nexus, userserivce -> userdao -> user
	//

	@Reference(url = "http://localhost:8081/")
	private UserService userService;

	public static void main(String[] args)
	{
		SpringApplication.run(RpcFxClientApplication.class, args);
	}


	private static class TagRouter implements Router{
		@Override public List<String> route(List<String> urls)
		{
			return urls;
		}
	}

	private static class RandomLoadBalancer implements LoadBalancer
	{
		@Override
		public String select(List<String> urls) {
			return urls.get(0);
		}
	}

	@Slf4j
	private static class CuicuiFilter implements Filter
	{
		@Override
		public boolean filter(RpcfxRequest request) {
			log.info("filter {} -> {}", this.getClass().getName(), request.toString());
			return true;
		}
	}
}
