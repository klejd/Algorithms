package com.example.server.api;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.example.server.domain.Country;
import com.example.server.domain.CountryDetail;
import com.example.server.domain.CountryList;
import com.example.server.service.CountriesService;
@RunWith(SpringRunner.class)
@WebMvcTest(CountriesApi.class)
@ContextConfiguration(classes= {CountryList.class,CountriesService.class,Country.class,CountriesApi.class})
public class CountriesApiTest {

	
	@Autowired
	   private MockMvc mvc;

	   
	   @Mock
		CountriesService countriesServiceTest;
	   
	   @Autowired
	   @InjectMocks
	   CountriesApi countriesApi;
	   
	   Country c1,c2,c3,c4,c5;
		
		ArrayList<Country> countriesList;
		ArrayList<Country> similarCountriesList;

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
			
			similarCountriesList = new ArrayList<Country>();
			similarCountriesList.add(c1);
			similarCountriesList.add(c3);

		}
	@Test
	public void getAllTest() {
		when(countriesServiceTest.getAll()).thenReturn(countriesList);
	       try {
			mvc.perform( get("/api/countries"))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name", is("aName")))
            .andExpect(jsonPath("$[0].code", is("a")))
            .andExpect(jsonPath("$[0].capital", is("acapital")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	           
	}
	
	@Test
	public void similarCountriesTest() {
		
		when(countriesServiceTest.getSimilarCountries(Mockito.anyString(),Mockito.anyBoolean(),Mockito.anyBoolean(),Mockito.anyBoolean())).thenReturn(similarCountriesList);
	       try {
			(mvc.perform( get("/api/similarCountries")
			.param("countryCode", "as")
		     ))
			.andDo(print())
			.andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name", is("aName")))
            .andExpect(jsonPath("$[0].code", is("a")))
            .andExpect(jsonPath("$[0].capital", is("acapital")))
            .andExpect(jsonPath("$", hasSize(2)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	           
	}

}
