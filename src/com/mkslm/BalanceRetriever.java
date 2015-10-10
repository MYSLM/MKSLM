package com.mkslm;

import org.json.JSONObject;

public class BalanceRetriever {
	public static JSONObject getBalanceJson() throws Exception {
		return UrlLib.get(Constant.BALANCEURL);
	}
	
	public static AccountInf getBalanceAcct() throws Exception {
		
        
        JSONObject balance = getBalanceJson();
        //System.out.println(balance.toString());
        AccountInf a = new AccountInf();
        a.aud=balance.getDouble("AUD");
        a.sgd=balance.getDouble("SGD");
        a.eur=balance.getDouble("EUR");
        a.usd=balance.getDouble("USD");
        a.csc=balance.getDouble("CSC");
		return a;
	}

	public static void main(String[] args) throws Exception {
		//getBalance();

	}

}
