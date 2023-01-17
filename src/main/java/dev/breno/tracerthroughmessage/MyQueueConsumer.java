package dev.breno.tracerthroughmessage;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

@Service
public class MyQueueConsumer {

    private final Logger logger = LoggerFactory.getLogger(MyQueueConsumer.class);

    @Autowired private AmazonSQS amazonSQS;
    @Value("${aws.sqs.queue.myQueue.url}") String myQueueUrl;

    @Scheduled(fixedDelay = 15000)
    public void receiveMessage() {
        var messageRequest = new ReceiveMessageRequest("http://localhost:4566/000000000000/myQueue");
        messageRequest.withMessageAttributeNames("All");
        ReceiveMessageResult receiveMessageResult = amazonSQS.receiveMessage(messageRequest);
        receiveMessageResult.getMessages().forEach(this::processMessage);
    }

    private void processMessage(Message message) {
        try {
            logger.info("Message received {}.", message);
            logger.info("context: {}", message.getAttributes());
            String messageBody = message.getBody();
            SimpleMessage simpleMessage = fromJson(messageBody);
            logger.info("Simple message: {}", simpleMessage);
            amazonSQS.deleteMessage(myQueueUrl, message.getReceiptHandle());
        } catch (Exception e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }

    private SimpleMessage fromJson(String json) throws Exception {
        var jsonMapper = JsonMapper.builder()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .build();

        jsonMapper.registerModule(new JavaTimeModule());

        try {
            return jsonMapper.readValue(json, SimpleMessage.class);
        } catch (Exception e) {
            throw new Exception("Couldn't deserialize object from json.");
        }

    }
}
