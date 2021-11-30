package com.assignment.patient.service;

import com.assignment.patient.bean.PatientInfo;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

/*
* Unit Test for PatientServiceImpl
*
* @Author Jewon Moon 11/28/2021
* */
class PatientServiceUnitTest {

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceUnitTest.class);
    private PatientInfo patientInfo;

    @BeforeEach
    void init() throws Exception{
        try {
            PatientServiceImpl patientService = new PatientServiceImpl();
            patientInfo = new PatientInfo(100, "JewonTest10", "MoonTest10", "2021-11-27");
            patientService.addNewPatient(patientInfo);
        } catch (Exception e){
            logger.debug("Error init() :", e);
        }
    }

    // Should retrieve data by search string
    @Test
    void canGetPatientInfoListBySearchString() throws Exception{
        logger.info("Start canGetPatientInfoListBySearchString()");
        try {
            PatientServiceImpl patientService = new PatientServiceImpl();
            String searchString = "JewonTest10";
            logger.info("searchString: " + searchString);

            Assertions.assertFalse(patientService.getPatientInfoList(searchString, "firstName").isEmpty());
            Assertions.assertEquals(1, patientService.getPatientInfoList(searchString, "firstName").size());
        } catch (Exception e){
            logger.debug("Error canGetPatientInfoListBySearchString() :", e);
        }
        logger.info("End canGetPatientInfoListBySearchString()");
    }

    // Should save new patient if all info is provided
    @Test
    void canAddNewPatient() throws Exception{
        logger.info("Start canAddNewPatient()");
        try {
            PatientServiceImpl patientService = new PatientServiceImpl();
            patientInfo = new PatientInfo(101, "JewonTest11", "MoonTest11", "2021-12-27");
            logger.info("new Patient Info: " + patientInfo);
            patientService.addNewPatient(patientInfo);
            String searchString = "JewonTest11";
            logger.info("searchString: " + searchString);

            Assertions.assertFalse(patientService.getPatientInfoList(searchString, "firstName").isEmpty());
            Assertions.assertEquals(1, patientService.getPatientInfoList(searchString, "firstName").size());
        } catch (Exception e){
            logger.debug("Error canAddNewPatient() :", e);
        }
        logger.info("End canAddNewPatient()");
    }

    // Should not proceed to save new patient if first name is null
    @Test
    void cannotAddNewPatientWithoutFirstName() throws Exception{
        logger.info("Start cannotAddNewPatientWithoutFirstName()");
        try {
            PatientServiceImpl patientService = new PatientServiceImpl();
            patientInfo = new PatientInfo(101, "", "MoonTest11", "2021-12-27");
            logger.info("new Patient Info: " + patientInfo);
            patientService.addNewPatient(patientInfo);
            String searchString = "JewonTest11";
            logger.info("searchString: " + searchString);

            Assertions.assertTrue(patientService.getPatientInfoList(searchString, "firstName").isEmpty());
            Assertions.assertEquals(0, patientService.getPatientInfoList(searchString, "firstName").size());
        } catch (Exception e){
            logger.debug("Error cannotAddNewPatientWithoutFirstName() :", e);
        }
        logger.info("End cannotAddNewPatientWithoutFirstName()");
    }

    // Should not proceed to save new patient if last name is null
    @Test
    void cannotAddNewPatientWithoutLastName() throws Exception{
        logger.info("Start cannotAddNewPatientWithoutLastName()");
        try {
            PatientServiceImpl patientService = new PatientServiceImpl();
            patientInfo = new PatientInfo(101, "JewonTest11", "", "2021-12-27");
            logger.info("new Patient Info: " + patientInfo);
            patientService.addNewPatient(patientInfo);
            String searchString = "JewonTest11";
            logger.info("searchString: " + searchString);

            Assertions.assertTrue(patientService.getPatientInfoList(searchString, "firstName").isEmpty());
            Assertions.assertEquals(0, patientService.getPatientInfoList(searchString, "firstName").size());
        } catch (Exception e){
            logger.debug("Error cannotAddNewPatientWithoutLastName() :", e);
        }
        logger.info("End cannotAddNewPatientWithoutLastName()");
    }

    // Should not proceed to save new patient if date of birth is null
    @Test
    void cannotAddNewPatientWithoutDob() throws Exception{
        logger.info("Start cannotAddNewPatientWithoutDob()");
        try {
            PatientServiceImpl patientService = new PatientServiceImpl();
            patientInfo = new PatientInfo(101, "JewonTest11", "MoonTest11", "");
            logger.info("new Patient Info: " + patientInfo);
            patientService.addNewPatient(patientInfo);
            String searchString = "JewonTest11";
            logger.info("searchString: " + searchString);

            Assertions.assertTrue(patientService.getPatientInfoList(searchString, "firstName").isEmpty());
            Assertions.assertEquals(0, patientService.getPatientInfoList(searchString, "firstName").size());
        } catch (Exception e){
            logger.debug("Error cannotAddNewPatientWithoutDob() :", e);
        }
        logger.info("End cannotAddNewPatientWithoutDob()");
    }
}