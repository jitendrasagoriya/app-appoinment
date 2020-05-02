package com.js.appointment.service.impl;

import com.js.appointment.dao.ShiftServiceDao;
import com.js.appointment.dao.TokensServiceDao;
import com.js.appointment.dao.impl.TokensServiceDaoImpl;
import com.js.appointment.exception.TokenNotFoundException;
import com.js.appointment.model.Shift;
import com.js.appointment.model.Tokens;
import com.js.appointment.service.TokensService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TokensServiceImpl implements TokensService {

    private final Logger logger = LoggerFactory.getLogger(TokensServiceImpl.class);

    @Autowired
    private TokensServiceDao tokensServiceDao;

    @Autowired
    private ShiftServiceDao shiftServiceDao;

    @Override
    public Tokens getTokenById(Long id) {
        return tokensServiceDao.getTokenById(id);
    }

    @Override
    public List<Tokens> getTokens() {
        return tokensServiceDao.getTokens();
    }

    @Override
    public Tokens addToken(Tokens tokens) {
        return tokensServiceDao.addToken(tokens);
    }

    @Override
    public Tokens modifyToken(Tokens tokens) {
        return tokensServiceDao.addToken(tokens);
    }

    @Override
    public Tokens getTokenByDoctorAndShift(String doctorId, String shift) {
        return tokensServiceDao.getTokenByDoctorAndShift(doctorId, shift);
    }

    @Override
    public Integer getCurrentToken(Long id) throws TokenNotFoundException {
        return tokensServiceDao.getTokenById(id).getCurrentToken();
    }

    @Override
    public Integer getCurrentToken(String id) throws TokenNotFoundException {
        return tokensServiceDao.getTokenByDoctorAndCurrentTokens(id).getCurrentToken();
    }

    @Override
    public Integer getCurrentToken(String doctorId, String shift) throws TokenNotFoundException {
        return tokensServiceDao.getTokenByDoctorAndShift(doctorId, shift).getCurrentToken();
    }

    @Override
    public Integer getMaxToken(Long id) throws TokenNotFoundException {
        return tokensServiceDao.getTokenById(id).getMaxTOken();
    }

    @Override
    public Integer getMaxToken(String id) throws TokenNotFoundException {
        return tokensServiceDao.getTokenByDoctorAndCurrentTokens(id).getMaxTOken();
    }

    @Override
    public Integer getMaxToken(String doctorId, String shift) throws TokenNotFoundException {
        return tokensServiceDao.getTokenByDoctorAndShift(doctorId, shift).getMaxTOken();
    }

    @Override
    public Integer nextToken(Long id) throws TokenNotFoundException {
        tokensServiceDao.nextTokenByN(id,1);
        return getCurrentToken(id);
    }

    @Override
    public Integer nextToken(String id) throws TokenNotFoundException {
        tokensServiceDao.nextTokenByN(id,1);
        return getCurrentToken(id);
    }

    @Override
    public void resetTokenToZero(Long id) {
        resetTokenToN(id,0);
    }

    @Override
    public void resetTokenToZero(String id) {
        resetTokenToN(id,0);
    }

    @Override
    public void resetTokenToN(Long id, Integer token) {
        tokensServiceDao.resetCurrentToken(id,token);
    }

    @Override
    public void resetTokenToN(String id, Integer token) {
        tokensServiceDao.resetCurrentToken(id, token);
    }

    @Override
    public void resetMaxTokenToZero(Long id) {
        resetTokenToN(id,0);
    }

    @Override
    public void resetMaxTokenToZero(String id) {
        resetMaxTokenToN(id,0);
    }

    @Override
    public void resetMaxTokenToN(Long id, Integer token) {
        tokensServiceDao.resetMaxToken(id, token);
    }

    @Override
    public void resetMaxTokenToN(String id, Integer token) {
        tokensServiceDao.resetMaxToken(id, token);
    }

    @Override
    public Boolean setCurrentTokenByDoctorIdAndShiftAndAndTokenDate(String drId, String shift ) {
        return setCurrent(drId,shift,Boolean.TRUE);
    }

    @Override
    public Boolean resetCurrentTokenByDoctorIdAndShiftAndAndTokenDate(String drId, String shift) {
        return setCurrent(drId,shift,Boolean.FALSE);
    }

    @Override
    public Integer increaseMaxByN(String drId, Integer n) throws TokenNotFoundException{
        tokensServiceDao.resetCurrentToken(drId,n);
        return getMaxToken(drId);
    }

    @Override
    public Integer increaseMaxByOne(String drId, Integer n) throws TokenNotFoundException {
        return increaseMaxByN(drId,1);
    }

    @Override
    public List<Tokens> getTokenByDoctor(String doctorId) {
        return tokensServiceDao.getTokenByDoctor(doctorId);
    }

    @Override
    public Page<Tokens> getTokenByDoctor(String doctorId, Pageable pageable) {
        return tokensServiceDao.getTokenByDoctor(doctorId, pageable);
    }

    @Override
    public List<Tokens> getTodayTokenByDoctor(String doctorId) {
        return getTokenByDoctorAndTokenDate(doctorId,new Date(System.currentTimeMillis()));
    }

    @Override
    public List<Tokens> getTokenByDoctorAndTokenDate(String doctorId, Date today) {
        return tokensServiceDao.getTokenByDoctorAndTokenDate(doctorId, today);
    }

    @Override
    public Tokens setCurrentShiftTrueById(Long id) {
        try {
            Tokens tokens = tokensServiceDao.getTokenById(id);
            if(tokens == null)
                throw new TokenNotFoundException(""+id);
            tokens.setCurrent(Boolean.TRUE);
            return tokensServiceDao.addToken(tokens);
        } catch (Exception exception){
            logger.error(exception.getMessage(),exception);
            return null;
        }

    }

    @Override
    public Tokens setCurrentShiftFalseById(Long id) {
        try {
            Tokens tokens = tokensServiceDao.getTokenById(id);
            if(tokens == null)
                throw new TokenNotFoundException(""+id);
            tokens.setCurrent(Boolean.FALSE);
            return tokensServiceDao.addToken(tokens);
        } catch (Exception exception){
            logger.error(exception.getMessage(),exception);
            return null;
        }

    }

    @Override
    public Boolean checkActiveShift() {
        return tokensServiceDao.getRepository().getCheckCurrentShift()>0?Boolean.TRUE:Boolean.FALSE;
    }

    private Boolean setCurrent(String drId, String shift,Boolean current) {
        try {
            Date toDay = new Date(System.currentTimeMillis());
            tokensServiceDao.setCurrentTokenByDoctorIdAndShiftAndAndTokenDate(drId, shift, toDay,current);
            return Boolean.TRUE;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return Boolean.FALSE;
    }

    @Override
    public Tokens getTokenByDoctorAndCurrentTokens(String drId) {
        return tokensServiceDao.getTokenByDoctorAndCurrentTokens(drId);
    }
}
