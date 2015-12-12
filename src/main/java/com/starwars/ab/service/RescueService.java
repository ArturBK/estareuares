package com.starwars.ab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwars.ab.model.People;

@Service
public class RescueService {
	
	private final String FIRSTPAGE = "http://swapi.co/api/people/?format=json&page=1";

	public List<People> rescuePeople(){
		
		ObjectMapper mapper = new ObjectMapper();
		List<People> jsonRequestsResult = new ArrayList<People>();
		
		try {
			People people = mapper.readValue(getPeopleJson(FIRSTPAGE), People.class);
			jsonRequestsResult.add(people);
			
			if (people.getNext() != null)
				jsonRequestsResult.add(
						mapper.readValue(getPeopleJson(people.getNext()), People.class));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return jsonRequestsResult;
	}
	
	private String getPeopleJson(String page){
		
		RestTemplate rest = new RestTemplate();

		String response = rest.exchange(page,
		HttpMethod.GET,
		new HttpEntity<String>(getHttpHeaders()),
		String.class).getBody();

		return response;
	}
	
	public HttpHeaders getHttpHeaders()
	{
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201");
		return headers;
	}
}