package com.js.appointment.dao;

import com.js.appointment.model.Doctor;
import com.js.appointment.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface DoctorServiceDao extends BaseSerivce<DoctorRepository> {

    public Doctor getDoctorByName( String drName);

    public List<Doctor> getDoctorByLike(@Param("drName")  String drName);

    public List<Doctor> getAllDoctor();

    public Page<Doctor> getAllDoctor(Pageable pageable);

    public Doctor getDoctorById(String id);

    public Doctor addDoctor(Doctor doctor);

    public boolean deleteDoctor(String id);

    public List<Doctor> searchDoctorByName(String token);


}
