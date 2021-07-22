package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ListOfTransaction;
import com.example.demo.domain.Transaction;

@Service
@EnableScheduling
public class NewTransactionService {
	
	@Autowired
	ListOfTransaction listOfTransaction;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	@Scheduled(fixedDelay=1000)
	 public void updateTransaction() {
		ArrayList<Transaction> x=updateLatestTransactions();
		for(Transaction t: x)
			System.out.println(t.getTime());
	    }
	 
	 public String getDate() {
		 Date date = new Date();
		 dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return dateFormat.format(date);
	 }

//		@Scheduled(fixedDelay=5000)
	 public ArrayList<Transaction> getLatestTransactions() {
		ArrayList<Transaction> latestTransactions = updateLatestTransactions();
		return latestTransactions;
	}
	
	public void addTransaction(Transaction transaction) {
		listOfTransaction.addInBothTransactionList(transaction);
	}
	
	 public ArrayList<Transaction> updateLatestTransactions() {
		 ArrayList<Transaction> latestTransactions=listOfTransaction.getLatestTransactions();
		 latestTransactions=latestTransactions.stream()
				 .filter(x-> evaluateTime(x.getTime()))
				 .collect(Collectors 
                         .toCollection(ArrayList::new));
		listOfTransaction.setLatestTransactions(latestTransactions);
		return latestTransactions;
	 }

		public boolean evaluateTime(String time) {
			long diffSeconds = 0,diff;
			 try {
				Date date1=dateFormat.parse(time);
				Date date2=dateFormat.parse(getDate());
				diff = date2.getTime() - date1.getTime();
				diffSeconds = diff / 1000; 

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return diffSeconds<=60;
		}
		
		public boolean deleteAll() {
			listOfTransaction.deleteAll();
			return true;
		}
		
}
