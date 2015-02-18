/**
 * 
 */
package com.itprosmx.spring.amqp

import org.springframework.amqp.core.Message
import org.springframework.stereotype.Component

/**
 * @author fgutierrezcru
 *
 */
@Component
class CustomListener {
	void process(CustomModel model){
		println "Received: $model"
	}
}
