package com.mkslm;
import java.net.*;
import java.io.*;
import org.json.*;
import java.util.*;


public class NewsRetriever {
	static boolean flag=true;
	//get the news
    public ArrayList<NewsInf> getNews(){
        String news="";
    	try{
    		URL url= new URL("http://128.199.74.105:2015/news/fx");
    		HttpURLConnection conn=(HttpURLConnection)url.openConnection();
    		conn.setDoOutput(true);
    		conn.setRequestMethod("GET");
    		BufferedReader in =new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
    		String line;
    		while((line=in.readLine())!= null){
    			news +=line;
    		}
    	}catch(Exception e){
    		System.out.println(e);
    	}
    	return splitNews(news);
    }
    //split the news
    public ArrayList<NewsInf> splitNews(String news){
    	JSONObject o= new JSONObject(news);
    	JSONObject oc= new JSONObject();
        JSONArray n = o.getJSONArray("news");
        ArrayList<NewsInf> newsArray = new ArrayList<NewsInf>();

        for(int i=0;i<n.length();i++){
        	oc=n.getJSONObject(i);
        	//System.out.println(oc);
            NewsInf inf = new NewsInf();
        	inf.currencyPair=oc.getString("currencyPair");
            inf.valueTime=oc.getLong("valueTime");
        	inf.impact=oc.getDouble("impact");
        	inf.windowMinutes=oc.getInt("windowMinutes");
        	//System.out.println("1:"+currencyPair+" 2:"+impact+" 3:"+windowMinutes+" 4:"+valueTime);
            newsArray.add(inf);
        } 
        return newsArray;
    }
    
    //run getNews per 1s
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewsRetriever n = new NewsRetriever();
        for(NewsInf news:n.getNews()) {
        	System.out.println(news);
        }
	}

}
