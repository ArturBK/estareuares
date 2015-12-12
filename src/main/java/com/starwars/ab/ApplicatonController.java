package com.starwars.ab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.starwars.ab.model.People;
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
    
    @RequestMapping("/rescuePeople")
    @ResponseBody
    List<People> rescuePeople() {
        return service.rescuePeople();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicatonController.class, args);
    }
}