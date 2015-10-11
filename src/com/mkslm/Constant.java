package com.mkslm;

public class Constant {
	public static String BASEURL = "http://128.199.74.105:2015/";
	
	// URL for get
	public static String TEAMID = "5f2e3bab-96ff-48f4-a546-526ef9945011";
	public static String BALANCEURL = BASEURL + "account/balance/" + TEAMID;
	public static String TRANSACTIONURL = BASEURL + "account/transactions/" + TEAMID;
	public static String LOANSCURRENTURL = BASEURL + "loans/current";
	public static String NEWSURL = BASEURL + "news/fx";
	public static String FXRATEURL = BASEURL + "fx/";
	
	//URL for post
	public static String LOANSBUYURL = BASEURL + "loans/buy";;
	public static String TRADEURL = BASEURL + "fx/quote";;
	public static String EXECTRADEURL = BASEURL + "fx/quote/execute";;

}
