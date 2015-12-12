package com.starwars.ab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwars.ab.domain.Specie;
import com.starwars.ab.domain.SpeciesScan;

@Component
public class SpeciesScanner extends Scanner {
	
	private final String FIRSTPAGE = "http://swapi.co/api/species/?page=1&format=json";	
	
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
				SpeciesScan newSpecie = mapper.readValue(getPeopleJson(next), SpeciesScan.class);
				jsonRequestsResult.add(newSpecie);
				next= newSpecie.getNext();
			}

		} catch (Exception e) {
			System.out.println("O império venceu " + e.getMessage());
		}
		
		return jsonRequestsResult;
	}

}
