package com.patikadev.Model;

public class AgencyEmployee extends User {
    public AgencyEmployee() {
    }

    public AgencyEmployee(int id, String name, String uname, String password) {
        super(id, name, uname, password, "employee");
    }
}
