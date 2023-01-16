package dev.breno.tracerthroughmessage;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MyTopicPublisher {

    private final Logger logger = LoggerFactory.getLogger(MyTopicPublisher.class);

    @Autowired private AmazonSNS amazonSNS;
    @Value("${aws.sns.topic.myTopic.arn}") private String myTopicArn;

    public void publishMessage(String message) {
        logger.info("Sending message \"{}\" for sns topic myTopic.", message);
        PublishRequest publishRequest = new PublishRequest(myTopicArn, message);
        PublishResult publishResult = amazonSNS.publish(publishRequest);
        logger.info("Message {} sent to myTopic.", publishResult.getMessageId());
    }
}
