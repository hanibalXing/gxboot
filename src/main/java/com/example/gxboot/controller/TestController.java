package com.example.gxboot.controller;

import com.example.gxboot.Bean.HttpMessage;
import com.example.gxboot.Bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.concurrent.ThreadLocalRandom.current;
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
	@Autowired
	private KafkaTemplate kafkaTemplate;

	@RequestMapping(path = "/hi", method = POST)
	public String add(@RequestBody HttpMessage httpMessage) {
		log.info("message:{}",httpMessage.getMessageContent());
		return "123";
	}

	@RequestMapping(path = "/hi/{id}", method = GET)
	public Page<User> get(@PathVariable Long id) {
		User user=new User("gxx",12);
		Page<User> p=new PageImpl<>(Arrays.asList(user));

		return p;
	}

	@RequestMapping(path = "/hi/{id}/{id2}", method = GET)
	public ResponseEntity<User> get(@PathVariable Long id,@PathVariable Long id2) {
		User user=new User("gxx",12);
		return ResponseEntity.status(200).body(user);
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

	@RequestMapping(path = "/sendmessage", method = POST)
	public String send(@RequestBody String msg) {
		//log.info("生产者生产的消息：" + msg);
		//ListenableFuture send = kafkaTemplate.send("testpartitions",1,  "test", msg);
		IntStream.rangeClosed(1,100).forEach(i->{
			kafkaTemplate.send("storm",String.valueOf(i),current().nextInt(5)+"," +i);
		});
		return "success";
	}


}
