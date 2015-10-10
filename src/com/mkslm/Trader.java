package com.mkslm;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Trader {

	public static String TRADING_ADDR=Constant.TRADEURL;
	public static String CONFIRM_ADDR=Constant.EXECTRADEURL;
	public static String preTrade(String use, String buy, double amount) {
		 // 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 创建httppost    
        HttpPost httppost = new HttpPost(TRADING_ADDR);  
        httppost.setHeader("Accept","application/json");
        httppost.setHeader("Content-Type","application/json");
        httppost.setHeader("dataType","json");
        JSONObject object=new JSONObject();
        object.put("teamId", Constant.TEAMID);
        object.put("currencyPair", use+buy);
        object.put("quantity", amount+"");
        try {  
            httppost.setEntity(new StringEntity(object.toString()));  
            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();
                JSONObject result=new JSONObject(EntityUtils.toString(entity, "UTF-8"));
                String quoteId=result.getJSONObject("quoteResponse").getString("quoteId");
                return quoteId;
//                if (entity != null) {  
//                    System.out.println("--------------------------------------");  
//                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
//                    System.out.println("--------------------------------------");  
//                }  
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
		return null;  
    }  
	public static void confirmTrade(String quoteId) {
		CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 创建httppost    
        HttpPost httppost = new HttpPost(CONFIRM_ADDR);  
        httppost.setHeader("Accept","application/json");
        httppost.setHeader("Content-Type","application/json");
        httppost.setHeader("dataType","json");
        JSONObject object=new JSONObject();
        object.put("teamId", "44c83d2a-2e69-412f-9143-4a6adcb2a89a");
        object.put("quoteId", quoteId);
        try {  
            httppost.setEntity(new StringEntity(object.toString()));  
            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();
                JSONObject result=new JSONObject(EntityUtils.toString(entity, "UTF-8"));
                System.out.println(result);
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
	}
	public static void trade(String use, String buy, double amount) {
		confirmTrade(preTrade(use, buy, amount));
	}
	public static void main(String[] args) {
		String id=Trader.preTrade("CSC","AUD", 1);
		System.out.println(id);
		Trader.confirmTrade(id);
	}
}
