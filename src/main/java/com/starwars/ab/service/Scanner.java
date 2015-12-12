package com.starwars.ab.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class Scanner {
	
	private final String USERAGENT = "Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201";

	public String getPeopleJson(String page){
		RestTemplate rest = new RestTemplate();
		String response = rest.exchange(page,
		HttpMethod.GET,
		new HttpEntity<String>(getHttpHeaders()),
		String.class).getBody();

		return response;
	}
	
	public HttpHeaders getHttpHeaders(){
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", USERAGENT);
		return headers;
	}
}
