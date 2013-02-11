/**
 * 
 */
package com.itprosmx.spring.amqp

import static org.junit.Assert.fail

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author felipeg
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/amqp-listener-delegate-context.xml")
class ListenerDelgateTest {

	@Test
	void myListenerDelegate() {
		// Nothing to Test
		// Just check the amqp-listener-delegate-context.xml
	}

}
