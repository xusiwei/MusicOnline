package cn.tingba.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Song entity. @author MyEclipse Persistence Tools
 */

public class Song implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String name;
	private String singer;
	private String style;
	private String description;
	private String savepath;
	private Timestamp uploadTime;
	private Integer goodcount;
	private Integer badcount;
	private Integer playcount;
	private Integer comcount;
	private Set comments = new HashSet(0);
	
	@Override
	public boolean equals(Object obj) {
		Song rhs = (Song)obj;
		return (int)id == (int)rhs.id;
	}
	
	@Override
	public String toString() {
//		return String.format("%d %5s %5s %4s ", id, name, singer, style)
//				+ user.getId() + "|" + savepath + "|" + uploadTime
//				+ "|" + goodcount + "|" + badcount + "|" + playcount + "|"
//				+ comcount;
		
		return String.format( //JSON格式，方便客户端读取
				"{'id':'%d', 'name':'%s', 'singer':'%s', 'style':'%s', 'description':'%s', 'url': '%s'}",   
				id, name, singer, style, description, savepath);
	}	

	// Constructors

	/** default constructor */
	public Song() {
	}

	/** minimal constructor */
	public Song(User user, String name, String singer, String savepath) {
		this.user = user;
		this.name = name;
		this.singer = singer;
		this.savepath = savepath;
	}

	/** full constructor */
	public Song(User user, String name, String singer, String style,
			String description, String savepath, Timestamp uploadTime,
			Integer goodcount, Integer badcount, Integer playcount,
			Integer comcount, Set comments) {
		this.user = user;
		this.name = name;
		this.singer = singer;
		this.style = style;
		this.description = description;
		this.savepath = savepath;
		this.uploadTime = uploadTime;
		this.goodcount = goodcount;
		this.badcount = badcount;
		this.playcount = playcount;
		this.comcount = comcount;
		this.comments = comments;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinger() {
		return this.singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSavepath() {
		return this.savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Integer getGoodcount() {
		return this.goodcount;
	}

	public void setGoodcount(Integer goodcount) {
		this.goodcount = goodcount;
	}

	public Integer getBadcount() {
		return this.badcount;
	}

	public void setBadcount(Integer badcount) {
		this.badcount = badcount;
	}

	public Integer getPlaycount() {
		return this.playcount;
	}

	public void setPlaycount(Integer playcount) {
		this.playcount = playcount;
	}

	public Integer getComcount() {
		return this.comcount;
	}

	public void setComcount(Integer comcount) {
		this.comcount = comcount;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

}