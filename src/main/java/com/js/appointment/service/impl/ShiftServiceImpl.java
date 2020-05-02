package com.js.appointment.service.impl;

import com.js.appointment.dao.ShiftServiceDao;
import com.js.appointment.model.Shift;
import com.js.appointment.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService {



    @Autowired
    private ShiftServiceDao shiftServiceDao;

    @Override
    public Shift add(Shift shift) {
        return shiftServiceDao.add(shift);
    }

    @Override
    public Shift modify(Shift shift) {
        return shiftServiceDao.modify(shift);
    }

    @Override
    public List<Shift> getAll() {
        return shiftServiceDao.getAll();
    }

    @Override
    public Page<Shift> getAll(Pageable pageable) {
        return shiftServiceDao.getAll(pageable);
    }

    @Override
    public List<Shift> getByDoctor(String drId) {
        return shiftServiceDao.getByDoctor(drId);
    }

    @Override
    public Page<Shift> getByDoctor(String drId, Pageable pageable) {
        return shiftServiceDao.getByDoctor(drId,pageable);
    }

    @Override
    public boolean delete(Long id) {
        return shiftServiceDao.delete(id);
    }
}
