/**
 * 
 */
package com.itprosmx.spring.integration

import groovy.transform.ToString

/**
 * @author felipeg
 *
 */
@ToString(includeFields=true,includePackage=false)
class SimpleModel {
	String name
	String phone
	Date birthday
}
