package com.starwars.ab.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.starwars.ab.model.Person;


public class PersonTest {

	@Test
	public void getSpecieTest(){
		String specieUrl = "http://swapi.co/api/species/1/";
		
		Person person = new Person();
		person.setSpeciesUrls(Lists.newArrayList(specieUrl));
		
		assertEquals("1",person.getSpecieId());
	}
	
	@Test
	public void getIdTest(){
		String url = "http://swapi.co/api/people/1/";
		
		Person person = new Person();
		person.setUrl(url);
		
		assertEquals("1",person.getId());
	}
}
