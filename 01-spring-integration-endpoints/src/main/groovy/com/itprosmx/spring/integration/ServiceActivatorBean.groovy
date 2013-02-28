/**
 * 
 */
package com.itprosmx.spring.integration

import groovy.util.logging.Log4j;

/**
 * @author felipeg
 *
 */
@Log4j
class ServiceActivatorBean {

	void process(msg){
		log.info "Processing..."
		log.info "Message: $msg"
	}
	
	def processReply(msg){
		log.info "Processing and Replying..."
		log.info "Message: $msg"
		
		msg.tag = "HELLO WORLD!"
		
		log.info "Sending reply with > tag:${msg.tag}"
		log.info msg
		
		msg
	}
}
