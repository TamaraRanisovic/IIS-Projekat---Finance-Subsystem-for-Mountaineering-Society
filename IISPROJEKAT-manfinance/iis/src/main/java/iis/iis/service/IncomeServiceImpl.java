package iis.iis.service;

import iis.iis.entity.Income;
import iis.iis.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {


    private final IncomeRepository incomeRepository;


    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;

    }

    @Override
    public Income createIncome(Income income) throws Exception {
        Income newIncome = this.incomeRepository.save(income);
        return newIncome;
    }
/*


    @Override
    public LoginDTO proveri(String email, String lozinka) throws Exception{
        LoginDTO loginDTO2 = new LoginDTO();
        Person user = userRepository.findByEmailAndPassword(email, lozinka);
        if(user!= null){
            loginDTO2.setId(user.getId());
            loginDTO2.setEmail(user.getEmail());
            loginDTO2.setFirstname(user.getFirstname());
            loginDTO2.setLastname(user.getLastname());
            loginDTO2.setPhonenumber(user.getPhonenumber());
            loginDTO2.setPassword(user.getPassword());
            loginDTO2.setRole(user.getRole());
        }

        if(loginDTO2.getEmail()==null & loginDTO2.getPassword()==null){
            throw new Exception("Niste uneli tacan email ili lozinku!");
        }
        return loginDTO2;

    }



*/

    @Override
    public List<Income> getAllIncomes(String role) throws Exception{
        if(!role.equals("MANFINANCE")){
            throw new Exception ("Nemate pristup ovim podacima!");

        }

        List<Income> allIncomes = this.incomeRepository.findAll();

        List<Income> incomes = new ArrayList<>();

        for(Income income: allIncomes){
            incomes.add(income);
        }

        return incomes;
    }


    public List<Income> findIncomesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return incomeRepository.findByDateBetween(startDate, endDate);
    }

/*

    @Override
    public Person nadjiusera(String user, String role) throws Exception{
        if(!role.equals("ADMINISTRATOR")){
            throw new Exception("Nemate pristup ovim podacima");
        }


        if(user.equals("undefined") ){
            throw new Exception("Niste izabrali korisnika!");
        }
        if(user.equals("null")){
            throw new Exception("Niste izabrali korisnika!");
        }

        Long l = Long.parseLong(user);
        Person person1 = this.userRepository.getReferenceById(l);
        return person1;
    }




    @Override
    public Person updateuser(Person user1, String user, String role) throws Exception{
        if(!role.equals("ADMINISTRATOR")){
            throw new Exception("Nemate pristup ovim podacima!");
        }

        if(user.equals("undefined") ){
            throw new Exception("Niste izabrali fitnes centar!");
        }
        if(user.equals("null")){
            throw new Exception("Niste izabrali fitnes centar!");
        }
        Long l = Long.parseLong(user);
        Person user2 = this.userRepository.getReferenceById(l);
        user2.setFirstname(user1.getFirstname());
        user2.setLastname(user1.getLastname());
        user2.setPhonenumber(user1.getPhonenumber());
        user2.setEmail(user1.getEmail());


        Person newuser = this.userRepository.save(user2);
        return newuser;


    }




    @Override
    public Person findparticipant(Long user, String role) throws Exception{
        if(!role.equals("PARTICIPANT")){
            throw new Exception("Nemate pristup ovim podacima!");
        }

        Person participant = this.userRepository.getReferenceById(user);

        return participant;



    }



    @Override
    public Person updateparticipant(Person participant, String role, Long user) throws Exception{

        if(!(role.equals("PARTICIPANT"))){
            throw new Exception("Nemate pristup ovim podacima!");
        }

        Person participant1 = this.userRepository.getReferenceById(user);

        participant1.setFirstname(participant.getFirstname());
        participant1.setLastname(participant.getLastname());
        participant1.setEmail(participant.getEmail());
        participant1.setPassword(participant.getPassword());
        participant1.setPhonenumber(participant.getPhonenumber());
        participant1.setDateofbirth(participant.getDateofbirth());


        Person participant2 = this.userRepository.save(participant1);
        return participant2;

    }*/
}
