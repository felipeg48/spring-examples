/**
 * 
 */
package com.itprosmx.spring.amqp.advance

import groovy.util.logging.Log

/**
 * @author fgutierrezcru
 *
 */
@Log
class MyMessageAdapter {

	void process(String message){
		log.info "Got it: $message"	
	}
}
