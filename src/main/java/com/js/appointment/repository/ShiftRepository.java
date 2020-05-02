package com.js.appointment.repository;

import com.js.appointment.model.Shift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift,Long> {

    @Query("SELECT S FROM Shift S WHERE S.drId= :drId")
    public List<Shift> getAllByDoctor(@Param("drId") String drId);

    @Query("SELECT S FROM Shift S WHERE S.drId= :drId")
    public Page<Shift> getAllByDoctor(@Param("drId") String drId, Pageable pageable);
}
