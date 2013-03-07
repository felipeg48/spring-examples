package com.itprosmx.spring.camel

import groovy.util.logging.Log4j

/**
 * 
 * @author felipeg
 *
 */
@Log4j
class SampleBean {
	
	def run(){
		log.info "Hello Groovy Camel"
	}
	
	def message(msg){
		def result = new String(msg)
		log.info "Got: $result"
	}
}
