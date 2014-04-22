package com.itprosmx.spring.amqp

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import com.itprosmx.spring.amqp.config.AdvanceConfig


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=[AdvanceConfig.class])
class AdvanceTest {

	@Autowired
	RabbitTemplate rabbitTemplate
	
	@Test
	void simple() {
		assertNotNull rabbitTemplate
		
		def answer = null
		while(answer != "q"){
			answer = input("> ")	
		}
	}

	def input = { prompt ->
		print prompt
		System.in.newReader().readLine()
	}
}
