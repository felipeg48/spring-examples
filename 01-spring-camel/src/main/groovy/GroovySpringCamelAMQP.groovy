

import grails.spring.BeanBuilder

import org.springframework.amqp.core.AmqpTemplate


def bb = new BeanBuilder()
//Windows (I hate windows for this)
bb.loadBeans("file:\\C:\\Users\\felgutie\\Documents\\GitHub\\spring-examples\\01-spring-camel\\src\\main\\resources\\META-INF\\spring\\SpringCamelAMQPBeans.groovy")
//Unix
//bb.loadBeans("classpath:/META-INF/spring/SpringCamelAMQPBeans.groovy")

def ctx = bb.createApplicationContext()
def camel = ctx.getBean("camelContext")

def template = camel.createProducerTemplate()
template.sendBody "direct:message", "Camel Ride for beginner"