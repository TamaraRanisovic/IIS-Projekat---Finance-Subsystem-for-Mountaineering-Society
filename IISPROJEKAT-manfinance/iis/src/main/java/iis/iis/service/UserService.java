package iis.iis.service;

import iis.iis.entity.LoginDTO;
import iis.iis.entity.Person;

import java.util.List;

public interface UserService {

    Person createUser(Person person) throws Exception;

    LoginDTO proveri(String email, String lozinka) throws Exception;

    List<Person> sviuseri(String role) throws Exception;

    Person nadjiusera(String user, String role) throws Exception;

   Person updateuser(Person user1, String user, String role) throws Exception;

    Person findparticipant(Long user,String role) throws Exception;

    Person updateparticipant( Person participant, String role, Long user) throws Exception;
}
