package com.example.demo.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Transaction;

@Service
public class ValidateJsonService {
	
	@Autowired
	NewTransactionService newTransactionService;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	public boolean validate(String time) {
		try {
			dateFormat.parse(time);
			} catch (Exception ex) {
				return false;			
		}
		return true;
		
	}
	
	public boolean validateTime(String time) {
		return newTransactionService.evaluateTime(time);
	}

}
