package com.js.appointment.service;

import com.js.appointment.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {

    public List<Doctor> getAllDoctor();

    public Page<Doctor> getAllDoctor(Pageable pageable);

    public Doctor getDoctorById(String id);

    public Doctor getDoctorByName(String name);

    public Doctor addDoctor(Doctor doctor);

    public Doctor modifyDoctor(Doctor doctor);

    public boolean deleteDoctor(String id);

    public List<Doctor> searchDoctorByName(String token);
}
