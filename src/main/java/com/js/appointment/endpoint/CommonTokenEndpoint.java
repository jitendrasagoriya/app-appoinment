package com.js.appointment.endpoint;


import com.js.appointment.exception.TokenNotFoundException;
import com.js.appointment.model.CurrentAndMaxToken;
import com.js.appointment.service.TokensService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/token/", produces = {MediaType.APPLICATION_JSON_VALUE })
public class CommonTokenEndpoint {

    private final Logger logger = LoggerFactory.getLogger(CommonTokenEndpoint.class);

    @Autowired
    private TokensService tokensService;

    @GetMapping(path = {"{ID}/","{ID}"})
    public ResponseEntity<?> getALlTokenByDoctor(@PathVariable(name = "ID") String id ) {
        try {
            return new ResponseEntity<>(tokensService.getTokenByDoctor(id), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(path = {"page/{ID}/","page/{ID}"})
    public ResponseEntity<?> getALlTokenByDoctor(@PathVariable(name = "ID") String id , Pageable pageable) {
        try {
            return new ResponseEntity<>(tokensService.getTokenByDoctor(id,pageable), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(path = {"{ID}/current/","{ID}/current"})
    public ResponseEntity<?> nextToken(@PathVariable(name = "ID") String id ) {
        try {
            return new ResponseEntity<Integer>(tokensService.getCurrentToken(id), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity< >(e.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(path = {"{ID}/currentAndMax/","{ID}/currentAndMax"})
    public ResponseEntity<?> currentToken(@PathVariable(name = "ID") String id ) {
        try {
            return new ResponseEntity<CurrentAndMaxToken>
                    (new CurrentAndMaxToken(tokensService.getCurrentToken(id),tokensService.getMaxToken(id)),
                            HttpStatus.OK);
        }catch (TokenNotFoundException e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity< >(e.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(path = {"{ID}/max/","{ID}/max"})
    public ResponseEntity<?> maxToken(@PathVariable(name = "ID") String id ) {
        try {
            return new ResponseEntity<Integer>(tokensService.getMaxToken(id), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
