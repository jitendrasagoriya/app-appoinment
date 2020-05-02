package com.js.appointment.service;

import com.js.appointment.model.Shift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShiftService {
    public Shift add(Shift shift);

    public Shift modify(Shift shift);

    public List<Shift> getAll();

    public Page<Shift> getAll(Pageable pageable);

    public List<Shift> getByDoctor(String drId);

    public Page<Shift> getByDoctor(String drId,Pageable pageable);

    public boolean delete(Long id);

}
