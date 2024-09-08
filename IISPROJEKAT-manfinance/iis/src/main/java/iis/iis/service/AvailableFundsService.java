package iis.iis.service;

import iis.iis.entity.AvailableFunds;
import iis.iis.entity.Income;

import java.time.LocalDateTime;
import java.util.List;

public interface AvailableFundsService {
    AvailableFunds createAvailableFunds(AvailableFunds availableFunds) throws Exception;
    AvailableFunds getLatestAvailableFunds(String role) throws Exception;
    List<AvailableFunds> getAllAvailableFunds(String role) throws Exception;
    AvailableFunds increaseAvailableFunds(float amount, String role) throws Exception;

    AvailableFunds decreaseAvailableFunds(float amount, String role) throws Exception;
}
