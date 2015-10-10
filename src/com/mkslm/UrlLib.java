package com.mkslm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class UrlLib {
	
	public static JSONObject get(String url) throws IOException {
		URL balanceURL = new URL(url);
		URLConnection link = balanceURL.openConnection(); 
		BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
		String inputLine;
        inputLine = in.readLine();
        in.close();
        
        JSONObject balance = new JSONObject(inputLine);
        
		return balance;
	}
	
	public static JSONArray getJSONArray(String url) throws IOException {
		URL balanceURL = new URL(url);
		URLConnection link = balanceURL.openConnection(); 
		BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
		String inputLine;
		inputLine = in.readLine();
		in.close();
		
		JSONArray balance = new JSONArray(inputLine);
		
		return balance;
	}
	
	public static JSONObject post(String url, JSONObject data) {
	    HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

	    JSONObject result = null;
	    try {
	        HttpPost request = new HttpPost(url);
	        request.addHeader("Accept","application/json");
	        request.addHeader("Content-Type","application/json");
	        request.addHeader("dataType","json");
	        request.setEntity(new StringEntity(data.toString())); 
	        
	        try {  
	        	HttpResponse response = httpClient.execute(request);  
                HttpEntity entity = response.getEntity();
                result=new JSONObject(EntityUtils.toString(entity, "UTF-8"));
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  

	    }catch (Exception ex) {
            ex.printStackTrace(); 
	    } 
	    return result;
	}

	public static void main(String[] args){
		JSONObject data = new JSONObject();
		data.put("teamId", Constant.TEAMID);
		data.put("loanstobuy", new JSONArray("[ff8b09e5-eaa0-4ee2-bd06-d58efe2de5a4]"));
		System.out.println(post(Constant.LOANSBUYURL, data));
	}
}
