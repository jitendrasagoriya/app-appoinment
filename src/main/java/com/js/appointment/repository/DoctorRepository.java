package com.js.appointment.repository;

import com.js.appointment.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("DoctorRepository")
public interface DoctorRepository extends JpaRepository<Doctor,String> {

    @Query("SELECT DR FROM Doctor DR WHERE DR.name = :name")
    public Doctor getDoctorByName(@Param("name")  String name);

    @Query("SELECT DR FROM Doctor DR WHERE DR.name like %:drName%")
    public List<Doctor> getDoctorByLike(@Param("drName")  String drName);
}
