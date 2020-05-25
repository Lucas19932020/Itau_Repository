package br.com.uscs.uscsitau.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class OrderProducer {

    @Value("${spring.kafka.consumer.topic}")
    private String orderTopic;

    private final KafkaTemplate kafkaTemplate;

    public OrderProducer(final KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(final @RequestBody Object obj) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        kafkaTemplate.send(orderTopic, mapper.writeValueAsString(obj));
    }
}
