package com.assignment.patient.service;

import com.assignment.patient.bean.PatientInfo;
import com.assignment.patient.exception.DataNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Implements for PatientService
 *
 * @Author Jewon Moon 11/27/2021
 * */
@Service("PatientService")
public class PatientServiceImpl implements PatientService {
    // Declare global variable for patient list because there is no database. If it can get a data from database, it should be a local variable.
    private static List<PatientInfo> allPatientInfoList = new ArrayList<>();

    // Get patient list
    public List<PatientInfo> getPatientInfoList(String searchString) throws Exception {
        // Get patients' data from embedded JSON file at first
        if(allPatientInfoList.size() == 0){
            this.generateJSONData();
        }

        // Search patients by search string and get all results
        List<PatientInfo> patientInfoList = new ArrayList<>();
        try {
            patientInfoList = allPatientInfoList.stream()
                    .filter(o -> o.getFirstName().equalsIgnoreCase(searchString) || "".equals(searchString))
                    .collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }

        return patientInfoList;
    }

    // Generate data from embedded JSON file and store data to allPatientInfoList
    private void generateJSONData() {
        try {
            JSONParser jsonParser = new JSONParser();
            // Get data from embedded JSON file
            JSONArray patientJsonArray = (JSONArray) jsonParser.parse(new FileReader("src/main/patient.json"));
            if(patientJsonArray != null && patientJsonArray.size() > 0){
                // store data to allPatientInfoList
                for(Object object : patientJsonArray){
                    JSONObject patientJsonObject = (JSONObject) object;
                    PatientInfo patientInfo = new PatientInfo(patientJsonObject);
                    allPatientInfoList.add(patientInfo);
                }
            } else {
                throw new DataNotFoundException("JSON Data Not Found");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Add new patient
    public Map<String, Object> addNewPatient(PatientInfo newPatientInfo) throws Exception {
        Map<String, Object> mapResult = new HashMap<>();
        String result = "fail";
        Boolean isValid;
        // Get maxIdx from the existing patients list for new patient's id
        int maxIdx = allPatientInfoList.size();
        if(newPatientInfo != null){
            if(newPatientInfo.getId() == null){
                newPatientInfo.setId(maxIdx + 1);
            }
            // Validate new patient info to confirm that all variables are not null
            isValid = this.validatePatientInfo(newPatientInfo);
            if(isValid){
                // Save new patient's data
                Boolean isDuplicated = allPatientInfoList.stream().anyMatch(o -> o.getId().equals(newPatientInfo.getId()));
                if(!isDuplicated){
                    allPatientInfoList.add(newPatientInfo);
                    result = "success";
                }
            }
        } else {
            throw new DataNotFoundException("Input Data Missing");
        }

        mapResult.put("result", result);
        mapResult.put("isValid", isValid);
        return mapResult;
    }

    // Validate new patient info
    private Boolean validatePatientInfo(PatientInfo newPatientInfo) throws Exception {
        Boolean isValid = true;
        if("".equalsIgnoreCase(newPatientInfo.getFirstName())){
            isValid = false;
        } else if("".equalsIgnoreCase(newPatientInfo.getLastName())){
            isValid = false;
        } else if("".equals(newPatientInfo.getDob())){
            isValid = false;
        } else if(!"".equals(newPatientInfo.getDob())){
            try {
                // Validate date format
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(newPatientInfo.getDob());
                if(!newPatientInfo.getDob().equals(sdf.format(date))){
                    isValid = false;
                }
            } catch (ParseException pe){
                pe.printStackTrace();
            }
        }

        return isValid;
    }

    // Delete all patients data
    public void deleteAllPatient() {
        allPatientInfoList.clear();
    }
}
