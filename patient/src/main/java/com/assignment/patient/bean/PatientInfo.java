package com.assignment.patient.bean;

import org.json.simple.JSONObject;

/*
 * PatientInfo
 *
 * @Author Jewon Moon 11/27/2021
 * */
public class PatientInfo {
    private Integer id;
    private String firstName;
    private String lastName;
    private String dob;

    public PatientInfo(){

    }

    public PatientInfo(Integer id, String firstName, String lastName, String dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public PatientInfo(String firstName, String lastName, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public PatientInfo(JSONObject jsonObject){
        this.id = Integer.parseInt(jsonObject.get("id").toString());
        this.firstName = jsonObject.get("firstName").toString();
        this.lastName = jsonObject.get("lastName").toString();
        this.dob = jsonObject.get("dob").toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "PatientInfo{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
