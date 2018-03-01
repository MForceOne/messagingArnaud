package com.ig.rest.model;

public class BrokerDetails {
	private String url;
	private String username;
	private String password;
	private String destination;
	private boolean topic;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean isTopic() {
		return topic;
	}

	public void setTopic(boolean topic) {
		this.topic = topic;
	}

}
