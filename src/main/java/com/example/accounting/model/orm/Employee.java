package com.example.accounting.model.orm;

import com.example.accounting.model.EmployeeJdbc;
import com.example.accounting.model.Rank;

public class Employee {
    private EmployeeJdbc employeeJdbc = new EmployeeJdbc();
    private Department department = new Department();
    private Rank rank = new Rank();
//    private Integer departmentId;
//    private Integer RankId;

    public Employee() {
    }

    public Employee(EmployeeJdbc employeeJdbc, Department department, Rank rank) {
        this.employeeJdbc = employeeJdbc;
        this.department = department;
        this.rank = rank;
    }

    public Integer getId() {
        return employeeJdbc.getId();
    }

    public String getLastName() {
        return employeeJdbc.getLastName();
    }

    public String getFirstName() {
        return employeeJdbc.getFirstName();
    }

    public String getPatronymic() {
        return employeeJdbc.getPatronymic();
    }

    public void setLastName(String lastName) {
        employeeJdbc.setLastName(lastName);
    }

    public void setFirstName(String firstName) {
        employeeJdbc.setFirstName(firstName);
    }

    public void setPatronymic(String patronymic) {
        employeeJdbc.setPatronymic(patronymic);
    }

    public EmployeeJdbc getEmployeeJdbc() {
        return employeeJdbc;
    }

    public void setEmployeeJdbc(EmployeeJdbc employeeJdbc) {
        this.employeeJdbc = employeeJdbc;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return employeeJdbc.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeJdbc.equals(employee.employeeJdbc);
    }

    @Override
    public int hashCode() {
        return employeeJdbc.hashCode();
    }
}
