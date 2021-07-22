package com.example.server.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.server.domain.Country;
import com.example.server.domain.CountryList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {CountryList.class,CountriesService.class,Country.class})
public class CountriesServiceTest {
	
	@Mock
	CountryList countryList;
	
	@Autowired
	@InjectMocks
	CountriesService countriesServiceTest;
	
	Country c1,c2,c3,c4,c5;
	String lendingType ;
	String incomelevel ;
	String region;
	
	ArrayList<Country> countriesList;
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);

		 c1 = new Country("a", "aName", "aRegion", "aincomelevel", "alendingType", "acapital", "1", "1");
		 c2 = new Country("b", "bName", "bRegion", "bincomelevel", "blendingType", "bcapital", "2", "2");
		 c3 = new Country("c", "cName", "aRegion", "aincomelevel", "alendingType", "ccapital", "3", "3");
		 c4 = new Country("d", "dName", "bRegion", "aincomelevel", "blendingType", "dcapital", "4", "4");
		 c5 = new Country("e", "eName", "aRegion", "aincomelevel", "blendingType", "ecapital", "5", "5");

		countriesList = new ArrayList<Country>();
		countriesList.add(c1);
		countriesList.add(c2);
		countriesList.add(c3);
		countriesList.add(c4);
		countriesList.add(c5);

		lendingType = "alendingType";
		incomelevel = "aincomelevel";
		region = "aRegion";

	}

	@Test
	public void FilterByLendingTypeTest() {
		ArrayList<Country> filteredCountries=countriesServiceTest.FilterByLendingType(countriesList,lendingType);
		assertFalse(filteredCountries.isEmpty());
		assertThat(filteredCountries.size()).isEqualTo(2);	
		assertTrue(filteredCountries.containsAll(Arrays.asList(c1,c3)));
		}
	
	@Test
	public void FilterByIncomeLevelTest() {
		ArrayList<Country> filteredCountries=countriesServiceTest.FilterByIncomeLevel(countriesList, incomelevel);
		assertFalse(filteredCountries.isEmpty());
		assertThat(filteredCountries.size()).isEqualTo(4);	
		assertTrue(filteredCountries.containsAll(Arrays.asList(c1,c3,c4,c5)));

	}
	
	@Test
	public void FilterByRegionTest() {
		ArrayList<Country> filteredCountries=countriesServiceTest.FilterByRegion(countriesList, region);
		assertFalse(filteredCountries.isEmpty());
		assertThat(filteredCountries.size()).isEqualTo(3);	
		assertTrue(filteredCountries.containsAll(Arrays.asList(c1,c3,c5)));

	}
	
	@Test
	public void getSimilarCountriesTest_WithoutFilter() {
		when(countryList.getCountryList()).thenReturn(countriesList);
		ArrayList<Country> filteredCountries=countriesServiceTest.getSimilarCountries("a", false, false, false);
		assertFalse(filteredCountries.isEmpty());
		assertThat(filteredCountries.size()).isEqualTo(5);	
		assertTrue(filteredCountries.containsAll(Arrays.asList(c1,c2,c3,c4,c5)));


	}
	
	@Test
	public void getSimilarCountriesTest_WithRegionFilter() {
		when(countryList.getCountryList()).thenReturn(countriesList);
		ArrayList<Country> filteredCountries=countriesServiceTest.getSimilarCountries("a", true, false, false);
		assertFalse(filteredCountries.isEmpty());
		assertThat(filteredCountries.size()).isEqualTo(3);	
		assertTrue(filteredCountries.containsAll(Arrays.asList(c1,c3,c5)));

	}
	
	@Test
	public void getSimilarCountriesTest_WithIncomeFilter() {
		when(countryList.getCountryList()).thenReturn(countriesList);
		ArrayList<Country> filteredCountries=countriesServiceTest.getSimilarCountries("a", false, true, false);
		assertFalse(filteredCountries.isEmpty());
		assertThat(filteredCountries.size()).isEqualTo(4);	
		assertTrue(filteredCountries.containsAll(Arrays.asList(c1,c3,c4,c5)));


	}
	@Test
	public void getSimilarCountriesTest_WithLendingFilter() {
		when(countryList.getCountryList()).thenReturn(countriesList);
		ArrayList<Country> filteredCountries=countriesServiceTest.getSimilarCountries("a", false, false, true);
		assertFalse(filteredCountries.isEmpty());
		assertThat(filteredCountries.size()).isEqualTo(2);	
		assertTrue(filteredCountries.containsAll(Arrays.asList(c1,c3)));


	}
	
	@Test
	public void getSimilarCountriesTest_WithRegionAndIncomeFilter() {
		when(countryList.getCountryList()).thenReturn(countriesList);
		ArrayList<Country> filteredCountries=countriesServiceTest.getSimilarCountries("a", true, true, false);
		assertFalse(filteredCountries.isEmpty());
		assertThat(filteredCountries.size()).isEqualTo(3);	
		assertTrue(filteredCountries.containsAll(Arrays.asList(c1,c3,c5)));


	}
	
	@Test
	public void getSimilarCountriesTest_WithRegionAndLendingFilter() {
		when(countryList.getCountryList()).thenReturn(countriesList);
		ArrayList<Country> filteredCountries=countriesServiceTest.getSimilarCountries("a", true, false, true);
		assertFalse(filteredCountries.isEmpty());
		assertThat(filteredCountries.size()).isEqualTo(2);	
		assertTrue(filteredCountries.containsAll(Arrays.asList(c1,c3)));


	}
	
	@Test
	public void getSimilarCountriesTest_WithIncomeAndLendingFilter() {
		when(countryList.getCountryList()).thenReturn(countriesList);
		ArrayList<Country> filteredCountries=countriesServiceTest.getSimilarCountries("a", false, true, true);
		assertFalse(filteredCountries.isEmpty());
		assertThat(filteredCountries.size()).isEqualTo(2);	
		assertTrue(filteredCountries.containsAll(Arrays.asList(c1,c3)));


	}
	
	@Test
	public void getSimilarCountriesTest_WithAllFilter() {
		when(countryList.getCountryList()).thenReturn(countriesList);
		ArrayList<Country> filteredCountries=countriesServiceTest.getSimilarCountries("a", true, true, true);
		assertFalse(filteredCountries.isEmpty());
		assertThat(filteredCountries.size()).isEqualTo(2);	
		assertTrue(filteredCountries.containsAll(Arrays.asList(c1,c3)));


	}

}
