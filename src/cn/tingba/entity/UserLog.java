package cn.tingba.entity;

import java.sql.Timestamp;

/**
 * UserLog entity. @author MyEclipse Persistence Tools
 */

public class UserLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String action;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public UserLog() {
	}

	/** full constructor */
	public UserLog(User user, String action, Timestamp time) {
		this.user = user;
		this.action = action;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}