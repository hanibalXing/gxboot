package com.example.gxboot.controller;

import com.example.gxboot.Bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * ClassName: TestController<br/>
 * Description: java类作用描述<br/>
 * date 2019/3/13 14:44
 *
 * @author gx
 * @Version 1.0
 * @since 1.7
 */
@RestController
@Slf4j
public class TestController {
	@RequestMapping(path = "/hi", method = POST)
	public String add(@RequestBody User user) {
		return "123";
	}

	@RequestMapping(path = "/hi/{id}", method = GET)
	public String get(@PathVariable Long id) {
		return "234";
	}

	@RequestMapping(path = "/hi/{id}", method = GET)
	public String page(@PathVariable Long id) {
		return "235";
	}

	@RequestMapping(path = "/hi/{id}", method = PUT)
	public String update(@PathVariable Long id, @RequestBody User user) {
		log.info("user:{}", user);
		return "345";
	}

	@RequestMapping(path = "/hi", method = DELETE)
	public String delete() {
		return "456";
	}


}
