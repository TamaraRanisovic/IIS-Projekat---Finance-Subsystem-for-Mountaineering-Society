package iis.iis.service;

import iis.iis.entity.Expense;
import iis.iis.entity.LoginDTO;
import iis.iis.entity.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseService {

    Expense createExpense(Expense expense) throws Exception;

//    LoginDTO proveri(String email, String lozinka) throws Exception;
    List<Expense> findExpensesByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Expense> getAllExpenses(String role) throws Exception;

  /*  Person nadjiusera(String user, String role) throws Exception;

    Person updateuser(Person user1, String user, String role) throws Exception;

    Person findparticipant(Long user,String role) throws Exception;

    Person updateparticipant( Person participant, String role, Long user) throws Exception;*/
}
