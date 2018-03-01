package com.ig.xml.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Order")
public class Order {

	private String account;

	private String submittedAt;

	private String receivedAt;

	private String market;

	private String action;

	private String size;

	@XmlElement(name = "accont")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@XmlElement(name = "SubmittedAt")
	public String getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(String submittedAt) {
		this.submittedAt = submittedAt;
	}

	@XmlElement(name = "ReceivedAt")
	public String getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(String receivedAt) {
		this.receivedAt = receivedAt;
	}

	@XmlElement(name = "market")
	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	@XmlElement(name = "action")
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@XmlElement(name = "size")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
