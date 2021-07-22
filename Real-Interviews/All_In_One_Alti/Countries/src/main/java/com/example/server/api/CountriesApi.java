package com.example.server.api;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.domain.Country;
import com.example.server.domain.CountryDetail;
import com.example.server.domain.CountryList;
import com.example.server.service.CountriesService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CountriesApi {
	
	@Autowired
	CountriesService countriesService;
	
	@GetMapping("/countries")
	public ArrayList<CountryDetail> getAll() {
		ArrayList<Country> countryList=countriesService.getAll();
		System.out.println("hewew----->> "+countryList);
		ArrayList<CountryDetail> countryDetail=countryList.stream().map(x->new CountryDetail(x.getCode(),x.getName(),x.getCapital())).filter(x->!x.getCapital().isEmpty()).collect(Collectors.toCollection(ArrayList::new));
		return countryDetail;
	}
	@GetMapping("/similarCountries")
	public ArrayList<CountryDetail> similarCountries(@RequestParam(required=true) String countryCode,@RequestParam(required=false) boolean region,@RequestParam(required=false) boolean incomeLevel,@RequestParam(required=false) boolean lendingType) {
		ArrayList<Country> countryList=countriesService.getSimilarCountries(countryCode,region,incomeLevel,lendingType);
		ArrayList<CountryDetail> countryDetail=countryList.stream().map(x->new CountryDetail(x.getCode(),x.getName(),x.getCapital(),x.getLongitude(),x.getLatitude())).filter(x->!x.getCapital().isEmpty()).collect(Collectors.toCollection(ArrayList::new));
		return countryDetail;
	}
	
}
