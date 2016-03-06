package com.khs.grokola.swagger.maven.plugin.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.maven.plugin.logging.Log;

public class GrokolaApiService {

	private String grokolaBaseUrl;
	private String grokolaAuthToken;
	private String userAgent = "GrokolaMavenPlugin/0.1 (+http://www.grokola.com)";
	private Log log;

	public GrokolaApiService(String grokolaBaseUrl, String grokolaAuthToken) {
		this.grokolaBaseUrl = grokolaBaseUrl;
		this.grokolaAuthToken = grokolaAuthToken;
	}

	private void logInfo(String info) {
		if (log != null) {
			log.info(info);
		}
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public void uploadSwaggerJson(String grokolaCategory, String jsonContent) throws IOException {
		// http://localhost:8080/swagger?category=testd&token=3a4f66ac-4793-4c1f-9c6d-4c5ee1afd0f1
		URL url = new URL(grokolaBaseUrl + "/swagger?category=" + grokolaCategory + "&token=" + grokolaAuthToken);

		// Build the connection
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", userAgent);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);

		// Write the body
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(jsonContent);
		wr.flush();
		wr.close();

		// Read the response
		int responseCode = connection.getResponseCode();
		logInfo("Sending 'POST' request to URL : " + url);
		logInfo("Post parameters : " + jsonContent);
		logInfo("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		logInfo(response.toString());
	}
}
