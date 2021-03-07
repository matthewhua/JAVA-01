/**
 * @author Matthew
 * @date 2021/3/1 17:12
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.matt.sqlTest.service.PlayerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:mapper/Player.xml")
public class TestPlayer
{

	@Autowired PlayerService playerService;

	@Test
	public void testAdd()
	{
		playerService.addServer(111L,"asgdhgas".getBytes(), "小马哥");
	}
}
