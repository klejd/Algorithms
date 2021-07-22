package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.http.HttpServletResponse;

import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Stats;
import com.example.demo.domain.Transaction;
import com.example.demo.service.NewTransactionService;
import com.example.demo.service.StatsService;
import com.example.demo.service.ValidateJsonService;


@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {
	
	@Autowired
	NewTransactionService newTransactionService;
	
	@Autowired
	ValidateJsonService validateJsonService;
	
	@Autowired
	StatsService statsService;
	
	@PostMapping
	public synchronized  ResponseEntity<Object> saveTransaction(@RequestBody Transaction transaction) {
//		System.out.print(b);
		if(!validateJsonService.validate(transaction.getTime()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		if(!validateJsonService.validateTime(transaction.getTime()))
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		newTransactionService.addTransaction(transaction);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@DeleteMapping
	public synchronized ResponseEntity<Object> deleteAll(){
		boolean status =newTransactionService.deleteAll();
		if(status)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
	}
	
	@GetMapping
	public synchronized Stats getStats() {
		return statsService.calculateStats();
	}
	
}
