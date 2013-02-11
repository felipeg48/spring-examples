/**
 * 
 */
package com.itprosmx.spring.amqp

/**
 * @author felipeg
 *
 */
class MyListenerDelegate implements TextMessageContentDelegate{
	
	void onMessage(String text){
		assert text != null
		println text
	}
}

