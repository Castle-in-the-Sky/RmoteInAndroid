package com.audiomobile.data;

public class LearnButtonData {
	int id;
	String keyName;
	String name;
	String data;
	int valid;
	int btId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getBtId() {
		return btId;
	}
	public void setBtId(int btId) {
		this.btId = btId;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	public String getInfo() {
		return "id ---> " +  id
				+"keyName ---> " + keyName
				+"name ---> " + name
				+"data ---> " + data
				+"valid ---> " + valid
				+"btId ---> " + btId;
		
	}
}
