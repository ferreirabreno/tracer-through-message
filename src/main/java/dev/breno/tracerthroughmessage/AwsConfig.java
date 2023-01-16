package dev.breno.tracerthroughmessage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Value("${aws.region}") private String awsRegion;
    @Value("${aws.sns.endpoint}") private String awsSnsEndpoint;
    @Value("${aws.sns.topic.myTopic.arn}") private String myTopicArn;
    @Value("${aws.accessKeyId}") private String accessKey;
    @Value("${aws.secretKey}") private String secretKey;

    @Bean
    public AmazonSNS amazonSNS() {
        var endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(awsSnsEndpoint, awsRegion);
        var credentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));

        return AmazonSNSClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(credentialsProvider)
                .build();
    }

    @Bean
    public AmazonSQS amazonSQS() {
        var endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(awsSnsEndpoint, awsRegion);
        var credentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));

        return AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(credentialsProvider)
                .build();
    }

}