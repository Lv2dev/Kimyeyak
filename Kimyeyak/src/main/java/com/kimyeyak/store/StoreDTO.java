package com.kimyeyak.store;

import java.sql.Date;

public class StoreDTO {
	private int storeId;
	private String memberId;
	private int category;
	private String notice;
	private String tel;
	private String thumb;
	private int openTime;
	private int closeTime;
	private int restDay;
	private int braketimeStart;
	private int braketimeEnd;
	private Date joinDay;
	private String storeName;
	private int status;
	private String address;
	private double addressX;
	private double addressY;
	
	//getters and setters
	
	
	public int getCategory() {
		return category;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public int getOpenTime() {
		return openTime;
	}
	public void setOpenTime(int openTime) {
		this.openTime = openTime;
	}
	public int getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(int closeTime) {
		this.closeTime = closeTime;
	}
	public int getRestDay() {
		return restDay;
	}
	public void setRestDay(int restDay) {
		this.restDay = restDay;
	}
	public int getBraketimeStart() {
		return braketimeStart;
	}
	public void setBraketimeStart(int braketimeStart) {
		this.braketimeStart = braketimeStart;
	}
	public int getBraketimeEnd() {
		return braketimeEnd;
	}
	public void setBraketimeEnd(int braketimeEnd) {
		this.braketimeEnd = braketimeEnd;
	}
	public Date getJoinDay() {
		return joinDay;
	}
	public void setJoinDay(Date joinDay) {
		this.joinDay = joinDay;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getAddressX() {
		return addressX;
	}
	public void setAddressX(double addressX) {
		this.addressX = addressX;
	}
	public double getAddressY() {
		return addressY;
	}
	public void setAddressY(double addressY) {
		this.addressY = addressY;
	}
	
	
}
