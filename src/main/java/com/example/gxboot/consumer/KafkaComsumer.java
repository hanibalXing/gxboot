package com.example.gxboot.consumer;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class KafkaComsumer {
	@KafkaListener(topics = "testpartitions", groupId = "1")
	public void listen1(ConsumerRecord<?, ?> record) throws Exception {
		log.info("topic = {}, offset = {},key = {},value = {},partition = {}", record.topic(),
				record.offset(), record.key(), record.value(), record.partition());
	}

	@KafkaListener(topics = "testpartitions", groupId = "1")
	public void listen2(ConsumerRecord<?, ?> record) throws Exception {
		log.info("topic = {}, offset = {},key = {}, value = {} , partition = {}", record.topic(),
				record.offset(), record.key(), record.value(), record.partition());
	}
}
