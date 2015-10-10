package com.mkslm;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class Loans {
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
	
	public static void main(String[] args) {
		System.out.print(getCurrentLoans().toString());

	}

}
