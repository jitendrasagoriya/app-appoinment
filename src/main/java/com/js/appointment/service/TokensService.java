package com.js.appointment.service;

import com.js.appointment.exception.TokenNotFoundException;
import com.js.appointment.model.Tokens;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface TokensService {

    public Tokens getTokenById(Long id);

    public List<Tokens> getTokens();

    public Tokens addToken(Tokens tokens);

    public Tokens modifyToken(Tokens tokens);

    public Tokens getTokenByDoctorAndShift(String doctorId,String shift);

    public Integer getCurrentToken(Long id) throws TokenNotFoundException;

    public Integer getCurrentToken(String id) throws TokenNotFoundException;

    public Integer getCurrentToken(String doctorId,String shift) throws TokenNotFoundException;

    public Integer getMaxToken(Long id) throws TokenNotFoundException;

    public Integer getMaxToken(String id) throws TokenNotFoundException;

    public Integer getMaxToken(String doctorId,String shift) throws TokenNotFoundException;

    public Integer nextToken(Long id) throws TokenNotFoundException;

    public Integer nextToken(String id) throws TokenNotFoundException;

    public void resetTokenToZero(Long id);

    public void resetTokenToZero(String id);

    public void resetTokenToN(Long id,Integer token);

    public void resetTokenToN(String id,Integer token);

    public void resetMaxTokenToZero(Long id);

    public void resetMaxTokenToZero(String id);

    public void resetMaxTokenToN(Long id,Integer token);

    public void resetMaxTokenToN(String id,Integer token);

    public Boolean setCurrentTokenByDoctorIdAndShiftAndAndTokenDate(String drId, String shift );

    public Boolean resetCurrentTokenByDoctorIdAndShiftAndAndTokenDate(String drId, String shift );

    public Integer increaseMaxByN(String drId, Integer n) throws TokenNotFoundException;

    public Integer increaseMaxByOne(String drId, Integer n) throws TokenNotFoundException;

    public List<Tokens> getTokenByDoctor(String doctorId);

    public Page<Tokens> getTokenByDoctor(@Param("doctorId") String doctorId, Pageable pageable);

    public List<Tokens> getTodayTokenByDoctor( String doctorId);

    public List<Tokens> getTokenByDoctorAndTokenDate( String doctorId, Date today);

    public Tokens setCurrentShiftTrueById(Long id);

    public Tokens setCurrentShiftFalseById(Long id);

    public Boolean checkActiveShift();

    public Tokens getTokenByDoctorAndCurrentTokens(String drId);
}
