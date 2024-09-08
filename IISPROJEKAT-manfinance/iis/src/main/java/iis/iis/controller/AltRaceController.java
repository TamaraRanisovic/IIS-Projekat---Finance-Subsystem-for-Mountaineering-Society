package iis.iis.controller;

import iis.iis.entity.*;
import iis.iis.service.AltRaceService;
import iis.iis.service.AvailableFundsService;
import iis.iis.service.IncomeService;
import iis.iis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/altrace")
public class AltRaceController {


    private final AltRaceService altRaceService;

    @Autowired
    public AltRaceController(AltRaceService altRaceService) {
        this.altRaceService = altRaceService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/add")
    public ResponseEntity<AltRace> createAltRace(@RequestBody AltRace altRace, @RequestParam(value = "role")  String role) throws Exception {

        AltRace newAltRace = altRaceService.createAltRace(altRace);
        return new ResponseEntity<>(newAltRace, HttpStatus.CREATED);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    public ResponseEntity<List<AltRace>> getAllAltRaces(@RequestParam(value = "role")  String role) throws Exception{

        List<AltRace> allAltRaces  = this.altRaceService.getAllAltRaces(role);

        return new ResponseEntity<>(allAltRaces, HttpStatus.OK);
    }

}
