package com.task.cards.xmlLogic;

import java.util.ArrayList;

public class Employee {
    private String firstname, lastname, secondname, position;
    private ArrayList<String> emailList, phoneList;

    public Employee(String firstname, String lastname, String secondname, String position) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.secondname = secondname;
        this.position = position;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getPosition() {
        return position;
    }

    public ArrayList<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(ArrayList<String> emailList) {
        this.emailList = emailList;
    }

    public ArrayList<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(ArrayList<String> phoneList) {
        this.phoneList = phoneList;
    }
}
