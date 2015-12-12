package com.starwars.ab.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.starwars.ab.domain.Person;
import com.starwars.ab.domain.Specie;

@Service
public class RescueService {
	@Autowired PlanetScanner plannetScanner;
	@Autowired SpeciesScanner speciesAnalysis;
	@Autowired Specie speciesDomain;
	@Autowired VehiclesInformation vehiclesInformation;
	
	public Map<String, List<Person>> rescuePeople(List<String> peopleIds){
		
		List<Person> allPeople = plannetScanner.getAllPeople();
		List<Person> peopleToSave = getById(peopleIds, allPeople);
		
		return speciesDomain.groupBySpecie(peopleToSave);
	}

	public Map<String, List<Person>> rescuePeopleWithVehicle(List<String> ids) {
		List<Person> allPeople = plannetScanner.getAllPeople();
		List<Person> peopleToSave = getById(ids, allPeople);
		Map<String, List<Person>> grouped = speciesDomain.groupBySpecie(peopleToSave);
				
		Map<String, List<Person>> travels = new HashMap<String, List<Person>>();
		travels.putAll(ancientsScapeFirst(grouped));
		Map<String, List<Person>> remains = removeAncients(grouped);
		travels.putAll(separeteSpeciesInTheShips(remains));
							
		return travels;
	}
	
	private List<Person> getById(List<String> peopleIds, List<Person> people) {
		return people.stream()
			      .filter(p -> peopleIds.contains(p.getId())).collect(Collectors.toList());
	}

	private Map<String, List<Person>> removeAncients(Map<String, List<Person>> grouped) {
		
		Map<String, List<Person>> travels = new HashMap<String, List<Person>>();

		List<Person> ancients = getAncientsFromSpecies(grouped);
		
		for (Entry<String, List<Person>> specie : grouped.entrySet()){
			specie.getValue().removeAll(ancients);
			travels.put(specie.getKey(), specie.getValue());
		}
		
		return travels;
	}

	private Map<String,List<Person>> separeteSpeciesInTheShips(Map<String, List<Person>> grouped) {
		Map<String, List<Person>> travels = new HashMap<String, List<Person>>();
		
		SpeciesScanner scan = new SpeciesScanner();
		List<Specie> speciesList = scan.allSpecies();
		
		for (Entry<String, List<Person>> speciesPeopleList : grouped.entrySet()){
			List<List<Person>> smallerLists = Lists.partition(speciesPeopleList.getValue(), vehiclesInformation.getVehicleCapacitie());
		
			int count = 1; 
			for (List<Person> people : smallerLists){
				travels.put(+count + " SpaceShip With " + speciesDomain.specieNameById(speciesPeopleList.getKey(), speciesList), people);
			}
		}
		
		return travels;
	}

	private Map<String, List<Person>> ancientsScapeFirst(Map<String, List<Person>> grouped) {
		return separeteByShip(getAncientsFromSpecies(grouped));
	}

	private Map<String, List<Person>> separeteByShip(List<Person> ancients) {
		List<List<Person>> smallerLists = Lists.partition(ancients, vehiclesInformation.getVehicleCapacitie());
		Map<String, List<Person>> travels = new HashMap<String, List<Person>>();

		int count = 1;
		for (List<Person> people : smallerLists){
			travels.put(++count +" SpaceShip With ANCIENTS", people);
		}
		
		return travels;
	}
	
	private List<Person> getAncientsFromSpecies(Map<String, List<Person>> grouped){
		
		List<Person> ancients = new ArrayList<Person>();
		
		for (Entry<String, List<Person>> peopleAndSpecie : grouped.entrySet()){
			List<Person> people = peopleAndSpecie.getValue();
			Collections.sort(people, (Person p1, Person p2) ->  Double.compare(p1.getBirthYearSWDate(),p2.getBirthYearSWDate()));
			
			ancients.add(people.get(0));
		}
		
		return ancients;
	}
}