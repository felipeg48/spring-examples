package com.springboot.rabbitmq

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class Producer {
	
	
	@Autowired
	RabbitTemplate rabbitmqTemplate

	//@Scheduled(cron="0 0 0/3 * * ?")
	@Scheduled(fixedDelay = 1000L)
	void send(){
		5.times {
			this.rabbitmqTemplate.convertAndSend 'foo', 'Hello world'
		}
	}
	
}
