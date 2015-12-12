package com.starwars.ab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwars.ab.domain.PeopleScans;
import com.starwars.ab.domain.Person;

@Component
public class PlanetScanner {
	
	private final String FIRSTPAGE = "http://swapi.co/api/people/?format=json&page=1";
	private final String USERAGENT = "Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201";

	public List<Person> getAllPeople() {
		return getPeopleFromScan(getAllScannedPlanet());
	}

	private List<Person> getPeopleFromScan(List<PeopleScans> allScannedPlanet) {
		
		return allScannedPlanet
				.stream()
				.filter(s -> s != null)
				.flatMap(firstNode -> firstNode.getPerson().stream())
				.collect(Collectors.toList());
	}

	private List<PeopleScans> getAllScannedPlanet() {
		ObjectMapper mapper = new ObjectMapper();
		List<PeopleScans> jsonRequestsResult = new ArrayList<PeopleScans>();
		
		try {
			PeopleScans inicialScan = mapper.readValue(getPeopleJson(FIRSTPAGE), PeopleScans.class);
			jsonRequestsResult.add(inicialScan);
			String next = inicialScan.getNext();
			
			while(next != null){
				PeopleScans newScan = mapper.readValue(getPeopleJson(next), PeopleScans.class);
				jsonRequestsResult.add(newScan);
				next= newScan.getNext();
			}

		} catch (Exception e) {
			System.out.println("O império venceu " + e.getMessage());
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
		headers.set("User-Agent", USERAGENT);
		return headers;
	}
}
