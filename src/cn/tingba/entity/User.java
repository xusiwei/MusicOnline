package cn.tingba.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String sex;
	private String email;
	private Integer level;
	private Integer space;
	private Integer used;
	private Set userLogs = new HashSet(0);
	private Set songs = new HashSet(0);
	private Set comments = new HashSet(0);
	
	public String toString() {
		return String.format("%4d %6s %6s %4s %6s", id, username, password,
				sex, email);
	}	

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/** full constructor */
	public User(String username, String password, String sex, String email,
			Integer level, Integer space, Integer used, Set userLogs,
			Set songs, Set comments) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.email = email;
		this.level = level;
		this.space = space;
		this.used = used;
		this.userLogs = userLogs;
		this.songs = songs;
		this.comments = comments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSpace() {
		return this.space;
	}

	public void setSpace(Integer space) {
		this.space = space;
	}

	public Integer getUsed() {
		return this.used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Set getUserLogs() {
		return this.userLogs;
	}

	public void setUserLogs(Set userLogs) {
		this.userLogs = userLogs;
	}

	public Set getSongs() {
		return this.songs;
	}

	public void setSongs(Set songs) {
		this.songs = songs;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

}