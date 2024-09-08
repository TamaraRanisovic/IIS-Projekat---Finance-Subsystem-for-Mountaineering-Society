package iis.iis.service;

import iis.iis.entity.Income;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IncomeService {

    Income createIncome(Income income) throws Exception;

//    LoginDTO proveri(String email, String lozinka) throws Exception;
    List<Income> findIncomesByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Income> getAllIncomes(String role) throws Exception;
/*
    Person nadjiusera(String user, String role) throws Exception;

    Person updateuser(Person user1, String user, String role) throws Exception;

    Person findparticipant(Long user,String role) throws Exception;

    Person updateparticipant( Person participant, String role, Long user) throws Exception;*/
}
