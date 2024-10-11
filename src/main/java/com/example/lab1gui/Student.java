package com.example.lab1gui;

public class Student{
    private String givenName;
    private String lastName;
    private String phoneNumber;
    private Float gpa;

    public Student(String givenName, String lastName, String phoneNumber, Float gpa){
        this.givenName = givenName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gpa = gpa;
    }

    public String getGivenName(){
        return givenName;
    }

    public void setGivenName(String givenName){
        this.givenName = givenName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public Float getGpa(){
        return gpa;
    }
    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }
}
