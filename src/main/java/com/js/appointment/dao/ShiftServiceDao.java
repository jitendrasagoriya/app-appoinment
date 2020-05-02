package com.js.appointment.dao;

import com.js.appointment.model.Shift;
import com.js.appointment.repository.ShiftRepository;
import org.hibernate.annotations.LazyToOne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShiftServiceDao extends BaseSerivce<ShiftRepository> {

    public Shift add(Shift shift);

    public Shift modify(Shift shift);

    public List<Shift> getAll();

    public Page<Shift> getAll(Pageable pageable);

    public List<Shift> getByDoctor(String drId);

    public Page<Shift> getByDoctor(String drId,Pageable pageable);

    public boolean delete(Long id);


}
