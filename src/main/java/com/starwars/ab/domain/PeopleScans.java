package com.starwars.ab.domain;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PeopleScans {
	
		private int count;
		private String next;
		private String previous;
		@JsonProperty("results")
	    private List<Person> person;
	    
		public String getNext() {
			return next;
		}
		public void setNext(String next) {
			this.next = next;
		}
		public List<Person> getPerson() {
			return Collections.unmodifiableList(person);
		}
		public void setPerson(List<Person> person) {
			this.person = person;
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