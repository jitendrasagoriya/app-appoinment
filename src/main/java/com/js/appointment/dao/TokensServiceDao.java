package com.js.appointment.dao;

import com.js.appointment.model.Tokens;
import com.js.appointment.repository.DoctorRepository;
import com.js.appointment.repository.TokensRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface TokensServiceDao extends BaseSerivce<TokensRepository> {

    public Tokens getTokenById(Long id);

    public List<Tokens> getTokens();

    public Tokens addToken(Tokens tokens);

    public Tokens getTokenByDoctorAndShift(String doctorId,String  shift);

    public void nextTokenByN(Long id,Integer n);

    public void nextTokenByN(String drId,Integer n);

    public void maxTokenByN(Long id,Integer n);

    public void maxTokenByN(String drId,Integer n);

    public void resetCurrentToken(Long id,Integer token);

    public void resetMaxToken(Long id,Integer token);

    public void resetCurrentToken(String id,Integer token);

    public void resetMaxToken(String id,Integer token);

    public void setCurrentTokenByDoctorIdAndShiftAndAndTokenDate(String drId, String shift, Date toDay, Boolean current);

    public Tokens getTokenByDoctorAndCurrentTokens(String drId);

    public List<Tokens> getTokenByDoctor(String doctorId);

    public Page<Tokens> getTokenByDoctor(@Param("doctorId") String doctorId, Pageable pageable);

    public List<Tokens> getTokenByDoctorAndTokenDate( String doctorId, Date today);
}
