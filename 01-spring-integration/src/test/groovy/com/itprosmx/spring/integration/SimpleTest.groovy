/**
 * 
 */
package com.itprosmx.spring.integration

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.Message
import org.springframework.integration.MessageChannel
import org.springframework.integration.message.GenericMessage
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author felipeg
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/integration-simple-context.xml")
class SimpleTest {

	@Autowired
	MessageChannel messageChannel
	
	@Test
	void simpleTest() {
		Message<String> message = new GenericMessage<String>("Simple Message")
		assertNotNull message
		messageChannel.send message
		
	}

}
