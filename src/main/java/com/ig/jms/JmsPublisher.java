package com.ig.jms;

import java.util.List;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.ig.rest.model.BrokerDetails;
import com.ig.xml.marshaller.OrderMarshaller;
import com.ig.xml.model.Order;

@Service
public class JmsPublisher {

	@Autowired
	OrderMarshaller orderMarshaller;

	public void send(BrokerDetails brokerDetails, List<Order> orders) {
		JmsTemplate jmsTemplate = getJmsTemplate(brokerDetails);
		for (Order order : orders) {
			jmsTemplate.convertAndSend(orderMarshaller.marshall(order));
		}
	}

	private JmsTemplate getJmsTemplate(BrokerDetails brokerDetails) {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(getConnectionFactory(brokerDetails));
		template.setPubSubDomain(brokerDetails.isTopic());
		template.setDefaultDestinationName(brokerDetails.getDestination());
		return template;
	}

	private ConnectionFactory getConnectionFactory(BrokerDetails brokerDetails) {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(brokerDetails.getUrl());
		connectionFactory.setUserName(brokerDetails.getUsername());
		connectionFactory.setPassword(brokerDetails.getPassword());
		return connectionFactory;
	}

}
