package com.itprosmx.spring.integration

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.Message
import org.springframework.integration.MessageChannel
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/integration-service-activator-context.xml")
class ServiceActivatorTest {

	Model model = new Model(name:"Spring",phone:"1-800-SPRING",birthday:new Date().parse("yyyy/MM/dd", "2005/01/01"))
	
	@Autowired
	MessageChannel messageChannel
	
	@Test
	void serviceActivatorTest() {
		assertNotNull model
		assertNotNull messageChannel
		
		def msg = MessageBuilder.withPayload(model).build()
		assertNotNull msg
		messageChannel.send msg 
	}

}
