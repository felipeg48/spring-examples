beans{
	
	sample(com.itprosmx.spring.camel.SampleBean)
	
 	xmlns camel: 'http://camel.apache.org/schema/spring'
 	camel {
		 camelContext(id:'camelContext', trace:true) {
			camel.'route'{
				camel.'from'(uri:'direct://message')
				camel.'to'(uri:'spring-amqp:my.direct.exchange:my.direct.queue:here.*?type=directc&durable=true&autodelete=false') {}
			} 
			camel.'route'{
				camel.'from'(uri:'spring-amqp:my.direct.exchange:my.direct.queue:here.*?type=directc&durable=true&autodelete=false')
				camel.'to'(uri:"bean://sample?method=message") {}
			}
		 }
	 }
	 
	 xmlns rabbit:'http://www.springframework.org/schema/rabbit'
	 
	 rabbit.'connection-factory'(id:'connectionFactory', username:'guest', password:'guest', host:'localhost' )
	 rabbit.'template'(id:"amqpTemplate",'connection-factory':'connectionFactory')
	 rabbit.'admin'('connection-factory':'connectionFactory')
	 
}