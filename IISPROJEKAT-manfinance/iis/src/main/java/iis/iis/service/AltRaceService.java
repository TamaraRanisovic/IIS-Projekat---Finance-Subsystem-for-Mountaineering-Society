package iis.iis.service;

import iis.iis.entity.AltRace;
import iis.iis.entity.Income;

import java.util.List;

public interface AltRaceService {

    AltRace createAltRace(AltRace altRace) throws Exception;

    List<AltRace> getAllAltRaces(String role) throws Exception;


}
