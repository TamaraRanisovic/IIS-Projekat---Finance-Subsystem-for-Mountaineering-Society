package iis.iis.controller;

import iis.iis.entity.*;
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
@RequestMapping(value = "/api/availablefunds")
public class AvailableFundsController {



    private final AvailableFundsService availableFundsService;

    @Autowired
    public AvailableFundsController(AvailableFundsService availableFundsService) {
        this.availableFundsService = availableFundsService;
    }



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/add")
    public ResponseEntity<AvailableFunds> createIncome(@RequestBody AvailableFunds availableFunds) throws Exception {

        AvailableFunds newAvailableFunds = availableFundsService.createAvailableFunds(availableFunds);

        return new ResponseEntity<>(newAvailableFunds, HttpStatus.CREATED);
    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/latest")
    public ResponseEntity<AvailableFunds> getLatestAvailableFunds(@RequestParam(value = "role") String role) throws Exception {
        AvailableFunds latestAvailableFunds = this.availableFundsService.getLatestAvailableFunds(role);
        if (latestAvailableFunds == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(latestAvailableFunds, HttpStatus.OK);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    public ResponseEntity<List<AvailableFunds>> getAllAvailableFunds(@RequestParam(value = "role")  String role) throws Exception{

        List<AvailableFunds> allAvailableFunds  = this.availableFundsService.getAllAvailableFunds(role);

        return new ResponseEntity<>(allAvailableFunds, HttpStatus.OK);

    }


}
