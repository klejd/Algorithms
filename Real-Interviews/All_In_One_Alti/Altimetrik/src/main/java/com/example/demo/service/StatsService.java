package com.example.demo.service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Stats;
import com.example.demo.domain.Transaction;

@Service
public class StatsService {
	
	@Autowired
	NewTransactionService newTransactionService;
	
	
	public Stats calculateStats() {
		Stats stats=new Stats();
		ArrayList<Transaction> listOfTransaction = newTransactionService.getLatestTransactions();
		ArrayList<Double> listOfAmounts=getListOfAmounts(listOfTransaction);
		if(listOfTransaction.size()<1)
			return stats;
		stats.setSum(calculateSum(listOfAmounts));
		stats.setAvg(calculateAvg(listOfAmounts));
		stats.setMax(calculateMax(listOfAmounts));
		stats.setMin(calculateMin(listOfAmounts));
		stats.setCount(listOfAmounts.size());
		return stats;
		
	}
	
	private ArrayList<Double> getListOfAmounts(ArrayList<Transaction> listOfTransaction){
		return listOfTransaction.stream().map(x-> x.getAmount())
				.collect(Collectors 
                         .toCollection(ArrayList::new));
	}
	private double calculateSum(ArrayList<Double> listOfAmounts) {
		return listOfAmounts.stream().mapToDouble(Double::doubleValue).sum();
	}
	private double calculateAvg(ArrayList<Double> listOfAmounts) {
		return listOfAmounts.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
	}
	private double calculateMax(ArrayList<Double> listOfAmounts) {
		return listOfAmounts.stream().mapToDouble(Double::doubleValue).max().getAsDouble();
	}
	private double calculateMin(ArrayList<Double> listOfAmounts) {
		return listOfAmounts.stream().mapToDouble(Double::doubleValue).min().getAsDouble();
	}
	
}
