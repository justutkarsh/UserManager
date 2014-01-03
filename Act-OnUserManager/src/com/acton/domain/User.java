package com.acton.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private String id;
	private List<Role> roleList;
	
	public User(String id, List<Role> roleList) {
		super();
		this.id = id;
		this.roleList = roleList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	 public String toString()
		{
			String str = "User:{id="+this.getId()+",roleList="+this.getRoleList()+"}";
			return str;
		}
}