package com.example.gxboot.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: KafkaComsumer<br/>
 * Description: java类作用描述<br/>
 * date 2019/3/18 15:06
 *
 * @author gx
 * @Version 1.0
 * @since 1.7
 */
@Component
public class KafkaComsumer {
	@KafkaListener(topics = "test")
	public void listen(ConsumerRecord<?, ?> record) throws Exception {
		System.out.printf("topic = %s, offset = %d,key = %s, value = %s \n", record.topic(), record.offset()
				, record.key(), record.value());
	}

}
