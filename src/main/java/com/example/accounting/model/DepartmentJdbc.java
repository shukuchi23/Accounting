package com.example.accounting.model;

import java.util.Objects;

public class DepartmentJdbc {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private Integer bossId;  // id  начальника отдела

    public DepartmentJdbc(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public DepartmentJdbc(Integer id, String name, String phone, String email, Integer bossId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.bossId = bossId;
    }

    public DepartmentJdbc() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBossId() {
        return bossId;
    }

    public void setBossId(Integer bossId) {
        this.bossId = bossId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentJdbc that = (DepartmentJdbc) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name;
    }
}
