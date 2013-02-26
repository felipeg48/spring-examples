

import grails.spring.BeanBuilder


def bb = new BeanBuilder()
bb.loadBeans("classpath:/META-INF/spring/SpringBeans.groovy")

def ctx = bb.createApplicationContext()

def s = ctx.getBean("sample")
println s
