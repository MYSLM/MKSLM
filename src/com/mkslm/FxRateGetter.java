package com.mkslm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class FxRateGetter {
	public static double getFxRate(String use, String buy) throws Exception {
		URL fxRate = new URL("http://192.168.2.5:2015/fx/" + use + buy);
		URLConnection link = fxRate.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
		String inputLine;
        inputLine = in.readLine();
        System.out.println(inputLine);
        in.close();
        
        JSONObject returnValue = new JSONObject(inputLine);
        JSONObject fxValue = returnValue.getJSONObject("fxValue");
        System.out.println(fxValue.toString());
        
		return fxValue.getDouble("fxRate");
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getFxRate("AUD", "USD"));
	}

}
