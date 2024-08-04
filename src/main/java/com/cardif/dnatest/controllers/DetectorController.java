package com.cardif.dnatest.controllers;

import com.cardif.dnatest.requests.DnaRequestDTO;
import com.cardif.dnatest.responses.DnaStatsResponse;
import com.cardif.dnatest.services.AlienDetectorService;
import com.cardif.dnatest.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
@CrossOrigin(origins = "${cross.origin}", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class DetectorController {

    @Autowired
    public AlienDetectorService alienDetectorService;

    @Autowired
    public StatisticsService statisticsService;

    @PostMapping(value = "/alien", consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
    public ResponseEntity<String> detect(@RequestBody DnaRequestDTO dnaRequest){
        Boolean isAlien = alienDetectorService.isAlien(dnaRequest.getDna());
        if(isAlien){
            return ResponseEntity.ok("Alien detected");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Human detected");
        }
    }

    @GetMapping(value = "/stats", produces = "application/json;charset=utf-8")
    public ResponseEntity<DnaStatsResponse> statistics(){
        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getStats());
    }
}
