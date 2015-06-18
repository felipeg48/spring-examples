/**
 * 
 */
package com.itprosmx.spring.integration

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.channel.ExecutorChannel
import org.springframework.integration.channel.PriorityChannel
import org.springframework.integration.channel.PublishSubscribeChannel
import org.springframework.integration.channel.QueueChannel
import org.springframework.integration.channel.RendezvousChannel
import org.springframework.integration.support.MessageBuilder
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.GenericMessage
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
	PriorityChannel priorityChannel
	
	@Autowired
	PriorityChannel priorityWithComparatorChannel
	
	@Autowired
	RendezvousChannel rendezvousChannel
	
	@Autowired
	MessageChannel replyChannel
	
	@Autowired
	PublishSubscribeChannel publishSubscribeChannel
	
	@Autowired
	DirectChannel directChannel
	
	@Autowired
	ExecutorChannel executorChannel
	
	
	def banner = { title ->
		println "*" * 50
		println "** $title"
		println "*" * 50
	}
	
	@Test
	void p2pChannelTest() {
		banner "p2pChannelTest"
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
	void pubsubChannelTest(){
		banner "pubsubChannelTest"
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
	void priorityChannelTest(){
		banner "priorityChannelTest"
		//Step 0. Some variables
		String payloadPriorityLow  = "Low Priority"
		String payloadPriorityHigh = "High Priority"
		
		//Producer
		//Step 1. Build the Message and send
		
		Message<String> message = MessageBuilder.withPayload(payloadPriorityLow).setPriority(1).build()
		assertNotNull message
		println "Sending Low Priority Message, with value 1"
		priorityChannel.send message
		
		//Step 2. Send another message with higher priority
		message = MessageBuilder.withPayload(payloadPriorityHigh).setPriority(10).build()
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
	void priorityWithComparatorChannelTest(){
		banner "priorityWithComparatorChannelTest"
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
	void rendezvousChannelTest(){
		banner "rendezvousChannelTest"
		//Step 0. Some variables
		def replyMessage  = "Got it"
		
		//Producer
		//Step 1. Build the message and send it
		Thread.start {
			println "Producer: Sending Message and waiting for reply"
			def msg = MessageBuilder
					  .withPayload("A rendezvous message")
					  .setHeader(MessageHeaders.REPLY_CHANNEL, replyChannel).build()
					  assertNotNull msg
					  rendezvousChannel.send msg,10000
					  
			Message rp = ((QueueChannel) replyChannel).receive(10000)
			assertNotNull rp
			println "Producer: Consumer reply > ${rp.payload}"
			assertEquals replyMessage,rp.payload
		}
		//Consumer
		//Step 2. Received the message and identify if it needs reply
		Message message = ((RendezvousChannel) rendezvousChannel).receive(10000)
		assertNotNull message
		println "Consumer: $message"
		
		//Step 2.1. Get the reply channel 
		def reply = message.getHeaders().get(MessageHeaders.REPLY_CHANNEL)
		assertNotNull reply
	
		//Step 2.2. Send a Reply to it 
		Message<String> gotit = new GenericMessage<String>(replyMessage)
		reply.send gotit
	}
	
	@Test
	void publishSubscribeChannelTest(){
		banner "publishSubscribeChannelTest"
		//Consumer
		//Step 1. Create Handler
		def handler = [ handleMessage: { msg -> println "Message (Received): $msg" } ] as MessageHandler
		//Step 2. Subscribe
		publishSubscribeChannel.subscribe handler
		
		//Producer
		//Step 3. 
		Message<String> msg = new GenericMessage<String>("Hello PublishSubscribeChannel!!")
		publishSubscribeChannel.send msg
		
		//Consumer
		//Step 4. Unsubscribe
		publishSubscribeChannel.unsubscribe handler
		
		
		//Producer
		//Step 5.
		msg = new GenericMessage<String>("Another message")
		publishSubscribeChannel.send msg
	}
	
	@Test
	void directChannelTest(){
		banner "directChannelTest"
		//Consumers
		//Step 1. Declare handlers
		def handlerC1 = [ handleMessage: { msg -> println "Message (Received) for Consumer 1: $msg" } ] as MessageHandler
		def handlerC2 = [ handleMessage: { msg -> println "Message (Received) for Consumer 2: $msg" } ] as MessageHandler
		def handlerC3 = [ handleMessage: { msg -> println "Message (Received) for Consumer 3: $msg" } ] as MessageHandler
		
		//Step 2. Subscribe
		directChannel.subscribe handlerC1
		directChannel.subscribe handlerC2
		directChannel.subscribe handlerC3
	
		//Producer
		//Step 3. Send the messages
		Message<String> msg = new GenericMessage<String>("Hello Direct Channel!!")
		9.times {
			//The consumer is choose through RoundRobin, an one message at a time
			directChannel.send msg
			sleep 800
		}
	}
	
	@Test
	void executorChannelTest(){
		banner "executorChannelTest"
		//Consumers
		//Step 1. Declare handlers
		def handlerC1 = [ handleMessage: { msg -> println "Message (Received) for Consumer 1: $msg" } ] as MessageHandler
		def handlerC2 = [ handleMessage: { msg -> println "Message (Received) for Consumer 2: $msg" } ] as MessageHandler
		def handlerC3 = [ handleMessage: { msg -> println "Message (Received) for Consumer 3: $msg" } ] as MessageHandler
		def handlerC4 = [ handleMessage: { msg -> println "Message (Received) for Consumer 4: $msg" } ] as MessageHandler
		
		//Step 2. Subscribe
		executorChannel.subscribe handlerC1
		executorChannel.subscribe handlerC2
		executorChannel.subscribe handlerC3
	
		//Producer
		//Step 4. Send the messages
		Message<String> msg = new GenericMessage<String>("Hello Executor Channel!!")
		
		15.times {
			//The consumer is choose through RoundRobin, an one message at a time
			//and is a failover test
			executorChannel.send msg
			sleep 500
			switch(it){
				case 2:
					executorChannel.unsubscribe handlerC1
					break
				case 5:
					executorChannel.unsubscribe handlerC2
					break
				case 7:
					executorChannel.subscribe handlerC4
					break
			}
		}
	}
}
