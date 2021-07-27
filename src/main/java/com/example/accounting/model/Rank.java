package com.example.accounting.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Rank {
    private Integer id;
    private String name;
    private BigDecimal salary;

    public Rank() {
    }

    public Rank(String name, BigDecimal salary) {
        this.name = name;
        this.salary = salary;
    }

    public Rank(Integer id, String name, BigDecimal salary) {
        this.id = id;
        this.name = name;
        // TODO: setSalary(salary);
        this.salary = salary;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rank rank = (Rank) o;
        return Objects.equals(id, rank.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    // TODO: throw IllegalArgument
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
