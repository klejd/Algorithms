package com.example.demo.domain;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ListOfTransaction {

	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private ArrayList<Transaction> latestTransactions = new ArrayList<Transaction>();
	
	public ListOfTransaction() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		addInBothTransactionList(new Transaction(10.05,dateFormat.format(date)));
		addInBothTransactionList(new Transaction(11.05,dateFormat.format(date)));
	}
	
	public void addInBothTransactionList(Transaction transaction) {
		transactions.add(transaction);
		latestTransactions.add(transaction);
	}
	
	public ArrayList<Transaction> getLatestTransactions() {
		return latestTransactions;
	}

	public void setLatestTransactions(ArrayList<Transaction> updatedTransactions) {
		this.latestTransactions = updatedTransactions;
	}

	

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public void addTransactions(Transaction transaction) {
		transactions.add(transaction);
	}
	
	
	public boolean deleteAll() {
		transactions.clear();
		latestTransactions.clear();
		return true;
	}
   
}
