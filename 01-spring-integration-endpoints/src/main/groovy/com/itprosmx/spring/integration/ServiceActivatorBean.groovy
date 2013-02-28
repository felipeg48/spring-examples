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
		log.info msg
	}
}
