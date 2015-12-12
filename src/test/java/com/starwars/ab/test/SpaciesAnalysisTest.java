package com.starwars.ab.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.starwars.ab.model.Person;
import com.starwars.ab.service.SpeciesAnalysis;


public class SpaciesAnalysisTest {

	@Test
	public void getSpecie(){
		assertEquals(2,new SpeciesAnalysis().groupBySpecie(Mock()).get("1").size());
		
	}

	private List<Person> Mock() {
		String specieUrl = "http://swapi.co/api/species/1/";
		Person person = new Person();
		person.setSpeciesUrls( Lists.newArrayList(specieUrl));
		
		String specieUrl2 = "http://swapi.co/api/species/3/";
		Person person2 = new Person();
		person2.setSpeciesUrls( Lists.newArrayList(specieUrl2));
		
		List<Person> people = Lists.newArrayList(person, person, person2);
		return people;
	}
}
