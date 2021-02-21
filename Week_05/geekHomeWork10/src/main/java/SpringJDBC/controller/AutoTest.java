package SpringJDBC.controller;

import io.matthew.autoConfig.ISchool;
import io.matthew.autoConfig.Klass;
import io.matthew.autoConfig.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *测试 starter
 *
 * @author Matthew
 * @date 2021/2/21 11:46
 */

// Rest风格Controller
@RestController
public class AutoTest
{
	@Autowired
	private ISchool school;
	@Autowired
	private Klass klass;
	@Autowired
	private Student student;

	@GetMapping("/hello")
	public String hello(){
		school.ding();
		klass.dong();
		student.print();
		return String.join(",",student.getApplicationContext().getBeanDefinitionNames());
	}
}
