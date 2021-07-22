package com.example.server.domain;

import java.util.ArrayList;

import org.springframework.stereotype.Component;


@Component
public class CountryList {
	
	private ArrayList<Country> countryList = new ArrayList<>();

	public ArrayList<Country> getCountryList() {
		return countryList;
	}
	
	public void addCountry(Country country) {
		this.countryList.add(country);
	}
}
