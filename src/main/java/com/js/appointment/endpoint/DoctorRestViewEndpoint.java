package com.js.appointment.endpoint;


import com.js.appointment.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/doctor/view", produces = {MediaType.APPLICATION_JSON_VALUE })
public class DoctorRestViewEndpoint {

    private final Logger logger = LoggerFactory.getLogger(DoctorRestEndpoint.class);

    @Autowired
    private DoctorService doctorService;

    @GetMapping(path = {"/",""})
    public ResponseEntity<?> getDoctorByToken() {
        try {
            return new ResponseEntity<List>(doctorService.getAllDoctor(), HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<Exception>(e, HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping(path = {"/page/","/page"})
    public ResponseEntity<?> getAllDoctor(Pageable pageable) {
        try {
            return new ResponseEntity<Page>(doctorService.getAllDoctor(pageable), HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<Exception>(e, HttpStatus.EXPECTATION_FAILED);
        }

    }
}
