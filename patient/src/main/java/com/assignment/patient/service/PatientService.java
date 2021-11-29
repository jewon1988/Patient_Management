package com.assignment.patient.service;

import com.assignment.patient.bean.PatientInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
 * PatientService
 *
 * @Author Jewon Moon 11/27/2021
 * */
@Service
public interface PatientService {

    public List<PatientInfo> getPatientInfoList(String searchString) throws Exception;

    public Map<String, Object> addNewPatient(PatientInfo patientInfo) throws Exception;

    public void deleteAllPatient() throws Exception;
}
