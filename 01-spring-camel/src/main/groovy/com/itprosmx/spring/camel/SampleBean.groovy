package com.itprosmx.spring.camel

/**
 * 
 * @author felipeg
 *
 */
class SampleBean {
	
	def run(){
		println "Hello Groovy Camel"
	}
	
	def message(msg){
		def result = new String(msg)
		println "Got: $result"
	}
}
