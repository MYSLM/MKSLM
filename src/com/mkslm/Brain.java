package com.mkslm;

import java.util.ArrayList;
import org.json.*;

public class Brain {

	public static void main(String[] args) {
		while (true) {
			NewsInf newInf = NewsManager.getNextNews();
			System.out.println();
			if(newInf!=null){
			String cur = newInf.currencyPair.substring(0, 3);
			// 获取当前余额
			try {
				AccountInf account = BalanceRetriever.getBalanceAcct();
				// 卖出当前货币至csc
				if (newInf.impact < 0) {
					Trader.trade(cur, "CSC", account.getValue(cur));
				}
				// 所有其他货币买入当前货币
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
					long time = newInf.valueTime + newInf.windowMinutes * 60 * 1000 - System.currentTimeMillis() + 5000;
					Thread.sleep(time);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}}

}
