package com.js.appointment.endpoint;

import com.js.appointment.model.Doctor;
import com.js.appointment.modelbuilder.DoctorBuilder;
import com.js.appointment.service.DoctorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(DoctorRestEndpoint.class)
public class DoctorRestEndpointTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DoctorService doctorService;

    @Test
    public void givenDoctor_404()throws Exception {
        List<Doctor> doctors = DoctorBuilder.getDoctorList();
        given(doctorService.getAllDoctor()).willReturn(doctors);
        mvc.perform(get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenDoctor_200() throws Exception {
        List<Doctor> doctors = DoctorBuilder.getDoctorList();
        given(doctorService.getAllDoctor()).willReturn(doctors);
        mvc.perform(get("/api/doctor/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].name", is(doctors.get(0).getName())));
    }


}
