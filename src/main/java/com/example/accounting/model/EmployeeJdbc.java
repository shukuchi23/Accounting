package com.example.accounting.model;

import java.util.Objects;

public class EmployeeJdbc {
    private Integer id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private Integer departmentId;
    private Integer rankId;

    public EmployeeJdbc(Integer id, String lastName, String firstName, String patronymic, Integer departmentId, Integer rankId) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.departmentId = departmentId;
        this.rankId = rankId;
    }

    public EmployeeJdbc() {
    }

    public Integer getId() {
        return id;
    }



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public String toString() {
        return String.format("%s %.1s. %.1s.", lastName, firstName, patronymic);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeJdbc that = (EmployeeJdbc) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
