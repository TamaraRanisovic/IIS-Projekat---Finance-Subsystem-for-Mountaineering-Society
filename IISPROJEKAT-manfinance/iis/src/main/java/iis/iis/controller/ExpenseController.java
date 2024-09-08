package iis.iis.controller;

import iis.iis.entity.*;
import iis.iis.service.AvailableFundsService;
import iis.iis.service.ExpenseService;
import iis.iis.service.IncomeService;
import iis.iis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/expense")
public class ExpenseController {



    private final ExpenseService expenseService;
    private final AvailableFundsService availableFundsService;

    @Autowired
    public ExpenseController(ExpenseService ExpenseService, AvailableFundsService availableFundsService) {
        this.expenseService = ExpenseService;
        this.availableFundsService = availableFundsService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/add")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense, @RequestParam(value = "role")  String role) throws Exception {

        Expense newExpense = expenseService.createExpense(expense);
        availableFundsService.decreaseAvailableFunds(newExpense.getAmount(), role);

        return new ResponseEntity<>(newExpense, HttpStatus.CREATED);
    }
/*

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/login")
    public ResponseEntity<LoginDTO> login(@RequestBody Person person) throws Exception {


        LoginDTO loginDTO1 = new LoginDTO();
        loginDTO1 = userService.proveri(person.getEmail(),person.getPassword());


        return new ResponseEntity<>(loginDTO1, HttpStatus.CREATED);


    }


*/

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    public ResponseEntity<List<Expense>> getAllExpenses(@RequestParam(value = "role")  String role) throws Exception{

        List<Expense> allExpenses  = this.expenseService.getAllExpenses(role);

        return new ResponseEntity<>(allExpenses, HttpStatus.OK);

    }



/*
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/one")
    public ResponseEntity<PersonDTO> finduser(@RequestParam(value = "user")  String user,@RequestParam(value = "role")  String role) throws Exception{

        Person person1  = this.userService.nadjiusera(user, role);
        PersonDTO personDTO = new PersonDTO(person1.getFirstname(), person1.getLastname(), person1.getPhonenumber(), person1.getEmail(), person1.getPassword(), person1.getDateofbirth());


        return new ResponseEntity<>(personDTO, HttpStatus.CREATED);

    }



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/update")
    public ResponseEntity<Person> updateuser(@RequestBody Person user1,@RequestParam(value = "user")  String user,@RequestParam(value = "role")  String role) throws Exception {

        Person user12 = this.userService.updateuser(user1, user, role);


        return new ResponseEntity<>(user12, HttpStatus.CREATED);
    }




    @GetMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> findparticipant(@RequestParam(value = "user") Long user, @RequestParam(value = "role") String role) throws Exception {


        Person participant = this.userService.findparticipant(user, role);

        PersonDTO participantDTO = new PersonDTO(participant.getFirstname(), participant.getLastname(), participant.getPhonenumber(), participant.getEmail(), participant.getPassword(), participant.getDateofbirth());

        return new ResponseEntity<>(participantDTO, HttpStatus.OK);
    }



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/updateparticipant")
    public ResponseEntity<PersonDTO> updateparticipant(@RequestBody Person participant, @RequestParam(value = "role") String role, @RequestParam(value = "user") Long user) throws Exception {

        Person newparticipant = userService.updateparticipant(participant, role, user);

        PersonDTO participantDTO = new PersonDTO(participant.getFirstname(), participant.getLastname(), participant.getPhonenumber(), participant.getEmail(), participant.getPassword(), participant.getDateofbirth());


        return new ResponseEntity<>(participantDTO, HttpStatus.CREATED);
    }*/

}
