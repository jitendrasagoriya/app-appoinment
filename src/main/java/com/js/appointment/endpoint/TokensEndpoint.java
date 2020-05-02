package com.js.appointment.endpoint;

import com.js.appointment.exception.TokenNotFoundException;
import com.js.appointment.model.CurrentAndMaxToken;
import com.js.appointment.model.Shift;
import com.js.appointment.model.Tokens;
import com.js.appointment.service.ShiftService;
import com.js.appointment.service.TokensService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/admin/token/", produces = {MediaType.APPLICATION_JSON_VALUE })
public class TokensEndpoint {

    private final Logger logger = LoggerFactory.getLogger(TokensEndpoint.class);

    @Autowired
    private TokensService tokensService;

    @Autowired
    private ShiftService shiftService;

    @PostMapping({"","/"})
    public ResponseEntity<?> newToken(@RequestParam(name = "shift") String shiftId,
                                            @SessionAttribute("ID") String Id) {
        try {
            Tokens tokens = new Tokens.TokensBuilder()
                    .shift(shiftId)
                    .drId(Id)
                    .build();
            return new ResponseEntity<Tokens>(tokensService.addToken(tokens), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity< >(e.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping({"","/"})
    public ResponseEntity<?> getTokenByDoctor(@SessionAttribute("ID") String Id,
                                            Pageable pageable) {
        try {
            return new ResponseEntity<>(tokensService.getTokenByDoctor(Id,pageable), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity< >(e.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping({"today","today/"})
    public ResponseEntity<?> getTodayTokenByDoctor(@SessionAttribute("ID") String Id ) {
        try {
            return new ResponseEntity<>(tokensService.getTodayTokenByDoctor(Id), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity< >(e.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping(path = {"next/","next"})
    public ResponseEntity<?> nextToken(@SessionAttribute(name = "ID") String id ) {
        try {
            return new ResponseEntity<Integer>(tokensService.nextToken(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity< >(e.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(path = {"/currentAndMax/","/currentAndMax"})
    public ResponseEntity<?> currentToken(@SessionAttribute(name = "ID") String id ) {
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


    @PutMapping(path = {"max/","max"})
    public ResponseEntity<?> maxToken(@SessionAttribute(name = "ID") String id,
                                      @RequestParam("maxcount") Integer maxCount) {
        try {
            Integer max =  tokensService.getMaxToken(id);
            tokensService.resetMaxTokenToN(id,(maxCount + max ) );
            return new ResponseEntity<Integer>(tokensService.getMaxToken(id), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity< >(e.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping(path = {"current/","current"})
    public ResponseEntity<?> setCurrentToken(@RequestParam(name = "ID") Long id ,
                                             @SessionAttribute(name = "ID") String drId) {
        try {
              Tokens  token = tokensService.getTokenByDoctorAndCurrentTokens(drId);
            if(token != null && token.getId() != id) {
                throw new Exception("There is already a current token");
            } else {
                if(token != null && token.getCurrent() == Boolean.TRUE)
                return new ResponseEntity<>(tokensService.setCurrentShiftFalseById(id)
                        , HttpStatus.OK);
            }
            return new ResponseEntity<>(tokensService.setCurrentShiftTrueById( id)
                    , HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity< >(e.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }



}
