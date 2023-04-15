package br.learn.kafka.spring.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaTopicListener {

	@Value("${topic.name.consumer}")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void processEvent(ConsumerRecord<String, String> payload){
    	System.out.println("New event available on topic : " + topicName + ", Content : " + payload.value());

    }
}
