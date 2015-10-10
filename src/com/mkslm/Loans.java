package com.mkslm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;

public class Loans {
	private static int MAXTERM = 40;
	private static int MININTERESTRATE = 2;
	
	public static JSONArray getCurrentLoans() {
		JSONObject loanInfo = null;
		try {
			loanInfo = UrlLib.get(Constant.LOANSCURRENTURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loanInfo.getJSONArray("loans");
	}
	
	/**
	 * 
	 * @param loansToBuy, the id of the loan, like "ff8b09e5-eaa0-4ee2-bd06-d58efe2de5a4"
	 * @return If buy success will return true, else return false
	 */
	public static Boolean buyLoans(String loansToBuy) {
		JSONObject data = new JSONObject();
		Boolean result;
		data.put("teamId", Constant.TEAMID);
		data.put("loansToBuy", new JSONArray("['" + loansToBuy  + "']"));
		
		data = UrlLib.post(Constant.LOANSBUYURL, data);
		if (data.has("error")) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}
	
	public static ArrayList<JSONObject> queryLoan(long timeToLive){
		ArrayList<JSONObject> loansToBuy = new ArrayList<JSONObject>();
		JSONArray currentLoans = getCurrentLoans();
		int loansNum = currentLoans.length();
		
//		System.out.println(loansNum);
		
		for (int i = 0; i < loansNum; i++) {
			JSONObject loan = currentLoans.getJSONObject(i);
			if (loan.getDouble("termInMinutes") < ((double) timeToLive / 60000)) {
				loansToBuy.add(loan);
				System.out.println(loan.toString());
			}
		}
		
		Comparator<JSONObject> cmp = new Comparator<JSONObject>() {
			public int compare(JSONObject json1, JSONObject json2){
				int json1Inte = json1.getInt("interestRatePercent");
				int json2Inte = json2.getInt("interestRatePercent");
				int compare = json2Inte - json1Inte;
				
				if (compare == 0) {
					compare = json1.getInt("termInMinutes") - json2.getInt("termInMinutes");
				} 
				return compare;
			}
		};
		System.out.println(loansToBuy.toString());
		
		loansToBuy.sort(cmp);
		
		System.out.println(loansToBuy.toString());
		
		// Buy the shortest loan
//		if (loansToBuy.size() > 0) {
//			System.out.println(loansToBuy.get(0).toString());
//			buyLoans(loansToBuy.get(0).getString("id"));
//		} else {
//			System.out.println("No loan to buy");
//		}
		return loansToBuy;
		
		// Buy all the loans in loanToBuy
//		for (JSONObject object : loansToBuy) {
//			buyLoans(object.getString("id"));
//		}
	}
	
	public static void main(String[] args) {
		
	}
}
