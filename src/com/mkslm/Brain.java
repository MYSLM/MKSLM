package com.mkslm;

import java.util.List;

import org.json.JSONObject;

public class Brain {
	private static long expireTime=System.currentTimeMillis()+30*60*1000;
	public static void main(String[] args) {
		while (true) {

			NewsInf newInf = NewsManager.getNextNews();
			
			if (newInf != null) {
				String cur = newInf.currencyPair.substring(0, 3);
				try {
					AccountInf account = BalanceRetriever.getBalanceAcct();
					if (newInf.impact < 0) {
						Trader.trade(cur, "CSC", account.getValue(cur));
						List<JSONObject> loans=Loans.queryLoan(expireTime-System.currentTimeMillis());
						for(JSONObject o:loans) {
							if(BalanceRetriever.getBalanceAcct().csc>o.getInt("amount")) {
								Loans.buyLoans(o.getString("id"));
							}
							else
								break;
						}
					}
					else {
						if (cur.equals("AUD")) {
							Trader.trade("CSC", "AUD", account.csc);
							Trader.trade("USD", "AUD", account.usd);
							Trader.trade("SGD", "AUD", account.sgd);
							Trader.trade("EUR", "AUD", account.eur);
						} else if (cur.equals("USD")) {
							Trader.trade("CSC", "USD", account.csc);
							Trader.trade("AUD", "USD", account.aud);
							Trader.trade("SGD", "USD", account.sgd);
							Trader.trade("EUR", "USD", account.eur);
						} else if (cur.equals("SGD")) {
							Trader.trade("CSC", "SGD", account.csc);
							Trader.trade("AUD", "SGD", account.aud);
							Trader.trade("USD", "SGD", account.usd);
							Trader.trade("EUR", "SGD", account.eur);
						} else if (cur.equals("EUR")) {
							Trader.trade("CSC", "EUR", account.csc);
							Trader.trade("AUD", "EUR", account.aud);
							Trader.trade("USD", "EUR", account.usd);
							Trader.trade("SGD", "EUR", account.sgd);
						}
						
						// sleep
						long time = newInf.valueTime + newInf.windowMinutes
								* 60 * 1000 - System.currentTimeMillis() + 5000;
						if (time > 0)
							Thread.sleep(time);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			else {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
