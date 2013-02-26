

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.Processor

def camelContext = new DefaultCamelContext()
camelContext.addRoutes(new RouteBuilder() {
	def void configure() {
		from("timer://jdkTimer?period=3000")
			.to("log://camelLogger?level=INFO")
			.process([ process: {  ex -> println("Hello World!") } ] as Processor)
	}
})
camelContext.start()
sleep 8000
camelContext.stop()
camelContext = null