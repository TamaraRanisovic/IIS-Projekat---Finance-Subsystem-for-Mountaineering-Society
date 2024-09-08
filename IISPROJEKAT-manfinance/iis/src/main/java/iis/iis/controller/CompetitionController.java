package iis.iis.controller;

import iis.iis.entity.Competition;
import iis.iis.entity.Person;
import iis.iis.entity.PersonDTO;
import iis.iis.service.CompetitionService;
import iis.iis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import iis.iis.service.CompetitionService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    public ResponseEntity<List<Competition>> nadjisvaTakmicenja(@RequestParam(value = "role")  String role) throws Exception{

        List<Competition> allCompetitions = this.competitionService.allCompetitions(role);
        return new ResponseEntity<>(allCompetitions, HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/update")
    public ResponseEntity<Competition> updateCompetition(@RequestBody Competition competition1,@RequestParam(value = "competition")  String competition,@RequestParam(value = "role")  String role) throws Exception {

        Competition competition2 = this.competitionService.updateCompetition(competition1, competition, role);


        return new ResponseEntity<>(competition2, HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/competition")
    public ResponseEntity<Competition> createCompetition(@RequestBody Competition competition) throws Exception {

        Competition newcompetition = competitionService.createCompetition(competition);


        return new ResponseEntity<>(newcompetition, HttpStatus.CREATED);
    }















}
