beans{
	 
	 xmlns rabbit:'http://www.springframework.org/schema/rabbit'
	 
	 rabbit.'connection-factory'(id:'connectionFactory', username:'guest', password:'guest', host:'localhost' )
	 rabbit.'template'(id:"amqpTemplate",'connection-factory':'connectionFactory')
	 rabbit.'admin'('connection-factory':'connectionFactory')
	 rabbit.'queue'(name:'myqueue')
	 
}