package iis.iis.service;

import iis.iis.entity.Person;
import iis.iis.entity.Prostorija;
import iis.iis.entity.Termin;

import java.util.List;
import java.util.Set;

public interface TerminService {


    Prostorija dodajnovuprostoriju(Prostorija prostorija, String role) throws Exception;

     List<Prostorija> nadjiprostorije(String role) throws Exception;

     Termin novitermin(Termin termin,String role,String prostorija,Long korisnik) throws Exception;

    Set<Termin> kreiranitermini(Long korisnik, String role) throws Exception;

    Set<Termin> otkazitermin(Long korisnik, String role, String termin1) throws Exception;

    Set<Termin> kreiranitermini1(Long korisnik, String role) throws Exception;

    Set<Termin> zakazimesto(Long korisnik, String role, String termin1) throws Exception;

    Set<Termin> svirezervisanitermini(Long korisnik, String uloga) throws Exception;

    Set<Termin> otkazimesto(Long korisnik, String role, String termin1) throws Exception;
}
