package com.example.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.server.domain.Country;
import com.example.server.domain.CountryList;

@Service
public class CountriesService {
	int currentPage = 1;
	
	@Autowired
	CountryList countryList;
	
	
//	@PostConstruct
	public void loadWorldBankNationData() {
		RestTemplate template = new RestTemplate();
		int pages = Integer.MAX_VALUE;
		while(currentPage <= pages) {
			pages = loadDataFromAPI(template);
		}
	}

	private int loadDataFromAPI(RestTemplate template) {
		StringBuilder jsonString = new StringBuilder(template.getForObject("http://api.worldbank.org/v2/country?format=json&page=" + currentPage, String.class));
		
		JSONArray arr = new JSONArray(jsonString.toString());
		JSONObject info = (JSONObject) arr.get(0);

		((JSONArray) arr.get(1)).forEach(obj -> {
			Country country = createCountry((JSONObject) obj);
			countryList.addCountry(country);
		});
		currentPage++;
		return info.getInt("pages");
	}


	private Country createCountry(JSONObject o) {
		JSONObject region = o.getJSONObject("region");
		JSONObject incomeLevel = o.getJSONObject("incomeLevel");
		JSONObject lendingType = o.getJSONObject("lendingType");
 
		Country country = new Country(o.getString("iso2Code"),o.getString("name"), region.getString("value"),incomeLevel.getString("value"),lendingType.getString("value"),o.getString("capitalCity"),o.getString("longitude"),o.getString("latitude"));

		return country;
	}


	public ArrayList<Country> getSimilarCountries(String countryCode, boolean region, boolean incomeLevel, boolean lendingType) {
		ArrayList<Country> countriesList= countryList.getCountryList();
		Country country = countriesList.stream().filter(x->x.getCode().equals(countryCode)).findFirst().orElse(null);
		if(country==null) {
			return countriesList;
		}
		if(region)
			countriesList= FilterByRegion(countriesList,country.getRegion());
		if(incomeLevel)
			countriesList= FilterByIncomeLevel(countriesList,country.getIncomeLevel());
		if(lendingType)
			countriesList= FilterByLendingType(countriesList,country.getLendingType());
		
		return countriesList;

	}

	public ArrayList<Country> FilterByLendingType(ArrayList<Country> countriesList, String lendingType) {
		ArrayList<Country> countryList = countriesList.stream().filter(x->x.getLendingType().equals(lendingType)).collect(Collectors.toCollection(ArrayList::new)); 
		return countryList;
	}

	public ArrayList<Country> FilterByIncomeLevel(ArrayList<Country> countriesList, String incomeLevel) {
		ArrayList<Country> countryList = countriesList.stream().filter(x->x.getIncomeLevel().equals(incomeLevel)).collect(Collectors.toCollection(ArrayList::new)); 
		return countryList;
	}

	public ArrayList<Country> FilterByRegion(ArrayList<Country> countriesList, String region) {
		ArrayList<Country> countryList = countriesList.stream().filter(x->x.getRegion().equals(region)).collect(Collectors.toCollection(ArrayList::new)); 
		return countryList;
	}

	public ArrayList<Country> getAll() {
		return countryList.getCountryList();
	}


	

}
