/**
 * 
 */
package com.itprosmx.spring.integration

import groovy.util.logging.Log

/**
 * @author felipeg
 *
 */
@Log
class ServiceOne {

	void processModel(Model model){
		log.info "Model processing..."
		log.info model.toString()
		log.info "Model process done."
	}
}
