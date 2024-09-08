package iis.iis.service;

import iis.iis.entity.AvailableFunds;
import iis.iis.entity.Income;
import iis.iis.repository.AvailableFundsRepository;
import iis.iis.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvalableFundsServiceImpl implements AvailableFundsService {
    private final AvailableFundsRepository availableFundsRepository;


    public AvalableFundsServiceImpl(AvailableFundsRepository availableFundsRepository) {
        this.availableFundsRepository = availableFundsRepository;

    }

    @Override
    public AvailableFunds createAvailableFunds(AvailableFunds availableFunds) throws Exception {
        AvailableFunds newAvailableFunds = this.availableFundsRepository.save(availableFunds);
        return newAvailableFunds;
    }

    @Override
    public AvailableFunds getLatestAvailableFunds(String role) throws Exception {
        if (!role.equals("MANFINANCE")) {
            throw new Exception("Nemate pristup ovim podacima!");
        }

        List<AvailableFunds> allAvailableFunds = this.availableFundsRepository.findAll();

        AvailableFunds latestAvailableFunds = null;
        LocalDateTime latestUpdateDate = null;

        for (AvailableFunds af : allAvailableFunds) {
            LocalDateTime currentDate = af.getUpdateDate();
            if (latestUpdateDate == null || currentDate.isAfter(latestUpdateDate)) {
                latestUpdateDate = currentDate;
                latestAvailableFunds = af;
            }
        }

        return latestAvailableFunds;
    }

    @Override
    public List<AvailableFunds> getAllAvailableFunds(String role) throws Exception{
        if(!role.equals("MANFINANCE")){
            throw new Exception ("Nemate pristup ovim podacima!");

        }

        List<AvailableFunds> allAvailableFunds = this.availableFundsRepository.findAll();

        List<AvailableFunds> availableFunds = new ArrayList<>();

        for(AvailableFunds af: allAvailableFunds){
            availableFunds.add(af);
        }

        return availableFunds;
    }

    @Override
    public AvailableFunds increaseAvailableFunds(float amount, String role) throws Exception {
        if (!role.equals("MANFINANCE")) {
            throw new Exception("Nemate pristup ovim podacima!");
        }

        AvailableFunds latestAvailableFunds = getLatestAvailableFunds(role);

        if (latestAvailableFunds == null) {
            throw new Exception("Nema dostupnih sredstava za ažuriranje!");
        }

        float currentAmount = latestAvailableFunds.getAmount();
        float updatedAmount = currentAmount + amount;

        AvailableFunds updatedAvailableFunds = new AvailableFunds();
        updatedAvailableFunds.setAmount(updatedAmount);
        updatedAvailableFunds.setUpdateDate(LocalDateTime.now());

        AvailableFunds newAvailableFunds = this.availableFundsRepository.save(updatedAvailableFunds);

        return newAvailableFunds;
    }

    @Override
    public AvailableFunds decreaseAvailableFunds(float amount, String role) throws Exception {
        if (!role.equals("MANFINANCE")) {
            throw new Exception("Nemate pristup ovim podacima!");
        }

        AvailableFunds latestAvailableFunds = getLatestAvailableFunds(role);

        if (latestAvailableFunds == null) {
            throw new Exception("Nema dostupnih sredstava za ažuriranje!");
        }

        float currentAmount = latestAvailableFunds.getAmount();
        float updatedAmount = currentAmount - amount;

        AvailableFunds updatedAvailableFunds = new AvailableFunds();
        updatedAvailableFunds.setAmount(updatedAmount);
        updatedAvailableFunds.setUpdateDate(LocalDateTime.now());

        AvailableFunds newAvailableFunds = this.availableFundsRepository.save(updatedAvailableFunds);

        return newAvailableFunds;
    }



}
