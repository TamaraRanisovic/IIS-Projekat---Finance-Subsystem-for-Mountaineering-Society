package iis.iis.controller;


import iis.iis.entity.Prostorija;
import iis.iis.entity.ProstorijaDTO;
import iis.iis.entity.Termin;
import iis.iis.entity.TerminDTO;
import iis.iis.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/termini")
public class TerminController {


    private final TerminService terminService;

    @Autowired
    public TerminController(TerminService terminService) {
        this.terminService = terminService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, value = "/novaprostorija")
    public ResponseEntity<Prostorija> dodajnovuprostoriju(@RequestBody Prostorija prostorija, @RequestParam(value = "role") String role) throws Exception {

        Prostorija novaprostorija = this.terminService.dodajnovuprostoriju(prostorija, role);


        return new ResponseEntity<>(novaprostorija, HttpStatus.CREATED);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/nadjiprostorije1")
    public ResponseEntity<Set<ProstorijaDTO>> nadjiprostorije1(@RequestParam(value = "role") String role) throws Exception {

        List<Prostorija> sveprostorije = this.terminService.nadjiprostorije(role);
        Set<ProstorijaDTO> prostorijedto = new HashSet<>();

        for (Prostorija prostorija : sveprostorije) {


            ProstorijaDTO prostorijaDTO = new ProstorijaDTO(prostorija.getId(),prostorija.getKapacitet(),prostorija.getOznaka()
                    );
            prostorijedto.add(prostorijaDTO);
        }

        return new ResponseEntity<>(prostorijedto, HttpStatus.CREATED);

    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/novitermin1")
    public ResponseEntity<TerminDTO> novitermin(@RequestBody Termin termin, @RequestParam(value = "role") String role, @RequestParam(value = "prostorija") String prostorija, @RequestParam(value = "korisnik") Long korisnik) throws Exception {


        Termin termin1 = this.terminService.novitermin(termin, role, prostorija, korisnik);

        TerminDTO terminDTO = new TerminDTO(termin1.getDatum(), termin1.getCena(),
                termin1.getBrojprijavljenih(), termin1.getProstorija().getOznaka(), termin1.getProfesor().getFirstname(), termin1.getProfesor().getLastname(),
                termin1.getOpis(), termin1.getTiptermina(), termin1.getId(), termin1.isAktivan());


        return new ResponseEntity<>(terminDTO, HttpStatus.OK);

    }




    @GetMapping(value = "/svitermini", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<TerminDTO>> listatermina(@RequestParam(value = "korisnik") Long korisnik, @RequestParam(value = "role") String role) throws Exception {


        Set<Termin> listatermina1 = this.terminService.kreiranitermini(korisnik, role);
        Set<TerminDTO> terminDTOS1 = new HashSet<>();

        for (Termin termin : listatermina1) {


            TerminDTO terminDTO = new TerminDTO(termin.getDatum(), termin.getCena(),
                    termin.getBrojprijavljenih(), termin.getProstorija().getOznaka(), termin.getProfesor().getFirstname(), termin.getProfesor().getLastname(),
                    termin.getOpis(), termin.getTiptermina(), termin.getId(), termin.isAktivan());
            terminDTOS1.add(terminDTO);
        }

        return new ResponseEntity<>(terminDTOS1, HttpStatus.OK);
    }




    @GetMapping(value = "/otkazitermin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<TerminDTO>> otkazitermin(@RequestParam(value = "korisnik") Long korisnik, @RequestParam(value = "role") String role, @RequestParam(value = "termin") String termin1) throws Exception {


        Set<Termin> listatermina1 = this.terminService.otkazitermin(korisnik, role, termin1);
        Set<TerminDTO> terminDTOS1 = new HashSet<>();

        for (Termin termin : listatermina1) {


            TerminDTO terminDTO = new TerminDTO(termin.getDatum(), termin.getCena(),
                    termin.getBrojprijavljenih(), termin.getProstorija().getOznaka(), termin.getProfesor().getFirstname(), termin.getProfesor().getLastname(),
                    termin.getOpis(), termin.getTiptermina(), termin.getId(), termin.isAktivan());
            terminDTOS1.add(terminDTO);
        }

        return new ResponseEntity<>(terminDTOS1, HttpStatus.OK);
    }



    @GetMapping(value = "/svitermini1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<TerminDTO>> listatermina1(@RequestParam(value = "korisnik") Long korisnik, @RequestParam(value = "role") String role) throws Exception {


        Set<Termin> listatermina1 = this.terminService.kreiranitermini1(korisnik, role);
        Set<TerminDTO> terminDTOS1 = new HashSet<>();

        for (Termin termin : listatermina1) {


            TerminDTO terminDTO = new TerminDTO(termin.getDatum(), termin.getCena(),
                    termin.getBrojprijavljenih(), termin.getProstorija().getOznaka(), termin.getProfesor().getFirstname(), termin.getProfesor().getLastname(),
                    termin.getOpis(), termin.getTiptermina(), termin.getId(), termin.isAktivan());
            terminDTOS1.add(terminDTO);
        }

        return new ResponseEntity<>(terminDTOS1, HttpStatus.OK);
    }



    @GetMapping(value = "/zakazimesto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<TerminDTO>> zakazimesto(@RequestParam(value = "korisnik") Long korisnik, @RequestParam(value = "role") String role, @RequestParam(value = "termin") String termin1) throws Exception {


        Set<Termin> listatermina1 = this.terminService.zakazimesto(korisnik, role, termin1);
        Set<TerminDTO> terminDTOS1 = new HashSet<>();

        for (Termin termin : listatermina1) {


            TerminDTO terminDTO = new TerminDTO(termin.getDatum(), termin.getCena(),
                    termin.getBrojprijavljenih(), termin.getProstorija().getOznaka(), termin.getProfesor().getFirstname(), termin.getProfesor().getLastname(),
                    termin.getOpis(), termin.getTiptermina(), termin.getId(), termin.isAktivan());
            terminDTOS1.add(terminDTO);
        }

        return new ResponseEntity<>(terminDTOS1, HttpStatus.OK);
    }


    @GetMapping(value = "/svirezervisanitermini", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<TerminDTO>> svirezervisanitermini(@RequestParam(value = "korisnik") Long korisnik, @RequestParam(value = "role") String role) throws Exception {


        Set<Termin> listatermina1 = this.terminService.svirezervisanitermini(korisnik, role);
        Set<TerminDTO> terminDTOS1 = new HashSet<>();

        for (Termin termin : listatermina1) {


            TerminDTO terminDTO = new TerminDTO(termin.getDatum(), termin.getCena(),
                    termin.getBrojprijavljenih(), termin.getProstorija().getOznaka(), termin.getProfesor().getFirstname(), termin.getProfesor().getLastname(),
                    termin.getOpis(), termin.getTiptermina(), termin.getId(), termin.isAktivan());
            terminDTOS1.add(terminDTO);
        }

        return new ResponseEntity<>(terminDTOS1, HttpStatus.OK);
    }



    @GetMapping(value = "/otkazimesto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<TerminDTO>> otkazimesto(@RequestParam(value = "korisnik") Long korisnik, @RequestParam(value = "role") String role, @RequestParam(value = "termin") String termin1) throws Exception {


        Set<Termin> listatermina1 = this.terminService.otkazimesto(korisnik, role, termin1);
        Set<TerminDTO> terminDTOS1 = new HashSet<>();

        for (Termin termin : listatermina1) {


            TerminDTO terminDTO = new TerminDTO(termin.getDatum(), termin.getCena(),
                    termin.getBrojprijavljenih(), termin.getProstorija().getOznaka(), termin.getProfesor().getFirstname(), termin.getProfesor().getLastname(),
                    termin.getOpis(), termin.getTiptermina(), termin.getId(), termin.isAktivan());
            terminDTOS1.add(terminDTO);
        }

        return new ResponseEntity<>(terminDTOS1, HttpStatus.OK);
    }
}