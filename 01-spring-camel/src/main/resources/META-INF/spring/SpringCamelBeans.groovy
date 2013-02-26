
beans{
	
	sample(com.itprosmx.spring.camel.SampleBean)
	
 	xmlns camel: 'http://camel.apache.org/schema/spring'
 	camel {
		 camelContext(id:'camelContext', trace:true) {
			camel.'route'{
				camel.'from'(uri:'timer://jdkTimer?period=3000') {}
				camel.'to'(uri:'log://camelLogger?level=INFO') {}
				camel.'to'(uri:"bean://sample?method=run") {}
				
			} 
		 }
	 }
}