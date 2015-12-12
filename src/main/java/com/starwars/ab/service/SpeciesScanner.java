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
import com.starwars.ab.domain.Specie;
import com.starwars.ab.domain.SpeciesScan;

@Component
public class SpeciesScanner {
	
	private final String FIRSTPAGE = "http://swapi.co/api/species/?page=1&format=json";
	private final String USERAGENT = "Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201";
	
	
	public List<Specie> allSpecies(){
		return getSpeciesFromScan(getAllScan());
	}
	
	private List<Specie> getSpeciesFromScan(List<SpeciesScan> allScanned) {
		
		return allScanned
				.stream()
				.filter(s -> s != null)
				.flatMap(firstNode -> firstNode.getSpecies().stream())
				.collect(Collectors.toList());
	}
	
	private List<SpeciesScan> getAllScan() {
		ObjectMapper mapper = new ObjectMapper();
		List<SpeciesScan> jsonRequestsResult = new ArrayList<SpeciesScan>();
		
		try {
			SpeciesScan specie = mapper.readValue(getPeopleJson(FIRSTPAGE), SpeciesScan.class);
			jsonRequestsResult.add(specie);
			String next = specie.getNext();
			
			while(next != null){
				System.out.println(next);

				SpeciesScan newSpecie = mapper.readValue(getPeopleJson(next), SpeciesScan.class);
				jsonRequestsResult.add(newSpecie);
				next= newSpecie.getNext();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonRequestsResult;
	}
	
	private String getPeopleJson(String page){
		System.out.println("getPeopleJson");

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
