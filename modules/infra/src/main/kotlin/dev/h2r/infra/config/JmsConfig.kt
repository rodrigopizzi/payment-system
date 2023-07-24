package dev.h2r.infra.config

import com.amazon.sqs.javamessaging.ProviderConfiguration
import com.amazon.sqs.javamessaging.SQSConnectionFactory
import jakarta.jms.Session
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.destination.DynamicDestinationResolver
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import java.net.URI


@Configuration
@EnableJms
class JmsConfig {

    @Value("\${aws.region}")
    private val awsRegion: String? = null

    @Value("\${aws.sqs.queue.endpoint}")
    private val endpointQueueUrl: String? = null

    @Bean
    fun sqsClient(): SqsClient {
        return SqsClient.builder()
            .credentialsProvider(DefaultCredentialsProvider.create())
            .region(Region.of(awsRegion))
            .endpointOverride(URI(endpointQueueUrl!!))
            .build()
    }

    @Bean
    fun sqsConnectionFactory(sqsClient: SqsClient): SQSConnectionFactory {
        return SQSConnectionFactory(ProviderConfiguration(), sqsClient)
    }

    @Bean
    fun jmsListenerContainerFactory(
        sqsClient: SqsClient,
        sqsConnectionFactory: SQSConnectionFactory
    ): DefaultJmsListenerContainerFactory {
        val factory = DefaultJmsListenerContainerFactory()
        factory.setConnectionFactory(sqsConnectionFactory)
        factory.setDestinationResolver(DynamicDestinationResolver())
        factory.setConcurrency("2")
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE)
        return factory
    }


    @Bean
    fun jmsTemplate(sqsConnectionFactory: SQSConnectionFactory): JmsTemplate {
        return JmsTemplate(sqsConnectionFactory)
    }

}