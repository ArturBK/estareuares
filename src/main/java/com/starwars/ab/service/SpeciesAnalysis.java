package com.starwars.ab.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.starwars.ab.model.Person;

@Component
public class SpeciesAnalysis {

	public Map<String, List<Person>> groupBySpecie(List<Person> people) {
		
		return people.stream().collect(Collectors.groupingBy(Person::getSpecieId));
	}
}
