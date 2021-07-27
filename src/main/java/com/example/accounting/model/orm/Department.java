package com.example.accounting.model.orm;

import com.example.accounting.model.DepartmentJdbc;

import java.util.*;

public class Department {
    private DepartmentJdbc departmentJdbc = new DepartmentJdbc();
    private Set<Employee> employees = new HashSet<>();
    private Employee boss;  // id  начальника отдела

    public Department() {
    }

    public Department(DepartmentJdbc departmentJdbc) {
        this.departmentJdbc = departmentJdbc;
    }

    public Department(DepartmentJdbc departmentJdbc, Set<Employee> employees, Employee boss) {
        this.departmentJdbc = departmentJdbc;
        this.employees = employees;
        this.boss = boss;
    }

    public Integer getId() {
        return departmentJdbc.getId();
    }

    public void addEmployee(Employee e) {
        e.setDepartment(this);
        employees.add(e);
    }

    public void addEmployees(List<Employee> employees) {
        for (Employee e : employees) {
            addEmployee(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return departmentJdbc.equals(that.departmentJdbc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentJdbc);
    }


    public DepartmentJdbc getDepartmentJdbc() {
        return departmentJdbc;
    }


    public Set<Employee> getEmployees() {
        return employees;
    }


    public Optional<Employee> getBoss() {
        return Optional.ofNullable(boss);
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
        departmentJdbc.setBossId(boss.getId());
    }

    @Override
    public String toString() {
        return departmentJdbc.toString();
    }

    public void setDepartmentJdbc(DepartmentJdbc departmentJdbc) {
        this.departmentJdbc = departmentJdbc;
    }
}
