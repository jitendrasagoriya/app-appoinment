package com.js.appointment.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.BooleanUtils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "DOCTOR")
public class Doctor {

    @javax.persistence.Id
    @Column(name = "ID", length = 150)
    private String Id;

    @Column(name = "DRNAME", nullable = false)
    private String name;

    @Column(name = "HOSPITAL")
    private String Hospital;

    @Column(name = "DEGREE",nullable = false)
    private String Degree;

    @Column(name = "SPECIALIST")
    private String specialist;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "LANDLINE")
    private String landLine;

    @Column(name = "APPONPHONE")
    private Boolean appoinmentOnPhone;

    @Column(name = "ISEMAILSEND")
    private Boolean isEmailSend;

    @Column(name = "ISSMSSEND")
    private Boolean isSmsSend;

    @Column(name = "ISACTIVE")
    private Boolean isActive;

    @Column(name = "CREATIONTIME")
    private Timestamp creationTime;

    @Column(name = "LASTMODIFIEDTIME")
    private Timestamp lastModifiedTime;

    @Transient
    private String password;

    @Transient
    private String userName;

    public Doctor(DoctorBuilder doctorBuilder) {
        this.Id = doctorBuilder.Id;
        this.name = doctorBuilder.name;
        this. Hospital = doctorBuilder.Hospital;
        this.Degree = doctorBuilder.Degree;
        this.specialist = doctorBuilder.specialist;
        this.address = doctorBuilder.address;
        this.mobile = doctorBuilder.mobile;
        this.landLine = doctorBuilder.landLine;
        this.appoinmentOnPhone = doctorBuilder.appoinmentOnPhone;
        this.isEmailSend = doctorBuilder.isEmailSend;
        this.isSmsSend = doctorBuilder.isSmsSend;
        this.isActive = doctorBuilder.isActive;
        this.creationTime = doctorBuilder.creationTime;
        this.lastModifiedTime = doctorBuilder.lastModifiedTime;
        this.userName = doctorBuilder.userName;
        this.password = doctorBuilder.password;
    }

    @PrePersist
    public void prePersist() {
        this.setCreationTime(new Timestamp(System.currentTimeMillis()));
        this.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
    }

    public Doctor() {
    }

    public Doctor(String id, String name, String hospital, String degree, String specialist, String address, String mobile, String landLine, Boolean appoinmentOnPhone, boolean isEmailSend, boolean isSmsSend, boolean isActive, Timestamp creationTime, Timestamp lastModifiedTime) {
        Id = id;
        this.name = name;
        Hospital = hospital;
        Degree = degree;
        this.specialist = specialist;
        this.address = address;
        this.mobile = mobile;
        this.landLine = landLine;
        this.appoinmentOnPhone = appoinmentOnPhone;
        this.isEmailSend = isEmailSend;
        this.isSmsSend = isSmsSend;
        this.isActive = isActive;
        this.creationTime = creationTime;
        this.lastModifiedTime = lastModifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return isEmailSend == doctor.isEmailSend &&
                isSmsSend == doctor.isSmsSend &&
                isActive == doctor.isActive &&
                Objects.equals(Id, doctor.Id) &&
                Objects.equals(name, doctor.name) &&
                Objects.equals(Hospital, doctor.Hospital) &&
                Objects.equals(Degree, doctor.Degree) &&
                Objects.equals(specialist, doctor.specialist) &&
                Objects.equals(address, doctor.address) &&
                Objects.equals(mobile, doctor.mobile) &&
                Objects.equals(landLine, doctor.landLine) &&
                Objects.equals(appoinmentOnPhone, doctor.appoinmentOnPhone) &&
                Objects.equals(creationTime, doctor.creationTime) &&
                Objects.equals(lastModifiedTime, doctor.lastModifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, Hospital, Degree, specialist, address, mobile, landLine, appoinmentOnPhone, isEmailSend, isSmsSend, isActive, creationTime, lastModifiedTime);
    }

    @Override
    public String toString() {
        return "{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", Hospital='" + Hospital + '\'' +
                ", Degree='" + Degree + '\'' +
                ", specialist='" + specialist + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", landLine='" + landLine + '\'' +
                ", appoinmentOnPhone=" + appoinmentOnPhone +
                ", isEmailSend=" + isEmailSend +
                ", isSmsSend=" + isSmsSend +
                ", isActive=" + isActive +
                ", creationTime=" + creationTime +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital() {
        return Hospital;
    }

    public void setHospital(String hospital) {
        Hospital = hospital;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLandLine() {
        return landLine;
    }

    public void setLandLine(String landLine) {
        this.landLine = landLine;
    }

    public Boolean getAppoinmentOnPhone() {
        return appoinmentOnPhone;
    }

    public void setAppoinmentOnPhone(Boolean appoinmentOnPhone) {
        this.appoinmentOnPhone = appoinmentOnPhone;
    }

    public boolean isEmailSend() {
        return isEmailSend;
    }

    public void setEmailSend(boolean emailSend) {
        isEmailSend = emailSend;
    }

    public boolean isSmsSend() {
        return isSmsSend;
    }

    public void setSmsSend(boolean smsSend) {
        isSmsSend = smsSend;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Timestamp lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Boolean getEmailSend() {
        return isEmailSend;
    }

    public void setEmailSend(Boolean emailSend) {
        isEmailSend = emailSend;
    }

    public Boolean getSmsSend() {
        return isSmsSend;
    }

    public void setSmsSend(Boolean smsSend) {
        isSmsSend = smsSend;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static class DoctorBuilder {
        private String Id;
        private String name;
        private String Hospital;
        private String Degree;
        private String specialist;
        private String address;
        private String mobile;
        private String landLine;
        private Boolean appoinmentOnPhone;
        private boolean isEmailSend;
        private boolean isSmsSend;
        private boolean isActive;
        private Timestamp creationTime;
        private Timestamp lastModifiedTime;
        private String userName;
        private String password;


        public DoctorBuilder() {
        }


        public DoctorBuilder(String id, String name) {
            Id = id;
            this.name = name;
        }


        public DoctorBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public DoctorBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public DoctorBuilder Id(String id) {
            this.Id = id;
            return this;
        }

        public DoctorBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public DoctorBuilder setHospital(String hospital) {
            this.Hospital = hospital;
            return this;
        }

        public DoctorBuilder setDegree(String degree) {
            this.Degree = degree;
            return this;
        }

        public DoctorBuilder setSpecialist(String specialist) {
            this.specialist = specialist;
            return this;
        }

        public DoctorBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public DoctorBuilder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public DoctorBuilder setLandLine(String landLine) {
            this.landLine = landLine;
            return this;
        }

        public DoctorBuilder setAppoinmentOnPhone(Boolean appoinmentOnPhone) {
            if(appoinmentOnPhone != null)
                this.appoinmentOnPhone = appoinmentOnPhone;
            else
                this.appoinmentOnPhone = Boolean.FALSE;
            return this;
        }

        public DoctorBuilder setEmailSend(boolean emailSend) {
            this.isEmailSend = emailSend;
            return this;
        }

        public DoctorBuilder setSmsSend(boolean smsSend) {
            this.isSmsSend = smsSend;
            return this;
        }

        public DoctorBuilder setActive(boolean active) {
            this.isActive = active;
            return this;
        }

        public DoctorBuilder setCreationTime(Timestamp creationTime) {
            this.creationTime = creationTime;
            return this;
        }

        public DoctorBuilder setLastModifiedTime(Timestamp lastModifiedTime) {
            this.lastModifiedTime = lastModifiedTime;
            return  this;
        }

        //Return the finally consrcuted User object
        public Doctor build() {
            Doctor doctor =  new Doctor(this);
            validateUserObject(doctor);
            return doctor;
        }

        public String buildJson() throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            Doctor doctor =  new Doctor(this);
            validateUserObject(doctor);
            return objectMapper.writeValueAsString(doctor);
        }
        private void validateUserObject(Doctor doctor) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }


}
