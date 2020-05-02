package com.js.appointment.endpoint;



import com.js.appointment.model.AuthToken;
import com.js.appointment.model.Doctor;
import com.js.appointment.service.DoctorService;
import com.js.appointment.service.ShiftService;
import org.js.autenticationclient.auth.CommonAuthentication;
import org.js.autenticationclient.auth.impl.CommonAuthenticationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/doctor", produces = {MediaType.APPLICATION_JSON_VALUE })
public class DoctorRestEndpoint {

    private final Logger logger = LoggerFactory.getLogger(DoctorRestEndpoint.class);

    @Autowired
    private DoctorService doctorService;

    private CommonAuthentication authentication = new CommonAuthenticationImpl();

    @GetMapping(path = {"/",""})
    public ResponseEntity<?> getDoctorByToken(@SessionAttribute("ID") String Id) {

        if (logger.isDebugEnabled ()) {
            logger.debug("getDoctorByToken called");
            logger.debug("SessionAttribute : ID is "+ Id);
        }

        Doctor doctor = null;
        try {
            doctor =  doctorService.getDoctorById(Id);
            if (logger.isDebugEnabled()) {
                logger.debug("Method : getDoctorByToken, Doctor : "+doctor.toString());
            }
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<Exception>(e, HttpStatus.EXPECTATION_FAILED);
        } finally {
            if (logger.isDebugEnabled()) {
                logger.debug("getDoctorByToken end");
            }
        }

    }

    @PostMapping("/auth")
    public ResponseEntity<?> getAccessToken(@RequestParam(name = "username") String email,
                                            @RequestParam(name = "password") String password) {

        if (logger.isDebugEnabled()) {
            logger.debug("getAccessToken called");
            logger.debug("username : "+ email);
            logger.debug("password : "+ password);
        }
        try {
            String accessToken = authentication.getToken(email, password);
            AuthToken authToken = new AuthToken(accessToken);
            return new ResponseEntity<AuthToken>( authToken , HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<Exception>(e, HttpStatus.EXPECTATION_FAILED);
        } finally {
            if (logger.isDebugEnabled()) {
                logger.debug("getAccessToken end");
            }
        }

    }



    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) {
        try {
            return new ResponseEntity<Doctor>(doctorService.addDoctor(doctor) , HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<Exception>(e, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> modifyDoctor(@RequestBody Doctor doctor) {
        try {
            return new ResponseEntity<Doctor>(doctorService.modifyDoctor(doctor) , HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<Exception>(e, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
