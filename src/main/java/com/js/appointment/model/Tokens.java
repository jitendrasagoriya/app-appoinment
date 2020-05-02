package com.js.appointment.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "TOKENS")
public class Tokens implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long Id;

    @Column(name = "DRID" , nullable = false)
    private String DrId;

    @Column(name = "TOKENDATE" , nullable = false)
    private Date tokenDate;

    @Column(name = "CURRENTTOKEN" , nullable = false)
    private Integer currentToken;

    @Column(name = "MAXTOKEN" , nullable = false)
    private Integer MaxTOken;

    @Column(name = "CURRENT" , nullable = false)
    private Boolean isCurrent;

    @Column(name = "SHIFT" , nullable = false)
    private String shift;

    @PrePersist
    public void prePersist() {
        this.setMaxTOken(0);
        this.setCurrentToken(0);
        this.setTokenDate(new Date(System.currentTimeMillis()));
        this.setCurrent(Boolean.FALSE);
    }

    public Tokens(TokensBuilder tokensBuilder) {
        this.Id = tokensBuilder.Id ;
        this.DrId = tokensBuilder.drId;
        this.tokenDate = tokensBuilder.tokenDate;
        this.currentToken = tokensBuilder.currentToken;
        this.MaxTOken = tokensBuilder.maxToken;
        this.shift = tokensBuilder.shift;
    }

    public Tokens() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDrId() {
        return DrId;
    }

    public void setDrId(String drId) {
        DrId = drId;
    }

    public Date getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(Date tokenDate) {
        this.tokenDate = tokenDate;
    }

    public Integer getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(Integer currentToken) {
        this.currentToken = currentToken;
    }

    public Integer getMaxTOken() {
        return MaxTOken;
    }

    public void setMaxTOken(Integer maxTOken) {
        MaxTOken = maxTOken;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tokens tokens = (Tokens) o;
        return Objects.equals(Id, tokens.Id) &&
                Objects.equals(DrId, tokens.DrId) &&
                Objects.equals(tokenDate, tokens.tokenDate) &&
                Objects.equals(currentToken, tokens.currentToken) &&
                Objects.equals(MaxTOken, tokens.MaxTOken) &&
                Objects.equals(isCurrent, tokens.isCurrent) &&
                Objects.equals(shift, tokens.shift);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, DrId, tokenDate, currentToken, MaxTOken, isCurrent, shift);
    }

    @Override
    public String toString() {
        return "Tokens{" +
                "Id=" + Id +
                ", DrId='" + DrId + '\'' +
                ", tokenDate=" + tokenDate +
                ", currentToken=" + currentToken +
                ", MaxTOken=" + MaxTOken +
                ", isCurrent=" + isCurrent +
                ", shift='" + shift + '\'' +
                '}';
    }

    public static class TokensBuilder {

        private Long Id;
        private String drId;
        private Long shiftId;
        private Date tokenDate;
        private Integer currentToken;
        private Integer maxToken;
        private Boolean isCurrent;
        private String shift;

        public TokensBuilder() {
        }

        public TokensBuilder id(Long id) {
            Id = id;
            return this;
        }

        public TokensBuilder drId(String drId) {
            this.drId = drId;
            return this;
        }

        public TokensBuilder shiftId(Long shiftId) {
            this.shiftId = shiftId;
            return this;
        }

        public TokensBuilder tokenDate(Date tokenDate) {
            this.tokenDate = tokenDate;
            return this;
        }

        public TokensBuilder currentToken(Integer currentToken) {
            this.currentToken = currentToken;
            return this;
        }

        public TokensBuilder maxTOken(Integer maxTOken) {
            this.maxToken = maxTOken;
            return this;
        }

        public TokensBuilder current(Boolean current) {
            this.isCurrent = current;
            return this;
        }

        public TokensBuilder shift(String shift) {
            this.shift = shift;
            return this;
        }


        //Return the finally consrcuted User object
        public Tokens build() {
            Tokens tokens =  new Tokens(this);
            validateUserObject(tokens);
            return tokens;
        }



        public String buildJson() throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            Tokens tokens =  new Tokens(this);
            validateUserObject(tokens);
            return objectMapper.writeValueAsString(tokens);
        }

        private void validateUserObject(Tokens authentication) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }
}
