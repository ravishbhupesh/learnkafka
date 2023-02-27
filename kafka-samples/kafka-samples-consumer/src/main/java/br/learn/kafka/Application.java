package br.learn.kafka;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		subscribe();
		
		//assign();
	}
	
	private static void subscribe() {
		Properties props = new Properties();
		//props.put("bootstrap.servers", "localhost:9092,localhost:9192,localhost:9292");
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("group.id", "console-consumer-myapp");

		try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
			
			consumer.subscribe(List.of("my-topic", "my-second-topic"));
			
			while(true) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
				records.forEach(record -> System.out.println(String.format("Topic : %s | Partition : %d | Offset : %d | Key : %s | Value : %s", 
						record.topic(), record.partition(), record.offset(), record.key(), record.value())));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void assign() {
		Properties props = new Properties();
		//props.put("bootstrap.servers", "localhost:9092,localhost:9192,localhost:9292");
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("group.id", "console-consumer-myapp");

		try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
			
			consumer.assign(List.of(new TopicPartition("my-topic", 0), new TopicPartition("my-second-topic", 2)));
			
			while(true) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
				records.forEach(record -> System.out.println(String.format("Topic : %s | Partition : %d | Offset : %d | Key : %s | Value : %s", 
						record.topic(), record.partition(), record.offset(), record.key(), record.value())));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
