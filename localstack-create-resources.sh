aws --endpoint-url=http://localstack:4566 sns create-topic --name myTopic
aws --endpoint-url=http://localstack:4566 sqs create-queue --queue-name myQueue
aws sns subscribe --endpoint-url=http://localstack:4566 \
  --topic-arn arn:aws:sns:us-east-1:000000000000:myTopic \
  --protocol sqs \
  --notification-endpoint arn:aws:sqs:us-east-1:000000000000:myQueue