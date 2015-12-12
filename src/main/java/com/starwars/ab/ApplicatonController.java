package com.starwars.ab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starwars.ab.model.Person;
import com.starwars.ab.service.RescueService;

@Controller
@EnableAutoConfiguration
@ComponentScan("com.starwars.ab")
public class ApplicatonController {

	@Autowired
	RescueService service;
	
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Running!";
    }
    
    @RequestMapping("/rescuePeopleWithMockedIds")
    @ResponseBody
    Map<String, List<Person>> rescuePeopleWithMockedIds() {
    	
		System.out.println("Início");

    	List<String> peopleIds= new ArrayList<String>();
    	peopleIds.add("1");
    	peopleIds.add("42");
    	peopleIds.add("5");
    	peopleIds.add("5");
        return service.rescuePeople(peopleIds);
    }
    
    @RequestMapping("/rescuePeople")
    @ResponseBody
    Map<String, List<Person>> rescuePeople(@RequestParam(value="ids", defaultValue="") List<String> ids){
        return service.rescuePeople(ids);
    }
    
    @RequestMapping("/rescuePeopleWithVehicle")
    @ResponseBody
    Map<String, List<Person>> rescuePeopleWithVehicle(@RequestParam(value="ids", defaultValue="") List<String> ids, @RequestParam(value="vehicle", defaultValue="72") String vehicle){
        return service.rescuePeopleWithVehicle(ids, vehicle);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicatonController.class, args);
    }
}