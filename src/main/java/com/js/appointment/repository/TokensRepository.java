package com.js.appointment.repository;

import com.js.appointment.model.Doctor;
import com.js.appointment.model.Tokens;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface TokensRepository extends JpaRepository<Tokens,Long> {

    @Query("SELECT T FROM Tokens T WHERE T.DrId = :doctorId AND T.shift = :shift")
    public Tokens getTokenByDoctorAndShift(@Param("doctorId") String doctorId,
                                           @Param("shift") String shift);

    @Query("SELECT T FROM Tokens T WHERE T.DrId = :doctorId")
    public List<Tokens> getTokenByDoctor(@Param("doctorId") String doctorId );

    @Query("SELECT T FROM Tokens T WHERE T.DrId = :doctorId")
    public Page <Tokens> getTokenByDoctor(@Param("doctorId") String doctorId, Pageable pageable);

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("UPDATE Tokens T SET T.currentToken = T.currentToken + :n WHERE T.Id = :id AND T.isCurrent = true AND T.currentToken <= T.MaxTOken")
    public void nextToken(@Param("id") Long id,@Param("n") Integer n);

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("UPDATE Tokens T SET T.MaxTOken = T.MaxTOken + :n WHERE T.Id = :id")
    public void maxTokenByN(@Param("id")Long id,
                            @Param("n")Integer n);


    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("UPDATE Tokens T SET T.currentToken = :n WHERE T.Id = :id")
    public void resetTokenByN(@Param("id") Long id,@Param("n")Integer n);

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("UPDATE Tokens T SET T.MaxTOken = :n WHERE T.Id = :id")
    public void resetMaxTokenByN(@Param("id")Long id,
                            @Param("n")Integer n);


    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("UPDATE Tokens T SET T.currentToken = T.currentToken + :n WHERE T.DrId = :drId AND T.isCurrent = true AND T.currentToken <= T.MaxTOken")
    public void nextTokenByDoctorId(@Param("drId") String drId,@Param("n") Integer n);

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("UPDATE Tokens T SET T.MaxTOken = T.MaxTOken + :n WHERE T.DrId = :drId AND T.isCurrent = true")
    public void maxTokenByNByDoctorId(@Param("drId")String drId,
                            @Param("n")Integer n);


    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("UPDATE Tokens T SET T.currentToken = :n WHERE T.DrId = :drId AND T.isCurrent = true")
    public void resetTokenByNByDoctorId(@Param("drId") String drId,@Param("n")Integer n);

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("UPDATE Tokens T SET T.MaxTOken = :n WHERE T.DrId = :drId AND T.isCurrent = true")
    public void resetMaxTokenByNByDoctorId(@Param("drId") String drId,
                                 @Param("n")Integer n);

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query("UPDATE Tokens T SET T.isCurrent = :current WHERE T.DrId = :drId AND T.shift = :shift AND T.tokenDate = :today")
    public void setCurrentTokenByDoctorIdAndShiftAndAndTokenDate(@Param("drId") String drId,
                                                                 @Param("shift") String shift,
                                                                 @Param("today") Date toDay,
                                                                 @Param("current") Boolean current  );

    @Query("SELECT T FROM Tokens T WHERE T.DrId = :doctorId AND T.isCurrent = true ")
    public Tokens getTokenByDoctorAndCurrent(@Param("doctorId") String doctorId);

    @Query("SELECT T FROM Tokens T WHERE T.DrId = :doctorId AND T.tokenDate = :today")
    public List<Tokens> getTokenByDoctorAndTokenDate(@Param("doctorId") String doctorId,
                                             @Param("today") Date today);

    @Query("SELECT COUNT (T) FROM Tokens T WHERE T.isCurrent = true")
    public Integer getCheckCurrentShift();

}
