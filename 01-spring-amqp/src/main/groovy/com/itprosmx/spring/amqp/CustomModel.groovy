/**
 * 
 */
package com.itprosmx.spring.amqp

import java.io.Serializable

/**
 * @author felipeg
 *
 */
@groovy.transform.ToString(includeNames=true)
class CustomModel implements Serializable {
	String name
	String phone
	Date birthday
}
