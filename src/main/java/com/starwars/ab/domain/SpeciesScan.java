package com.starwars.ab.domain;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpeciesScan {
	
	private int count;
	private String next;
	private String previous;
	@JsonProperty("results")
    private List<Specie> species;
    
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public List<Specie> getSpecies() {
		return Collections.unmodifiableList(species);
	}
	public void setSpecie(List<Specie> species) {
		this.species = species;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
}
