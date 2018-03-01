package com.ig.xml.marshaller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ig.xml.model.Order;

public class OrderMarshallerTest {

	@Test
	public void testMarshall() {
		Order order = new Order();
		order.setAccount("account");
		order.setAction("action");
		order.setMarket("market");
		order.setReceivedAt("receivedAt");
		order.setSize("size");
		order.setSubmittedAt("submittedAt");
		OrderMarshaller orderMarshaller = new OrderMarshaller();
		orderMarshaller.init();
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Order><accont>account</accont><action>action</action><market>market</market><ReceivedAt>receivedAt</ReceivedAt><size>size</size><SubmittedAt>submittedAt</SubmittedAt></Order>",
				orderMarshaller.marshall(order));

	}
}
