package com.js.appointment.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.js.autenticationclient.bean.Authentication;
import org.js.autenticationclient.registration.Registration;
import org.js.autenticationclient.registration.impl.RegistrationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.appointment.dao.DoctorServiceDao;
import com.js.appointment.model.Doctor;
import com.js.appointment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    private Registration registration = new RegistrationImpl();

    @Autowired
    private DoctorServiceDao doctorServiceDao;

    @Override
    public List<Doctor> getAllDoctor() {
        return doctorServiceDao.getAllDoctor();
    }

    @Override
    public Page<Doctor> getAllDoctor(Pageable pageable) {
        return doctorServiceDao.getAllDoctor(pageable);
    }

    @Override
    public Doctor getDoctorById(String id) {
        return doctorServiceDao.getDoctorById(id);
    }

    @Override
    public Doctor getDoctorByName(String name) {
        return doctorServiceDao.getDoctorByName(name);
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {

        Authentication member = null;
        Authentication authentication = new Authentication.AuthenticationBuilder
                (doctor.getUserName(),doctor.getPassword())
                .build();

        try {
            member = registration.registerYouMember(authentication);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(),e);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        if(member != null ){
            doctor.setId(member.getUserId());
            return doctorServiceDao.addDoctor(doctor);
        }
        return null;

    }

    @Override
    public Doctor modifyDoctor(Doctor doctor) {
        return doctorServiceDao.addDoctor(doctor);
    }

    @Override
    public boolean deleteDoctor(String id) {
        return doctorServiceDao.deleteDoctor(id);
    }

    @Override
    public List<Doctor> searchDoctorByName(String token) {
        return doctorServiceDao.searchDoctorByName(token);
    }
}
