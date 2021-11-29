package com.assignment.patient.controller;

import com.assignment.patient.bean.PatientInfo;
import com.assignment.patient.exception.DataNotFoundException;
import com.assignment.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* Patient RESTController
*
* @Author Jewon Moon 11/27/2021
* */
@RestController
@RequestMapping(path = "api/patient")
public class PatientRestController {

    @Autowired
    private PatientService patientService;

    /*
     * Get all patients list
     *
     * @throws Exception, DataNotFoundException
     * @return List<PatientInfo> patientInfoList
     * */
    @GetMapping(path = "/list")
    public List<PatientInfo> getAllPatientList() throws Exception{
        List<PatientInfo> patientInfoList = patientService.getPatientInfoList("");
        // If result data list is null, throw exception.
        if(patientInfoList.size() == 0){
            throw new DataNotFoundException("Data Not Found");
        }

        return patientInfoList;
    }

    /*
     * Get selected patient by search string(first name)
     * @Param searchString
     *
     * @throws Exception, DataNotFoundException
     * @return List<PatientInfo> patientInfoList
     * */
    @PostMapping(path = "/list")
    public List<PatientInfo> getPatientListBySearchString(@RequestParam String searchString) throws Exception{
        List<PatientInfo> patientInfoList = patientService.getPatientInfoList(searchString);
        // If result data list is null, throw exception.
        if(patientInfoList.size() == 0){
            throw new DataNotFoundException("Data Not Found");
        }

        return patientInfoList;
    }

    /*
     * Add new patient
     * @Param firstName
     * @Param lastName
     * @Param dob
     *
     * @throws Exception, DataNotFoundException
     * @return String result(success or fail)
     * */
    @PostMapping(path = "/add/new")
    public String addNewPatient(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam String dob) throws Exception {
        Map<String, Object> mapResult = new HashMap<>();
        PatientInfo patientInfo = new PatientInfo(firstName, lastName, dob);
        mapResult = patientService.addNewPatient(patientInfo);

        String result = mapResult.get("result").toString();
        Boolean isValid = (Boolean) mapResult.get("isValid");
        if("fail".equals(result) && !isValid){
            throw new DataNotFoundException("Input data is not valid");
        }

        return result;
    }

    /*
     * Delete all patients info
     *
     * @throws Exception
     * */
    @GetMapping(path = "/delete/all")
    public void deleteAllPatient() throws Exception {
        patientService.deleteAllPatient();
    }
}
