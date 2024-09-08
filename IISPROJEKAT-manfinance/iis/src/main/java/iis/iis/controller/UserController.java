package iis.iis.controller;

import iis.iis.entity.LoginDTO;
import iis.iis.entity.Person;
import iis.iis.entity.PersonDTO;
import iis.iis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {



    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }






    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/user")
    public ResponseEntity<Person> createUser(@RequestBody Person person) throws Exception {

        Person newperson = userService.createUser(person);


        return new ResponseEntity<>(newperson, HttpStatus.CREATED);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/login")
    public ResponseEntity<LoginDTO> login(@RequestBody Person person) throws Exception {


        LoginDTO loginDTO1 = new LoginDTO();
        loginDTO1 = userService.proveri(person.getEmail(),person.getPassword());

        System.out.println("Login request received for role1: " + loginDTO1.getRole());

        return new ResponseEntity<>(loginDTO1, HttpStatus.CREATED);


    }




    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    public ResponseEntity<List<Person>> nadjisveusere(@RequestParam(value = "role")  String role) throws Exception{

        List<Person> sviuseri  = this.userService.sviuseri(role);



        return new ResponseEntity<>(sviuseri, HttpStatus.CREATED);

    }




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
    }

}
