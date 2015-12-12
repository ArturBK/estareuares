package com.starwars.ab.domain;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.starwars.ab.util.StarWarsDate;

@Component
public class Person {
	private String name;
	@JsonProperty("birth_year")
    private String birthYear;
	@JsonProperty("films")
    private ArrayList<String> filmsUrls;
    private String gender;
	@JsonProperty("hair_color")
    private String hairColor;
    private String height;
	@JsonProperty("homeworld")
    private String homeWorldUrl;
    private String mass;
	@JsonProperty("skin_color")
    private String skinColor;
	@JsonProperty("eye_color")
    private String eyeColor;
    private String created;
    private String edited;
    private String url;
	@JsonProperty("species")
    private ArrayList<String> speciesUrls;
	@JsonProperty("starships")
    private ArrayList<String> starshipsUrls;
	@JsonProperty("vehicles")
    private ArrayList<String> vehiclesUrls;
	
	public double getBirthYearSWDate(){
		StarWarsDate swDate = new StarWarsDate();
		return swDate.convert(birthYear);
	}
	
	public String getSpecieId(){
		if (this.speciesUrls == null || this.speciesUrls.isEmpty()) return "unknow";

		String[] parts = this.speciesUrls.get(0).split("/");
		return parts[5];
	}
	
	public String getId(){
		if (this.url == null) return "unknow";

		String[] parts = this.url.split("/");
		return parts[5];
	}
	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	public ArrayList<String> getFilmsUrls() {
		return filmsUrls;
	}
	public void setFilmsUrls(ArrayList<String> filmsUrls) {
		this.filmsUrls = filmsUrls;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHairColor() {
		return hairColor;
	}
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getHomeWorldUrl() {
		return homeWorldUrl;
	}
	public void setHomeWorldUrl(String homeWorldUrl) {
		this.homeWorldUrl = homeWorldUrl;
	}
	public String getMass() {
		return mass;
	}
	public void setMass(String mass) {
		this.mass = mass;
	}
	public String getSkinColor() {
		return skinColor;
	}
	public void setSkinColor(String skinColor) {
		this.skinColor = skinColor;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ArrayList<String> getSpeciesUrls() {
		return speciesUrls;
	}
	public void setSpeciesUrls(ArrayList<String> speciesUrls) {
		this.speciesUrls = speciesUrls;
	}
	public ArrayList<String> getStarshipsUrls() {
		return starshipsUrls;
	}
	public void setStarshipsUrls(ArrayList<String> starshipsUrls) {
		this.starshipsUrls = starshipsUrls;
	}
	public ArrayList<String> getVehiclesUrls() {
		return vehiclesUrls;
	}
	public void setVehiclesUrls(ArrayList<String> vehiclesUrls) {
		this.vehiclesUrls = vehiclesUrls;
	}
}
