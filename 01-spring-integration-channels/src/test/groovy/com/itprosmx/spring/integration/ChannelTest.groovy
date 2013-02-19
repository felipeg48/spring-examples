/**
 * 
 */
package com.itprosmx.spring.integration

import static org.junit.Assert.*

import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.Message
import org.springframework.integration.MessageChannel
import org.springframework.integration.MessageHeaders
import org.springframework.integration.channel.PublishSubscribeChannel
import org.springframework.integration.channel.QueueChannel
import org.springframework.integration.core.MessageHandler
import org.springframework.integration.message.GenericMessage
import org.springframework.integration.support.MessageBuilder
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author felipeg
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/channels-context.xml")
class ChannelTest {

	@Autowired
	MessageChannel messageChannel
	
	@Autowired
	MessageChannel pubsubChannel
	
	@Autowired
	MessageChannel priorityChannel
	
	@Autowired
	MessageChannel priorityWithComparatorChannel
	
	@Test
	@Ignore
	void p2pChannelTest() {
		//Producer
		//Step 1. Build the Message
		Message<String> message = new GenericMessage<String>("Simple Message")
		assertNotNull message
		
		//Step 2. Send the Message
		messageChannel.send message
		
		//Consumer
		//Step 3. Received the Message
		Message msgReceived = ((QueueChannel) messageChannel).receive(10000)
		assertNotNull msgReceived
		println "P2P Message (Received): ${msgReceived.payload}"
	}

	@Test
	@Ignore
	void pubsubChannelTest(){
		//Consumer
		//Step 1. Subscribe to the Channel
		((PublishSubscribeChannel)pubsubChannel).
		subscribe([
					handleMessage: { msg ->
										println "PubSub Message (Received): $msg"
						           }
				  ] as MessageHandler)
		
		
		//Producer
		//Step 2. Build the Message
		Message<String> message = new GenericMessage<String>("Simple Message")
		assertNotNull message
		
		//Step 3. Send the Message
		pubsubChannel.send message
	}
	
	@Test
	@Ignore
	void priorityChannelTest(){
		
		//Step 0. Some variables
		String payloadPriorityLow  = "Low Priority"
		String payloadPriorityHigh = "High Priority"
		
		//Producer
		//Step 1. Build the Message and send
		Message<String> message = MessageBuilder.withPayload(payloadPriorityLow).setHeader(MessageHeaders.PRIORITY, 1).build();
		assertNotNull message
		println "Sending Low Priority Message, with value 1"
		priorityChannel.send message
		
		//Step 2. Send another message with higher priority
		message = MessageBuilder.withPayload(payloadPriorityHigh).setHeader(MessageHeaders.PRIORITY, 10).build();
		assertNotNull message
		println "Sending High Priority Message, with value 10"
		priorityChannel.send message
		
		//Consumer
		//Step 3. Received the Messages
		Message msgReceived = ((QueueChannel) priorityChannel).receive(10000)
		assertNotNull msgReceived
		println "Message (Received): ${msgReceived.payload}"
		assertEquals payloadPriorityHigh,msgReceived.payload
		
		msgReceived = ((QueueChannel) priorityChannel).receive(10000)
		assertNotNull msgReceived
		println "Message (Received): ${msgReceived.payload}"
		assertEquals payloadPriorityLow,msgReceived.payload

	}
	
	@Test
	@Ignore
	void priorityWithComparatorChannelTest(){
		//Step 0. Model
		def payloadPriorityLow = new SimpleModel(name:"Doe",phone:"1-800-VMWARE",birthday:Date.parse("yyyy-mm-dd","1990-08-22"))
		def payloadPriorityHigh = new SimpleModel(name:"John",phone:"1-800-SPRING",birthday:Date.parse("yyyy-mm-dd","1980-08-22"))
		
		//Producer
		//Step 1. Build the Message and send
		Message<SimpleModel> message = new GenericMessage<SimpleModel>(payloadPriorityLow)
		assertNotNull message
		println "Sending Low Priority Message -> $payloadPriorityLow"
		priorityWithComparatorChannel.send message
		
		//Step 2. Send another message with higher priority
		message = new GenericMessage<SimpleModel>(payloadPriorityHigh)
		assertNotNull message
		println "Sending High Priority Message -> $payloadPriorityHigh"
		priorityWithComparatorChannel.send message
		
		
		println "Seniors must go first"
		
		//Consumer
		//Step 3. Received the Messages
		Message msgReceived = ((QueueChannel) priorityWithComparatorChannel).receive(10000)
		assertNotNull msgReceived
		println "Message (Received): ${msgReceived.payload}"
		assertEquals payloadPriorityHigh.name,msgReceived.payload.name
		
		msgReceived = ((QueueChannel) priorityWithComparatorChannel).receive(10000)
		assertNotNull msgReceived
		println "Message (Received): ${msgReceived.payload}"
		assertEquals payloadPriorityLow.name,msgReceived.payload.name
	}
	
	@Test
	@Ignore
	void rendezvousChannelTest(){
		
	}
}
