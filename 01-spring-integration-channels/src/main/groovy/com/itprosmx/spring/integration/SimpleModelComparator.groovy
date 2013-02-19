/**
 * 
 */
package com.itprosmx.spring.integration

import org.springframework.integration.Message

/**
 * @author felipeg
 *
 */
class SimpleModelComparator implements Comparator<Message<SimpleModel>> {

	@Override
	int compare(Message<SimpleModel> model, Message<SimpleModel> otherModel) {
		//Compare the models based on the Date. Seniors must be first
		return model.payload.birthday.compareTo(otherModel.payload.birthday)
	}

}
