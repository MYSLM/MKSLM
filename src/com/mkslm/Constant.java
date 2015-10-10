package com.mkslm;

public class Constant {
	public static String BASEURL = "http://128.199.74.105:2015/";
	
	// URL for get
	public static String TEAMID = "44c83d2a-2e69-412f-9143-4a6adcb2a89a";
	public static String BALANCEURL = BASEURL + "account/balance/" + TEAMID;
	public static String TRANSACTIONURL = BASEURL + "account/transactions/" + TEAMID;
	public static String LOANSCURRENTURL = BASEURL + "loans/current";
	public static String NEWSURL = BASEURL + "news/fx";
	
	//URL for post
	public static String LOANSBUYURL = BASEURL + "loans/buy";;
	public static String TRADEURL = BASEURL + "fx/quote";;
	public static String EXECTRADEURL = BASEURL + "fx/quote/execute";;

}
