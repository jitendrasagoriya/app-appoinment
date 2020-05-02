package com.js.appointment.repository;

import com.js.appointment.model.Doctor;
import com.js.appointment.modelbuilder.DoctorBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class DoctorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void testDoctorByName() {
        Doctor doctor = DoctorBuilder.getDoctor();
        entityManager.persist(doctor);
        entityManager.flush();
        Doctor result = doctorRepository.getDoctorByName(doctor.getName());
        assertThat(doctor.getName())
                .isEqualTo(result.getName());
    }

    @Test
    public void tetDoctorByLike() {
        DoctorBuilder.getDoctorList().forEach((doctor -> {
            entityManager.persist(doctor);
            entityManager.flush();
        }));

        List<Doctor> result = doctorRepository.getDoctorByLike("jitendra");
        assertThat(result.size())
                .isEqualTo(2);

        result = doctorRepository.getDoctorByLike("jit");
        assertThat(result.size())
                .isEqualTo(3);

    }
}
