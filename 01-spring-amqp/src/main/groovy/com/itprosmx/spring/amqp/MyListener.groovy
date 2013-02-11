/**
 * 
 */
package com.itprosmx.spring.amqp

import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageListener

/**
 * @author felipeg
 *
 */
class MyListener implements MessageListener {

	private static final String DEFAULT_CHARSET = "UTF-8";
	
	@Override
	void onMessage(Message message) {
		assert message
		def msg = new String(message.body,DEFAULT_CHARSET)
		assert msg != null
		println msg
	}

}

