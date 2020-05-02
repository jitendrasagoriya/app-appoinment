package com.js.appointment.modelbuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.js.appointment.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorBuilder {

    public static Doctor getDoctor() {
        return new Doctor.DoctorBuilder("123456","jitendra")
                .setActive(true)
                .setDegree("MBBS")
                .build();
    }

    public static List<Doctor> getDoctorList() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add( new Doctor.DoctorBuilder("123456","jitendra sagoriya")
                .setActive(true)
                .setDegree("MBBS")
                .build());
        doctors.add( new Doctor.DoctorBuilder("445656","jitendra singh")
                .setActive(true)
                .setDegree("MBBS")
                .build());
        doctors.add( new Doctor.DoctorBuilder("34545","jit singh")
                .setActive(true)
                .setDegree("MBBS")
                .build());

        doctors.add( new Doctor.DoctorBuilder("5765755","purnima singh")
                .setActive(true)
                .setDegree("MBBS")
                .build());

        doctors.add( new Doctor.DoctorBuilder("345456","Amit kumar")
                .setActive(true)
                .setDegree("MBBS")
                .build());
        return doctors;
    }

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println( new Doctor.DoctorBuilder("123456","jitendra")
                .setActive(true)
                .setDegree("MBBS").buildJson());
    }
}
