package dev.breno.tracerthroughmessage;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MyTopicPublisher {

    private final Logger logger = LoggerFactory.getLogger(MyTopicPublisher.class);

    @Autowired private AmazonSNS amazonSNS;
    @Value("${aws.sns.topic.myTopic.arn}") private String myTopicArn;

    public void publishMessage(SimpleMessage message, Map<String, String> tracingHeaders) {
        logger.info("Sending message \"{}\" for sns topic myTopic.", message);

        Map<String, MessageAttributeValue> messageAttributes = buildMessageAttributes(tracingHeaders);

        PublishRequest publishRequest = new PublishRequest(myTopicArn, message.toString());
        publishRequest.withMessageAttributes(messageAttributes);

        PublishResult publishResult = amazonSNS.publish(publishRequest);

        logger.info("Message {} sent to myTopic.", publishResult.getMessageId());
    }

    private Map<String, MessageAttributeValue> buildMessageAttributes(Map<String, String> attributes) {
        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
        attributes.forEach((key, value) -> messageAttributes.put(key,
                new MessageAttributeValue().withDataType("String").withStringValue(value)));
        return messageAttributes;
    }
}
