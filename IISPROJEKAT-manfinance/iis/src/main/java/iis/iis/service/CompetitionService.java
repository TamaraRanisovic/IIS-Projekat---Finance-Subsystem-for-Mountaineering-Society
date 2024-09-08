package iis.iis.service;

import iis.iis.entity.Competition;
import iis.iis.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompetitionService {

    List<Competition> allCompetitions(String role) throws Exception;

    Competition updateCompetition(Competition competition1, String competition, String role) throws Exception;

    Competition createCompetition(Competition competition) throws Exception;
}
