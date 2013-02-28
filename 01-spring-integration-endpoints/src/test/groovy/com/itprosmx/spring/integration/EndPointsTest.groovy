/**
 * 
 */
package com.itprosmx.spring.integration

import static org.junit.Assert.*
import groovy.util.logging.Log4j

import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.MessageChannel
import org.springframework.integration.channel.PublishSubscribeChannel
import org.springframework.integration.core.PollableChannel
import org.springframework.integration.support.MessageBuilder
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author felipeg
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/endpoints-context.xml")
@Log4j
class EndPointsTest {

	@Autowired
	MessageChannel messageChannelSA
	
	@Autowired
	MessageChannel messageChannelSAReply
	@Autowired
	PollableChannel messageChannelSAReplyOutput
	
	@Autowired
	MessageChannel inputChannel
	
	
	@Test
	@Ignore
	void serviceActivatorTest() {
		assertNotNull messageChannelSA
		
		def model = new ServiceActivatorModel(name:"John Doe",phone:"1-800-SPRING",birthday:new Date().parse("yyyy/MM/dd", "2005/01/01"))
		assertNotNull model
		
		def msg = MessageBuilder.withPayload(model).build()
		assertNotNull msg
		messageChannelSA.send msg
	}
	
	@Test
	@Ignore
	void serviceActivatorReplyTest() {
		assertNotNull messageChannelSAReply
		assertNotNull messageChannelSAReplyOutput
		
		def model = new ServiceActivatorModel(name:"John Doe",phone:"1-800-SPRING",birthday:new Date().parse("yyyy/MM/dd", "2005/01/01"))
		assertNotNull model
		
		def msg = MessageBuilder.withPayload(model).build()
		assertNotNull msg
		messageChannelSAReply.send msg
		
		def reply = messageChannelSAReplyOutput.receive()
		assertNotNull reply
		log.info reply
	}
	
	@Test
	void messageBridgeTest() {
		assertNotNull inputChannel
		def model = new ServiceActivatorModel(name:"John Doe",phone:"1-800-SPRING",birthday:new Date().parse("yyyy/MM/dd", "2005/01/01"))
		assertNotNull model
		
		def msg = MessageBuilder.withPayload(model).build()
		assertNotNull msg
		inputChannel.send msg
	}
	
	@Test
	void messageEnricherTest() {
		
	}

	@Test
	void gatewaySynchronousTest() {
		
	}
	@Test
	void gatewayASynchronousTest() {
		
	}
	
	@Test
	void delayerTest() {
		
	}
}
