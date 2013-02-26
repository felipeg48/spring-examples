import grails.spring.BeanBuilder


def bb = new BeanBuilder()
bb.loadBeans("classpath:/META-INF/spring/SpringCamelBeans.groovy")

def ctx = bb.createApplicationContext()
sleep 10000