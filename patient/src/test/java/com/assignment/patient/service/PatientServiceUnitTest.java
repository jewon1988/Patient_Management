package com.assignment.patient.service;

import com.assignment.patient.bean.PatientInfo;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
* Unit Test for PatientServiceImpl
*
* @Author Jewon Moon 11/28/2021
* */
class PatientServiceUnitTest {

    private PatientInfo patientInfo;

    @BeforeEach
    void init() throws Exception{
        PatientServiceImpl patientService = new PatientServiceImpl();
        patientInfo = new PatientInfo(100, "JewonTest10", "MoonTest10", "2021-11-27");
        patientService.addNewPatient(patientInfo);
    }

    // Should retrieve data by search string
    @Test
    void canGetPatientInfoListBySearchString() throws Exception{
        PatientServiceImpl patientService = new PatientServiceImpl();
        String searchString = "JewonTest10";

        Assertions.assertFalse(patientService.getPatientInfoList(searchString).isEmpty());
        Assertions.assertEquals(1, patientService.getPatientInfoList(searchString).size());
    }

    // Should save new patient if all info is provided
    @Test
    void canAddNewPatient() throws Exception{
        PatientServiceImpl patientService = new PatientServiceImpl();
        patientInfo = new PatientInfo(101, "JewonTest11", "MoonTest11", "2021-12-27");
        patientService.addNewPatient(patientInfo);
        String searchString = "JewonTest11";

        Assertions.assertFalse(patientService.getPatientInfoList(searchString).isEmpty());
        Assertions.assertEquals(1, patientService.getPatientInfoList(searchString).size());
    }

    // Should not proceed to save new patient if first name is null
    @Test
    void cannotAddNewPatientWithoutFirstName() throws Exception{
        PatientServiceImpl patientService = new PatientServiceImpl();
        patientInfo = new PatientInfo(101, "", "MoonTest11", "2021-12-27");
        patientService.addNewPatient(patientInfo);
        String searchString = "JewonTest11";

        Assertions.assertTrue(patientService.getPatientInfoList(searchString).isEmpty());
        Assertions.assertEquals(0, patientService.getPatientInfoList(searchString).size());
    }

    // Should not proceed to save new patient if last name is null
    @Test
    void cannotAddNewPatientWithoutLastName() throws Exception{
        PatientServiceImpl patientService = new PatientServiceImpl();
        patientInfo = new PatientInfo(101, "JewonTest11", "", "2021-12-27");
        patientService.addNewPatient(patientInfo);
        String searchString = "JewonTest11";

        Assertions.assertTrue(patientService.getPatientInfoList(searchString).isEmpty());
        Assertions.assertEquals(0, patientService.getPatientInfoList(searchString).size());
    }

    // Should not proceed to save new patient if date of birth is null
    @Test
    void cannotAddNewPatientWithoutDob() throws Exception{
        PatientServiceImpl patientService = new PatientServiceImpl();
        patientInfo = new PatientInfo(101, "JewonTest11", "MoonTest11", "");
        patientService.addNewPatient(patientInfo);
        String searchString = "JewonTest11";

        Assertions.assertTrue(patientService.getPatientInfoList(searchString).isEmpty());
        Assertions.assertEquals(0, patientService.getPatientInfoList(searchString).size());
    }
}