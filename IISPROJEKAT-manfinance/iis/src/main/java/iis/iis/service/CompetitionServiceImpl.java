package iis.iis.service;

import iis.iis.entity.Competition;
import iis.iis.entity.Person;
import iis.iis.repository.CompetitionRepository;
import iis.iis.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;

    }
    @Override
    public Competition createCompetition(Competition competition) throws Exception {

        Competition newcompetition = this.competitionRepository.save(competition);
        return newcompetition;
    }

    @Override
    public List<Competition> allCompetitions(String role) throws Exception{
        if(!role.equals("MANRACE")){
            throw new Exception ("Nemate pristup ovim podacima!");

        }


        List<Competition> allCompetitions1 = this.competitionRepository.findAll();

        List<Competition> allCompetitions2 = new ArrayList<>();

        for(Competition competition: allCompetitions1){

            allCompetitions2.add(competition);

        }
        return allCompetitions2;



    }

    @Override
    public Competition updateCompetition(Competition competition1, String competition, String role) throws Exception{
        if(!role.equals("MANRACE")){
            throw new Exception("Nemate pristup ovim podacima!");
        }

        if(competition.equals("undefined") ){
            throw new Exception("Niste izabrali takmicenje!");
        }
        if(competition.equals("null")){
            throw new Exception("Niste izabrali takmicenje!");
        }
        Long l = Long.parseLong(competition);
        Competition competition2 = this.competitionRepository.getReferenceById(l);
        competition2.setType(competition1.getType());
        competition2.setDescription(competition1.getDescription());
        competition2.setLocation(competition1.getLocation());
        competition2.setNumberAvailable(competition1.getNumberAvailable());

        Competition newcompetition = this.competitionRepository.save(competition2);
        return newcompetition;


    }
}
