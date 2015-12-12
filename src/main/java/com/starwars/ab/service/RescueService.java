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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.starwars.ab.model.Person;

@Service
public class RescueService {
	

	@Autowired
	PlanetScanner plannetScanner;
	@Autowired
	SpeciesAnalysis speciesAnalysis;
	
	public Map<String, List<Person>> rescuePeople(List<String> peopleIds){
		
		List<Person> allPeople = plannetScanner.getAllPeople();
		List<Person> peopleToSave = getById(peopleIds, allPeople);
		
		return speciesAnalysis.groupBySpecie(peopleToSave);
	}

	private List<Person> getById(List<String> peopleIds, List<Person> people) {
		return people.stream()
			      .filter(p -> peopleIds.contains(p.getId())).collect(Collectors.toList());
	}

	public Map<String, List<Person>> rescuePeopleWithVehicle(List<String> ids, String vehicle) {
		List<Person> allPeople = plannetScanner.getAllPeople();
		List<Person> peopleToSave = getById(ids, allPeople);
		Map<String, List<Person>> grouped = speciesAnalysis.groupBySpecie(peopleToSave);
		
		
		int placesInVehicle = 4; //  "passengers": "4", 
		
		ListMultimap<String, List<Person>> travels = ArrayListMultimap.create();
		
		
		travels.putAll((ancientsScapeFirst(grouped, placesInVehicle));
		travels.putAll(separeteSpeciesInTheShips(grouped, placesInVehicle));
							
		return travels;
	}

	private ListMultimap<String, List<Person>> separeteSpeciesInTheShips(Map<String, List<Person>> grouped,
			int placesInVehicle) {
		ListMultimap<String, List<Person>> travels = ArrayListMultimap.create();
		
		for (Entry<String, List<Person>> speciesList : grouped.entrySet()){
			List<List<Person>> smallerLists = Lists.partition(speciesList.getValue(), placesInVehicle);
		
			for (List<Person> people : smallerLists){
				travels.put(speciesList.getKey(), people);
			}
			
		}
		
		return travels;
	}

	private Multimap<? extends String, ? extends List<Person>> ancientsScapeFirst(Map<String, List<Person>> grouped, int placesInVehicle) {
		
		List<Person> ancients = getAncientsFromSpecies(grouped);
		List<List<Person>> smallerLists = Lists.partition(ancients, placesInVehicle);
		
		Map<String, List<Person>> travels = new HashMap<String, List<Person>>();

		for (List<Person> people : smallerLists){
			travels.put("Ancients", people);
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