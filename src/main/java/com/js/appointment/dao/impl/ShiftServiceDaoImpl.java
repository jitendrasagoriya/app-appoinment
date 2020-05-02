package com.js.appointment.dao.impl;

import com.js.appointment.dao.ShiftServiceDao;
import com.js.appointment.model.Shift;
import com.js.appointment.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftServiceDaoImpl implements ShiftServiceDao {

    @Autowired
    private ShiftRepository repository;

    @Override
    public Shift add(Shift shift) {
        return repository.saveAndFlush(shift);
    }

    @Override
    public Shift modify(Shift shift) {
        return repository.saveAndFlush(shift);
    }

    @Override
    public List<Shift> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Shift> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Shift> getByDoctor(String drId) {
        return repository.getAllByDoctor(drId);
    }

    @Override
    public Page<Shift> getByDoctor(String drId,Pageable pageable) {
        return repository.getAllByDoctor(drId,pageable);
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.deleteById(id);
            return Boolean.TRUE;
        }catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public ShiftRepository getRepository() {
        return null;
    }
}
