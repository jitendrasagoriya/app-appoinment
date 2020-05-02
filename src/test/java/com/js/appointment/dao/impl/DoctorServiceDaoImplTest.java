package com.js.appointment.dao.impl;

import com.js.appointment.config.JpaTestConfig;
import com.js.appointment.config.TestConfig;
import com.js.appointment.model.Doctor;
import com.js.appointment.modelbuilder.DoctorBuilder;
import com.js.appointment.utils.UniqueUUId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import static org.assertj.core.api.Assertions.assertThat;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { JpaTestConfig.class, TestConfig.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@Sql(scripts = {"classpath:doctor-data.sql"})
public class DoctorServiceDaoImplTest {

    @Autowired
    private DoctorServiceDaoImpl doctorServiceDao;

    @Test
    public void testPersistDoctor() {
        Doctor doctor = DoctorBuilder.getDoctor();
        doctor.setId(UniqueUUId.getAlphaNumeric());
        doctorServiceDao.getRepository().save(doctor);
        Doctor response = doctorServiceDao.getRepository().getOne(doctor.getId());
        assertThat(response.getId())
                .isEqualTo(doctor.getId());
    }

    @Test
    public void testGetAllDoctors() {
        List<Doctor> doctors = doctorServiceDao.getAllDoctor();
        assertThat(doctors.size()).isEqualTo(4);
    }

    @Test
    public void testDoctorByName() {
        Doctor doctor = doctorServiceDao.getDoctorByName("JITENDRA");
        assertThat(doctor.getId()).isEqualTo("56989");
    }

    @Test
    public void testGetDoctorByLike() {
        List<Doctor> doctors = doctorServiceDao.searchDoctorByName("JIT");
        assertThat(doctors.size()).isEqualTo(2);
    }
}
