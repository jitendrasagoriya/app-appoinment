package com.js.appointment.dao.impl;

import com.js.appointment.dao.TokensServiceDao;
import com.js.appointment.model.Tokens;
import com.js.appointment.repository.TokensRepository;
import com.js.appointment.service.impl.DoctorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class TokensServiceDaoImpl implements TokensServiceDao {

    private final Logger logger = LoggerFactory.getLogger(TokensServiceDaoImpl.class);

    @Autowired
    private TokensRepository repository;

    @Override
    public Tokens getTokenById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<Tokens> getTokens() {
        return repository.findAll();
    }

    @Override
    public Tokens addToken(Tokens tokens) {
        return repository.save(tokens);
    }

    @Override
    public Tokens getTokenByDoctorAndShift(String doctorId, String shift) {
        return repository.getTokenByDoctorAndShift(doctorId,shift);
    }

    @Transactional
    @Override
    public void nextTokenByN(Long id,Integer n) {
        repository.nextToken(id,n);
    }

    @Transactional
    @Override
    public void nextTokenByN(String drId, Integer n) {
        repository.nextTokenByDoctorId(drId, n);
    }

    @Transactional
    @Override
    public void maxTokenByN(Long id,Integer n){
        repository.maxTokenByN(id, n);
    }

    @Transactional
    @Override
    public void maxTokenByN(String drId, Integer n) {
        repository.maxTokenByNByDoctorId(drId, n);
    }

    @Override
    public void resetCurrentToken(Long id, Integer token) {
        Tokens tokens = repository.getOne(id);
        tokens.setCurrentToken(token);
        repository.saveAndFlush(tokens);
    }

    @Override
    public void resetMaxToken(Long id, Integer token) {
        Tokens tokens = repository.getOne(id);
        tokens.setMaxTOken(token);
        repository.saveAndFlush(tokens);
    }

    @Override
    public void resetCurrentToken(String id, Integer token) {
        Tokens tokens = repository.getTokenByDoctorAndCurrent(id);
        resetCurrentToken(tokens.getId(),token);
    }

    @Override
    public void resetMaxToken(String id, Integer token) {
        Tokens tokens = repository.getTokenByDoctorAndCurrent(id);
        resetMaxToken(tokens.getId(),token);
    }

    @Transactional
    @Override
    public void setCurrentTokenByDoctorIdAndShiftAndAndTokenDate(String drId, String shift, Date toDay,Boolean current) {
        repository.setCurrentTokenByDoctorIdAndShiftAndAndTokenDate(drId, shift, toDay,current);
    }

    @Override
    public Tokens getTokenByDoctorAndCurrentTokens(String drId) {
        return repository.getTokenByDoctorAndCurrent(drId);
    }

    @Override
    public List<Tokens> getTokenByDoctor(String doctorId) {
        return repository.getTokenByDoctor(doctorId);
    }

    @Override
    public Page<Tokens> getTokenByDoctor(String doctorId, Pageable pageable) {
        return repository.getTokenByDoctor(doctorId, pageable);
    }

    @Override
    public List<Tokens> getTokenByDoctorAndTokenDate(String doctorId, Date today) {
        return repository.getTokenByDoctorAndTokenDate(doctorId,today);
    }

    @Override
    public TokensRepository getRepository() {
        return repository;
    }
}
