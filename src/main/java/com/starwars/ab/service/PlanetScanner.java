package com.starwars.ab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwars.ab.domain.PeopleScans;
import com.starwars.ab.domain.Person;

@Component
public class PlanetScanner extends Scanner {
	
	private final String FIRSTPAGE = "http://swapi.co/api/people/?format=json&page=1";

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
}
