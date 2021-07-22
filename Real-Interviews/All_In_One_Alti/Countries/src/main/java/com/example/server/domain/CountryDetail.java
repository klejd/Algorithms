package com.example.server.domain;

public class CountryDetail{
	String code;
	String name;
	String capital;
	String longitude;
	String latitude;
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public CountryDetail(String code, String name, String capital, String longitude, String latitude) {
		super();
		this.code = code;
		this.name = name;
		this.capital = capital;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public CountryDetail() {
		
	}
	
	public CountryDetail(String code,String name, String capital) {
		super();
		this.code=code;
		this.name = name;
		this.capital = capital;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}