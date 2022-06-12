package com.kimyeyak.booking;

import java.sql.Timestamp;

public class BookingRuleDTO {
	private int id;
	private int storeId;
	private int minPeople;
	private int maxPeople;
	private int count;
	private Timestamp time;
	private String notice;
	
	//getters and setters
	
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getMinPeople() {
		return minPeople;
	}
	public void setMinPeople(int minPeople) {
		this.minPeople = minPeople;
	}
	public int getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	
}
