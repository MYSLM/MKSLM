package com.mkslm;

public class AccountInf {
	double csc;
	double usd;
	double eur;
	double aud;
	double sgd;
	
	public double getValue(String cur){
		if(cur.equals("CSC")){
		    return csc;
		}else if(cur.equals("USD")){
			return usd;
		}else if(cur.equals("EUR")){
			return eur;
		}else if(cur.equals("AUD")){
			return aud;
		}else if(cur.equals("SGD")){
			return sgd;
		}else{
			return 0;
		}
	}
}
