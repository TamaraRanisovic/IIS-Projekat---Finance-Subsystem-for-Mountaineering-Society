package iis.iis.service;

import iis.iis.entity.*;
import iis.iis.repository.AltRaceRepository;
import iis.iis.repository.IncomeRepository;
import iis.iis.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
public class AltRaceServiceImpl implements AltRaceService {


    private final AltRaceRepository altRaceRepository;


    public AltRaceServiceImpl(AltRaceRepository altRaceRepository) {
        this.altRaceRepository = altRaceRepository;

    }

    @Override
    public AltRace createAltRace(AltRace altRace) throws Exception {
        AltRace newAltRace = this.altRaceRepository.save(altRace);
        return newAltRace;
    }


    @Override
    public List<AltRace> getAllAltRaces(String role) throws Exception{
        if(!role.equals("MANFINANCE")){
            throw new Exception ("Nemate pristup ovim podacima!");

        }

        List<AltRace> allAltRaces = this.altRaceRepository.findAll();

        List<AltRace> altRaces = new ArrayList<>();

        for(AltRace altRace: allAltRaces){
            altRaces.add(altRace);
        }

        return altRaces;
    }

}
