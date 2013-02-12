/**
 * 
 */
package com.itprosmx.spring.integration

import groovy.util.logging.Log4j

/**
 * @author felipeg
 *
 */
@Log4j
class ServiceOne {

	void processModel(Model model){
		log.info "Model processing..."
		log.info model.toString()
		log.info "Model process done."
	}
}
