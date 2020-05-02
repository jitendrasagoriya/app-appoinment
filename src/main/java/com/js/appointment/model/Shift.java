package com.js.appointment.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SHIFT")
public class Shift {

    @javax.persistence.Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "DRID",nullable = false)
    private String drId;

    @Column(name = "SHIFTTIME",nullable = false)
    private String shiftTime;

    public Shift() {
    }

    public Shift(Long id,String drId, String shiftTime) {
        this.id = id;
        this.drId = drId;
        this.shiftTime = shiftTime;
    }

    public Shift(ShiftBuilder shiftBuilder) {
        this.id = shiftBuilder.id;
        this.drId = shiftBuilder.drId;
        this.shiftTime = shiftBuilder.shiftTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shift shift = (Shift) o;
        return Objects.equals(id, shift.id) &&
                Objects.equals(drId, shift.drId) &&
                Objects.equals(shiftTime, shift.shiftTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, drId, shiftTime);
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", drId='" + drId + '\'' +
                ", shiftTime='" + shiftTime + '\'' +
                '}';
    }

    public static class ShiftBuilder {

        private Long id;
        private String drId;
        private String shiftTime;

        public ShiftBuilder(Long id, String drId, String shiftTime) {
            this.id = id;
            this.drId = drId;
            this.shiftTime = shiftTime;
        }

        public ShiftBuilder() {
        }

        public ShiftBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ShiftBuilder drId(String drId) {
            this.drId = drId;
            return this;
        }

        public ShiftBuilder shiftTime(String shiftTime) {
            this.shiftTime = shiftTime;
            return this;
        }

        public Shift build() {
            Shift shift =  new Shift(this);
            validateUserObject(shift);
            return shift;
        }

        public String buildJson() throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            Shift shift =  new Shift(this);
            validateUserObject(shift);
            return objectMapper.writeValueAsString(shift);
        }

        private void validateUserObject(Shift authentication) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }

    }
}
