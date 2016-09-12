package com.miltos.tutorials.messenger.model;

public class Link {
	
	/*
	 * The fields of the class ...
	 */
	private String link;
	private String rel;
	
	/*
	 * The constructors of the Link class ...
	 */
	public Link() {
		//Do nothing ...
	}
	
	public Link(String link, String rel) {
		super();
		this.link = link;
		this.rel = rel;
	}
	
	/*
	 * Getters and Setters ...
	 */
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}
}
