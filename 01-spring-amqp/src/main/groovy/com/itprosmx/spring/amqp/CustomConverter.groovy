/**
 * 
 */
package com.itprosmx.spring.amqp

import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.support.converter.MessageConversionException
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.stereotype.Component

/**
 * @author felipeg
 *
 */
@Component
class CustomConverter implements MessageConverter {


	@Override
	public Message toMessage(Object object, MessageProperties messageProperties)
	throws MessageConversionException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream()
		ObjectOutput out = new ObjectOutputStream(bos)
		out.writeObject(object)
		
		Message msg = new Message(bos.toByteArray(),messageProperties)
		
		out.close();
		bos.close();

		return msg;
	}


	@Override
	public Object fromMessage(Message message)
	throws MessageConversionException {
		 ByteArrayInputStream bis = new ByteArrayInputStream(message.body)
		 ObjectInput objectInput = new ObjectInputStream(bis)
		return objectInput.readObject();
	}
}
