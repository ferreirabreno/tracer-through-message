package dev.breno.tracerthroughmessage.messaging;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import dev.breno.tracerthroughmessage.messaging.models.SimpleMessage;
import dev.breno.tracerthroughmessage.messaging.models.TopicMessage;
import dev.breno.tracerthroughmessage.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MyQueueConsumer {

    private final Logger logger = LoggerFactory.getLogger(MyQueueConsumer.class);

    @Autowired private AmazonSQS amazonSQS;
    @Value("${aws.sqs.queue.myQueue.url}") String myQueueUrl;

    @Scheduled(fixedDelay = 15000)
    public void receiveMessage() {
        var messageRequest = new ReceiveMessageRequest(myQueueUrl);
        ReceiveMessageResult receiveMessageResult = amazonSQS.receiveMessage(messageRequest);
        receiveMessageResult.getMessages().forEach(this::processMessage);
    }

    private void processMessage(Message message) {
        try {
            TopicMessage topicMessage = JsonUtils.fromJson(message.getBody(), TopicMessage.class);
            logger.info("Topic message content: {}", topicMessage);

            SimpleMessage simpleMessage = JsonUtils.fromJson(topicMessage.message(), SimpleMessage.class);
            logger.info("Simple message content: {}", simpleMessage);

            amazonSQS.deleteMessage(myQueueUrl, message.getReceiptHandle());
        } catch (Exception exception) {
            logger.error(Arrays.toString(exception.getStackTrace()));
        }
    }


}
