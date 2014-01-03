package com.acton.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Role {
	public Role(String id, List<Permission> permList) {
		super();
		this.id = id;
		this.permList = permList;
	}
	private String id;
	private List<Permission> permList;
	
	public List<Permission> getPermList() {
		return permList;
	}
	public void setPermList(List<Permission> permList) {
		this.permList = permList;
	}
	public String toString()
		{
			String str = "Role:{id="+this.getId()+",permList="+this.permList+"}";
			return str;
		}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}


