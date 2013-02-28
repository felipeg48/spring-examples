/**
 * 
 */
package com.itprosmx.spring.integration

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.MessageChannel
import org.springframework.integration.support.MessageBuilder
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author felipeg
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/endpoints-context.xml")
class EndPointsTest {

	@Autowired
	MessageChannel messageChannelSA
	
	@Test
	void serviceActivatorTest() {
		assertNotNull messageChannelSA
		
		def model = new ServiceActivatorModel(name:"John Doe",phone:"1-800-SPRING",birthday:new Date().parse("yyyy/MM/dd", "2005/01/01"))
		assertNotNull model
		
		def msg = MessageBuilder.withPayload(model).build()
		assertNotNull msg
		messageChannelSA.send msg
	}
	
	@Test
	void messageBridgeTest() {
		
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
