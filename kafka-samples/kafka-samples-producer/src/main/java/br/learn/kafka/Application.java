package br.learn.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		Properties props = new Properties();
		//props.put("bootstrap.servers", "localhost:9092,localhost:9192,localhost:9292");
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
			for (int i = 0; i < 100; i++) {
				producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i),
						"MyMessage: " + Integer.toString(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
