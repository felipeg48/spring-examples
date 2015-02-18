/**
 * 
 */
package com.itprosmx.spring.amqp

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author felipeg
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/amqp-custom-converter-context.xml")
class CustomMessageConverterTest {

	@Autowired
	AmqpTemplate amqpTemplate
	
	@Test
	void simpleMessageConverter() {
		assertNotNull amqpTemplate
		org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter adpater
		def model = new CustomModel(name:"John",phone:"1-800-JOHN-DOE",birthday:new Date().parse("M/d/yyyy", "08/10/1930"))
		amqpTemplate.convertAndSend "myqueue", model
		
		sleep 3000
	}
}

