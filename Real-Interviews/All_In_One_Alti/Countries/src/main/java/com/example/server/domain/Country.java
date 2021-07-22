package com.example.server.domain;



import org.springframework.stereotype.Component;


@Component
public class Country {
	private static final long serialVersionUID = 1L;
	
	private String code;

	private String name;

	private String region;

	private String incomeLevel;

	private String lendingType;

	private String capital;
	
	private String longitude;
	
	private String latitude;
	


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

	public Country() {
		
	}
	
	public Country(String code,String name, String region,String incomelevel,String lendingType,String capital,String longitude,String latitude) {
		this.code=code;
		this.name=name;
		this.region=region;
		this.incomeLevel=incomelevel;
		this.lendingType=lendingType;
		this.capital=capital;
		this.longitude=longitude;
		this.latitude=latitude;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getIncomeLevel() {
		return incomeLevel;
	}

	public void setIncomeLevel(String incomeLevel) {
		this.incomeLevel = incomeLevel;
	}

	public String getLendingType() {
		return lendingType;
	}

	public void setLendingType(String lendingType) {
		this.lendingType = lendingType;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	
}