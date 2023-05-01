package br.learn.kafka.spring.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaTopicListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTopicListener.class);

    @Value("${topic.name.consumer}")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void processEvent(ConsumerRecord<String, String> payload) {
	LOGGER.info("New event available on topic : {}, Content : {}", topicName, payload.value());
	LOGGER.info("Is {} a prime number ? : {}", payload.value(), isPrime(Long.parseLong(payload.value())));
    }

    private boolean isPrime(long input) {

	long absInput = Math.abs(input);

	if (0 == absInput || 1 == absInput) {
	    return false;
	} else {
	    for (long i = 2; i < absInput; i++) {
		if (absInput % i == 0) {
		    return false;
		}
	    }
	}
	return true;
    }
}
