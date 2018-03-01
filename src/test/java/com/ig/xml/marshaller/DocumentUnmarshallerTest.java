package com.ig.xml.marshaller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import com.ig.xml.model.Document;
import com.ig.xml.model.Order;

public class DocumentUnmarshallerTest {

	private DocumentUnmarshaller unmarshaller;
	
	@Before
	public void setUp() throws Exception {
		unmarshaller = new DocumentUnmarshaller();
		unmarshaller.init();

	}
	@Test
	public void test() {
		Path path = Paths.get("src/test/resources",
				"interview-test-orders-1.xml");

		Document document = unmarshaller.unmarshall(path.toFile());
		assertTrue(document.getOrders().size() == 2);
		Order order1 = document.getOrders().get(0);
		assertEquals(order1.getAccount(), "AX001");
		assertEquals(order1.getSubmittedAt(), "1507060723641");
		assertEquals(order1.getReceivedAt(), "1507060723642");
		assertEquals(order1.getMarket(), "VOD.L");
		assertEquals(order1.getAction(), "BUY");
		assertEquals(order1.getSize(), "100");

		Order order2 = document.getOrders().get(1);
		assertEquals(order2.getAccount(), "AX002");
		assertEquals(order2.getSubmittedAt(), "1507060723651");
		assertEquals(order2.getReceivedAt(), "1507060723652");
		assertEquals(order2.getMarket(), "VOD.L");
		assertEquals(order2.getAction(), "BUY");
		assertEquals(order2.getSize(), "200");
	}
	
	@Test
	public void testFail() {
		boolean excNotTriggered = true;
		Path path = Paths.get("src/test/resources",
				"interview-test-orders-XXX.xml");
		try {
			unmarshaller.unmarshall(path.toFile());
		} catch (Exception e) {
			assertEquals("Could not unmarshall the file",
					e.getMessage());
			excNotTriggered = false;
		}
		if (excNotTriggered) {
			fail("An exception should have been triggered");
		}
	}

	@Test
	public void testFail_notInit() {
		DocumentUnmarshaller unmarshaller = new DocumentUnmarshaller();
		boolean excNotTriggered = true;
		Path path = Paths.get("src/test/resources",
				"interview-test-orders-1.xml");
		try {
			unmarshaller.unmarshall(path.toFile());
		} catch (Exception e) {
			excNotTriggered = false;
		}
		if (excNotTriggered) {
			fail("An exception should have been triggered");
		}
	}

}
