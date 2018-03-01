package com.ig.xml.marshaller;

import java.io.StringWriter;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Service;

import com.ig.xml.model.Order;

@Service
public class OrderMarshaller {

	private JAXBContext context;
	
	@PostConstruct
	public void init() {
		try {
			context = JAXBContext.newInstance(Order.class);
		} catch (JAXBException e) {
			throw new RuntimeException("Could not instanciate JaxbContext");
		}
	}
	
	public String marshall(Order order) {
		StringWriter sw = new StringWriter();
		try {
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(order, sw);
		} catch (JAXBException e) {
			throw new RuntimeException("Could not marshall an order to xml");
		}
		return sw.toString();
	}

}
