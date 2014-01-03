package com.acton.domain;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Permission {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;

	public Permission(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	 public String toString()
		{
			String str = "Permission:{id="+this.id+",name="+this.name+"}";
			return str;
		}
}



