package iis.iis.service;

import iis.iis.entity.Person;
import iis.iis.entity.Prostorija;
import iis.iis.entity.Termin;
import iis.iis.repository.ProstorijaRepository;
import iis.iis.repository.TerminRepository;
import iis.iis.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class TerminServiceImpl implements TerminService{


    private final TerminRepository terminRepository;
    private final ProstorijaRepository prostorijaRepository;
    private final UserRepository userRepository;


    public TerminServiceImpl(TerminRepository terminRepository, ProstorijaRepository prostorijaRepository, UserRepository userRepository ) {
        this.terminRepository = terminRepository;
        this.prostorijaRepository = prostorijaRepository;
        this.userRepository = userRepository;

    }

    @Override
    public Prostorija dodajnovuprostoriju(Prostorija prostorija, String role) throws Exception {
        if(!role.equals("PROFESOR")){
            throw new Exception ("Nemate pristup ovim podacima!");

        }



        Prostorija novaprostorija = this.prostorijaRepository.save(prostorija);
        return novaprostorija;
    }


    @Override
    public List<Prostorija> nadjiprostorije(String role) throws Exception{

        if(!role.equals("PROFESOR")){
            throw new Exception("Nemate pristup ovim podacima!");
        }

        List<Prostorija> sveprostorije = this.prostorijaRepository.findAll();



        return sveprostorije;




    }






    @Override
    public Termin novitermin(Termin termin, String role, String prostorija, Long korisnik) throws Exception{
        if(!role.equals("PROFESOR")){
            throw new Exception("Nemate pristup ovim podacima!");
        }



        if(prostorija.equals("undefined")){
            throw new Exception("Niste izabrali salu!");
        }

        if(prostorija.equals("null")){
            throw new Exception("Niste izabrali salu!");
        }

        Long s = Long.parseLong(prostorija);


        Prostorija prostorija2 = this.prostorijaRepository.pronadjipoid(s);

        Set<Termin> trmn = prostorija2.getTermini();
        for(Termin terminn1: trmn){
            if(terminn1.getDatum().equals( termin.getDatum())){
                throw new Exception("Vec postoji termin u izabrano vreme u izabranoj sali!");
            }
        }


        if(termin.getDatum().isBefore(LocalDateTime.now())){
            throw new Exception("Datum termina mora biti veci od danasnjeg datuma!");
        }



        Person person2 = this.userRepository.pronadjipoid(korisnik);


        Termin termin1 = new Termin();

        termin1.setDatum(termin.getDatum());
        termin1.setCena(termin.getCena());
        termin1.setOpis(termin.getOpis());
        termin1.setProstorija(prostorija2);
        termin1.setTiptermina(termin.getTiptermina());
        termin1.setAktivan(true);
        termin1.setBrojprijavljenih(0);
        termin1.setProfesor(person2);

        Termin termin3 = this.terminRepository.save(termin1);

        return termin3;


    }





    @Override
    public  Set<Termin> kreiranitermini(Long korisnik, String role) throws Exception{
        if (!role.equals( "PROFESOR")) {
            throw new Exception("Nemate pristup ovim podacima!");
        }

        Person user = this.userRepository.pronadjipoid(korisnik);
        Set<Termin> termini =  user.getKreiranitermini();
        Set<Termin> sviaktivnitermini = new HashSet<>();
        for(Termin termin: termini)
        {
            if(termin.isAktivan())
            {
                sviaktivnitermini.add(termin);
            }
        }

        return sviaktivnitermini;

    }




    @Override
    public  Set<Termin> otkazitermin(Long korisnik, String role, String termin1) throws Exception{

        if(!role.equals("PROFESOR")){
            throw new Exception("Nemate pristup ovim podacima!");
        }

        if(termin1.equals("undefined") ){
            throw new Exception("Niste izabrali termin!");
        }
        if(termin1.equals("null")){
            throw new Exception("Niste izabrali termin!");
        }

        Long l = Long.parseLong(termin1);

        Person profesor = this.userRepository.pronadjipoid(korisnik);
        Termin termin12 = this.terminRepository.pronadjipoid(l);

        termin12.setAktivan(false);
//        clan.removetermin(termin1);
//        termin1.removeclan1(clan);

        this.terminRepository.save(termin12);
        Set<Termin> termini =  profesor.getKreiranitermini();
        Set<Termin> sviaktivnitermini = new HashSet<>();
        for(Termin termin: termini)
        {
            if(termin.isAktivan())
            {
                sviaktivnitermini.add(termin);
            }
        }


        return sviaktivnitermini;


    }



    @Override
    public  Set<Termin> kreiranitermini1(Long korisnik, String role) throws Exception{
        if (!role.equals( "POHADJACOBRAZOVNIHPROG")) {
            throw new Exception("Nemate pristup ovim podacima!");
        }

        Person user = this.userRepository.pronadjipoid(korisnik);

        List<Termin> termini =  this.terminRepository.findAll();
        Set<Termin> sviaktivnitermini = new HashSet<>();
        for(Termin termin: termini) {

                if (termin.isAktivan() && termin.getDatum().isAfter(LocalDateTime.now())) {
                    sviaktivnitermini.add(termin);
                }
            }


        return sviaktivnitermini;

    }



    @Override
    public  Set<Termin> zakazimesto(Long korisnik, String role, String termin1) throws Exception{

        if(!role.equals("POHADJACOBRAZOVNIHPROG")){
            throw new Exception("Nemate pristup ovim podacima!");
        }

        if(termin1.equals("undefined") ){
            throw new Exception("Niste izabrali termin!");
        }
        if(termin1.equals("null")){
            throw new Exception("Niste izabrali termin!");
        }

        Long l = Long.parseLong(termin1);

        Person pohadjac = this.userRepository.pronadjipoid(korisnik);
        Termin termin12 = this.terminRepository.pronadjipoid(l);
        Set<Termin> termini1 = pohadjac.getTermin1();
        for(Termin termin: termini1)
        {
            if(termin.getId()==termin12.getId())
            {
                throw new Exception("Vec ste zakazali mesto u odabranom terminu!");
            }
        }

        if(termin12.getBrojprijavljenih()<termin12.getProstorija().getKapacitet()) {
            termin12.setBrojprijavljenih(termin12.getBrojprijavljenih()+1);
            Termin terminn = this.terminRepository.save(termin12);
            termini1.add(terminn);
        }
        else
        {
            throw new Exception("Popunjena su sva mesta za ovaj termin");
        }

        pohadjac.setTermin1(termini1);
        this.userRepository.save(pohadjac);

        List<Termin> termini =  this.terminRepository.findAll();
        Set<Termin> sviaktivnitermini = new HashSet<>();
        for(Termin termin: termini)
        {
            if(termin.isAktivan() && termin.getDatum().isAfter(LocalDateTime.now()))
            {
                sviaktivnitermini.add(termin);
            }
        }


        return sviaktivnitermini;


    }




    @Override
    public  Set<Termin> svirezervisanitermini(Long korisnik, String role) throws Exception{
        if (!role.equals( "POHADJACOBRAZOVNIHPROG")) {
            throw new Exception("Nemate pristup ovim podacima!");
        }

        Person user = this.userRepository.pronadjipoid(korisnik);
        Set<Termin> svirezervisanitermini =  user.getTermin1();

        Set<Termin> sviaktivnitermini = new HashSet<>();
        for(Termin termin: svirezervisanitermini)
        {
            if(termin.isAktivan() && termin.getDatum().isAfter(LocalDateTime.now()))
            {
                sviaktivnitermini.add(termin);
            }
        }

        return sviaktivnitermini;

    }




    @Override
    public  Set<Termin> otkazimesto(Long korisnik, String role, String termin1) throws Exception{

        if(!role.equals("POHADJACOBRAZOVNIHPROG")){
            throw new Exception("Nemate pristup ovim podacima!");
        }

        if(termin1.equals("undefined") ){
            throw new Exception("Niste izabrali termin!");
        }
        if(termin1.equals("null")){
            throw new Exception("Niste izabrali termin!");
        }

        Long l = Long.parseLong(termin1);

        Person pohadjac = this.userRepository.pronadjipoid(korisnik);
        Termin termin12 = this.terminRepository.pronadjipoid(l);
        termin12.setBrojprijavljenih(termin12.getBrojprijavljenih()-1);
        Set<Termin> termini1 = pohadjac.getTermin1();
        Termin terminn = this.terminRepository.save(termin12);
        termini1.remove(terminn);
        pohadjac.setTermin1(termini1);
        this.userRepository.save(pohadjac);

        Set<Termin> termini =  pohadjac.getTermin1();
        Set<Termin> sviaktivnitermini = new HashSet<>();
        for(Termin termin: termini)
        {
            if(termin.isAktivan() && termin.getDatum().isAfter(LocalDateTime.now()))
            {
                sviaktivnitermini.add(termin);
            }
        }


        return sviaktivnitermini;


    }


}
