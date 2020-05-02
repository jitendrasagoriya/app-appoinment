package com.js.appointment.dao.impl;

import com.js.appointment.dao.DoctorServiceDao;
import com.js.appointment.model.Doctor;
import com.js.appointment.repository.DoctorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceDaoImpl implements DoctorServiceDao {

    private final Logger logger = LoggerFactory.getLogger(DoctorServiceDaoImpl.class);

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public DoctorRepository getRepository() {
        return doctorRepository;
    }

    @Override
    public Doctor getDoctorByName(String drName) {
        return doctorRepository.getDoctorByName(drName);
    }

    @Override
    public List<Doctor> getDoctorByLike(String drName) {
        return doctorRepository.getDoctorByLike(drName);
    }

    @Override
    public List<Doctor> getAllDoctor() {
        return doctorRepository.findAll();
    }

    @Override
    public Page<Doctor> getAllDoctor(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }

    @Override
    public Doctor getDoctorById(String id) {
        return doctorRepository.findById(id).get();
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public boolean deleteDoctor(String id) {
        Doctor dr = null;
        try {
            dr = doctorRepository.getOne(id);
            if (dr == null)
                return false;
            doctorRepository.delete(dr);
            return true;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    @Override
    public List<Doctor> searchDoctorByName(String token) {
        return doctorRepository.getDoctorByLike(token);
    }
}
