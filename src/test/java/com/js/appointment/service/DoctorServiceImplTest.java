package com.js.appointment.service;

import com.js.appointment.dao.impl.DoctorServiceDaoImpl;
import com.js.appointment.model.Doctor;
import com.js.appointment.modelbuilder.DoctorBuilder;
import com.js.appointment.service.impl.DoctorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
public class DoctorServiceImplTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public DoctorService employeeService() {
            return new DoctorServiceImpl();
        }
    }

    @Autowired
    private DoctorService doctorService;

    @MockBean
    private DoctorServiceDaoImpl doctorServiceDao;

    @Test
    public void testGetAllDoctor() {
        List<Doctor> doctors = DoctorBuilder.getDoctorList();
        Mockito.when(doctorServiceDao.getAllDoctor())
                .thenReturn(doctors);
        assertThat(doctors.size()).isEqualTo(5);

    }
}
