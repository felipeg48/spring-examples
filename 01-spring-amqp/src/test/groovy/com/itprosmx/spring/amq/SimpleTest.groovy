package com.itprosmx.spring.amq

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/amqp-context.xml")
class SimpleTest {

	@Autowired
	AmqpTemplate amqpTemplate
	
	@Test
	void simple() {
		assertNotNull amqpTemplate
	}

}
