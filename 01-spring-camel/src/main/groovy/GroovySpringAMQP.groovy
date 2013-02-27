

import grails.spring.BeanBuilder

import org.springframework.amqp.core.AmqpTemplate


def bb = new BeanBuilder()
bb.loadBeans("classpath:/META-INF/spring/SpringAMQPBeans.groovy")

def ctx = bb.createApplicationContext()

def template = ctx.getBean(AmqpTemplate.class)
template.convertAndSend "myqueue", "foo"

def foo = template.receiveAndConvert "myqueue"
println foo