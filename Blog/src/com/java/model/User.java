package com.java.model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class User {
	private int userId;
	private String userName;
	private String password;
	private String motto;
	private int points=0;
	private Date registerDate;
	private int years=0;
	/**
	 * @return the userId
	 */
	public User(){
		
	}
	public User(String userName,String password){
		this.userName=userName;
		this.password=password;
	}
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the motto
	 */
	public String getMotto() {
		return motto;
	}
	/**
	 * @param motto the motto to set
	 */
	public void setMotto(String motto) {
		this.motto = motto;
	}
	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	/**
	 * @return the registerDate
	 */
	public Date getRegisterDate() {
		return registerDate;
	}
	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	/**
	 * @return the years
	 */
	public int getYears() {
		/*
		LocalDate today=LocalDate.now();
		int year=today.getYear();
		int month=today.getMonthValue();
		int day=today.getDayOfMonth();
		Date rDate=this.getRegisterDate();
		Calendar cal=Calendar.getInstance();
		cal.setTime(rDate);
		*
		*/
		return years;
	}
	/**
	 * @param years the years to set
	 */
	public void setYears(int years) {
		this.years = years;
	}
	
}
