package main.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;



public class Main {

	public static void main(String[] args) {

		try {
			postRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			getRequest();
		} catch (IOException e) {
			System.out.println("problem");
			e.printStackTrace();
		}


	}


	public static void getRequest() throws IOException {
		URL urlGet = new URL("http://localhost:8080/Messenger/webapi/messages");
		HttpURLConnection connection = (HttpURLConnection) urlGet.openConnection();
		connection.setRequestMethod("GET");
		int responseCode = connection.getResponseCode();
		String readLine = null;
		//	    System.out.println(response.toString());

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader In = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer response = new StringBuffer();
			while ((readLine = In.readLine()) != null) {
				response.append(readLine);
			}	
			In.close();
			System.out.println("Json String Result " + response.toString());

		}
		else {
			System.out.println("Get didnt work");
		}


	}
	public static void postRequest() throws IOException{


		JSONObject obj = new JSONObject();
		obj.put("author", "Michael Test");
		obj.put("message", "Hello are we going to make this JSON Work already?");
		//		System.out.println(obj);

		URL urlPost = new URL("http://localhost:8080/Messenger/webapi/messages");
		HttpURLConnection postConnection = (HttpURLConnection) urlPost.openConnection();
		postConnection.setRequestMethod("POST");
		postConnection.setRequestProperty("Michael", "asdf");
		postConnection.setRequestProperty("Content-Type", "application/json");


		postConnection.setDoOutput(true);
		OutputStream os = postConnection.getOutputStream();
		os.write(obj.toString().getBytes("UTF8"));
		os.close();

		int responseCode = postConnection.getResponseCode();
		System.out.println("POST Response Code :  " + responseCode);
		System.out.println("POST Response Message : " + postConnection.getResponseMessage());
		
		
		System.out.println(HttpURLConnection.HTTP_CREATED);
		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
			postConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			} 
			in.close();
			System.out.println(response.toString());
		} 
		else {
			System.out.println("POST NOT WORKED");
		}

	}
}