package com.itprosmx.spring.amqp

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author felipeg
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/amqp-listener-context.xml")
public class ListenerTest {

	@Test
	public void myListener() {
		// Nothing to Test 
		// Just check the amqp-listener-context.xml
		
	}

}
