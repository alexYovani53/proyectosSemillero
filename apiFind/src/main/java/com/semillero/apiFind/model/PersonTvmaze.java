package com.semillero.apiFind.model;

import java.util.List;

public class PersonTvmaze {

	private String name;
	private String country;
	private String birthday;
	
	private List<mediaItunes> medias;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public List<mediaItunes> getMedias() {
		return medias;
	}

	public void setMedias(List<mediaItunes> medias) {
		this.medias = medias;
	}
	
	
	
}
