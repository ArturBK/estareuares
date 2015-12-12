package com.starwars.ab.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Specie {

    public String name;
    public String classification;
    public String designation;

	@JsonProperty("average_height")
    public String averageHeight;

	@JsonProperty("average_lifespan")
    public String averageLifespan;

	@JsonProperty("eye_colors")
    public String eyeColors;

	@JsonProperty("hair_colors")
    public String hairColors;

	@JsonProperty("skin_colors")
    public String skinColors;

	@JsonProperty("homeworld")
    public String homeWorld;

    public String language;
    public String created;
    public String edited;
    public String url;

	@JsonProperty("people")
    public ArrayList<String> peopleUrls;

	@JsonProperty("films")
    public ArrayList<String> filmsUrls;
	

	public String specieNameById(String id, List<Specie> speciesList){
		List<Specie> specie = speciesList.stream().filter(s -> s.getId().equalsIgnoreCase(id)).collect(Collectors.toList());
		return specie == null ? "Unknow" : specie.get(0).getName();
	}

	public Map<String, List<Person>> groupBySpecie(List<Person> people) {
		return people.stream().collect(Collectors.groupingBy(Person::getSpecieId));
	}
    
	public String getId(){
		if (this.url == null) return "Unknow";

		String[] parts = this.url.split("/");
		return parts[5];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAverageHeight() {
		return averageHeight;
	}

	public void setAverageHeight(String averageHeight) {
		this.averageHeight = averageHeight;
	}

	public String getAverageLifespan() {
		return averageLifespan;
	}

	public void setAverageLifespan(String averageLifespan) {
		this.averageLifespan = averageLifespan;
	}

	public String getEyeColors() {
		return eyeColors;
	}

	public void setEyeColors(String eyeColors) {
		this.eyeColors = eyeColors;
	}

	public String getHairColors() {
		return hairColors;
	}

	public void setHairColors(String hairColors) {
		this.hairColors = hairColors;
	}

	public String getSkinColors() {
		return skinColors;
	}

	public void setSkinColors(String skinColors) {
		this.skinColors = skinColors;
	}

	public String getHomeWorld() {
		return homeWorld;
	}

	public void setHomeWorld(String homeWorld) {
		this.homeWorld = homeWorld;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getEdited() {
		return edited;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

	public ArrayList<String> getPeopleUrls() {
		return peopleUrls;
	}

	public void setPeopleUrls(ArrayList<String> peopleUrls) {
		this.peopleUrls = peopleUrls;
	}

	public ArrayList<String> getFilmsUrls() {
		return filmsUrls;
	}

	public void setFilmsUrls(ArrayList<String> filmsUrls) {
		this.filmsUrls = filmsUrls;
	}
	
	
}