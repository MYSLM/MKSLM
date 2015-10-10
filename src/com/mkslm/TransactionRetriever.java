package com.mkslm;
import java.io.IOException;

import org.json.JSONArray;

public class TransactionRetriever {
	public static JSONArray getTransactions() throws IOException {
		return UrlLib.getJSONArray(Constant.TRANSACTIONURL);
	}

	public static void main(String[] args) throws IOException {
		System.out.println(getTransactions().toString());
	}

}
