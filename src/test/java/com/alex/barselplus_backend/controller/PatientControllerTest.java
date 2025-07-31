package com.alex.barselplus_backend.controller;

import com.alex.barselplus_backend.dto.PatientDTO;
import com.alex.barselplus_backend.service.PatientService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = PatientController.class)
public class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PatientService patientService;

    @Test
    public void testFindPatientByNationalId_ReturnsPatientDTO() throws Exception {
        // basic mock of the DTO?
        PatientDTO mockPatient = new PatientDTO();
        mockPatient.setFirstName("Jane");
        mockPatient.setLastName("Doe");
        mockPatient.setPhoneNumber("12345678");

        when(patientService.findPatientByNationalID(123456789L)).thenReturn(mockPatient);

        mockMvc.perform(get("/api/patient/123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.phoneNumber").value("12345678"));
    }

    @Test
    public void testFindPatientByNationalId_NotFound() throws Exception {
        when(patientService.findPatientByNationalID(anyLong()))
                .thenThrow(new RuntimeException("Patient not found"));

        mockMvc.perform(get("/api/patient/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Patient not found"));
    }
}
