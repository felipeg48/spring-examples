/**
 * 
 */
package com.itprosmx.spring.amqp.config

import org.aopalliance.aop.Advice
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.config.StatelessRetryOperationsInterceptorFactoryBean
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.policy.MapRetryContextCache
import org.springframework.retry.support.RetryTemplate

import com.itprosmx.spring.amqp.advance.MyMessageAdapter
import com.rabbitmq.client.Channel
import com.rabbitmq.client.ShutdownListener
import com.rabbitmq.client.ShutdownSignalException


/**
 * @author fgutierrezcru
 *
 */
@Configuration
class AdvanceConfig {
	
	
	@Bean
	RabbitTemplate rabbitTemplate(){
		new RabbitTemplate(connectionFactory())
	}
	
	@Bean
	CachingConnectionFactory connectionFactory(){
		def connectionFactory = new CachingConnectionFactory("localhost")
		connectionFactory
	}
	
	@Bean
	RabbitAdmin rabbitAdmin(){
		new RabbitAdmin(connectionFactory())
	}

	@Bean
	SimpleMessageListenerContainer listener(){
		
		MessageListenerAdapter adapter = new MessageListenerAdapter(new MyMessageAdapter())
		adapter.defaultListenerMethod = "process"
		SimpleMessageListenerContainer listener = new SimpleMessageListenerContainer(connectionFactory())
		listener.messageListener = adapter
		listener.queueNames = ["myQueue"]
		
		listener
	}
	
	@Bean
	Queue getQueue(){
		Queue queue = new Queue("myQueue")
	}
	
}
