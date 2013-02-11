/**
 * 
 */
package com.itprosmx.spring.amqp

/**
 * @author felipeg
 *
 */
interface TextMessageContentDelegate {
	void onMessage(String text)
}
