

import grails.spring.BeanBuilder

import org.springframework.amqp.core.AmqpTemplate


def bb = new BeanBuilder()
bb.loadBeans("classpath:/META-INF/spring/SpringCamelAMQPBeans.groovy")

def ctx = bb.createApplicationContext()
