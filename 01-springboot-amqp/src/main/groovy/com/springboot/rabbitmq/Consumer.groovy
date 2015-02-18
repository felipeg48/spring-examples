package com.springboot.rabbitmq

import org.springframework.amqp.core.AcknowledgeMode
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import com.rabbitmq.client.Channel

@Configuration
class Consumer {
	
	@Autowired
	ConnectionFactory connectionFactory

	@Bean
	SimpleMessageListenerContainer container() {
		(this.connectionFactory as CachingConnectionFactory).requestedHeartBeat = 10
		def container = new SimpleMessageListenerContainer(this.connectionFactory)
		
		def listener = [
			onMessage: { Message message, Channel channel ->
					def r = new Random()
					def m = new String(message.body)
					
					println "Message:$m, from Channel: ${channel.channelNumber}"
				}
			] as ChannelAwareMessageListener
		
		container.messageListener = new MessageListenerAdapter(listener)
		container.acknowledgeMode = AcknowledgeMode.AUTO
		container.concurrentConsumers = 1
		container.queueNames = ['foo']
		container
	}
}
