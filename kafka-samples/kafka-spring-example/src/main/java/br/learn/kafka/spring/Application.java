package br.learn.kafka.spring;

import java.util.Random;
import java.util.stream.IntStream;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@EnableKafka
@SpringBootApplication
public class Application {

    @Value("${topic.name.consumer}")
    private String topicName;

    public static void main(String[] args) {

	SpringApplication.run(Application.class, args);
    }

    @Bean
    NewTopic topic() {
	return TopicBuilder.name(topicName)
		.partitions(1)
		.replicas(1)
		.build();
    }

    @Bean
    ApplicationRunner runner(KafkaTemplate<String, String> template) {
	Random random = new Random();
	return args -> {
	    IntStream samples = random.ints(10, 1, 500);
	    samples.forEach(s -> template.send(topicName, Long.toString(s)));
	};
    }

}
