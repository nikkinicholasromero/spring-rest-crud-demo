package com.demo.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Employee {
    @Id
    private String id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private long numberOfDependents;

    @Column(precision = 1000, scale = 2)
    private BigDecimal height;

    @Column(precision = 1000, scale = 2)
    private BigDecimal weight;

    @Column
    private LocalDate hiredDate;

    @Column
    private LocalTime startTime;

    @Column
    private boolean isRegular;

    @Embedded
    private Audit audit;

    protected Employee() {
        // Note : Required by JPA. Do not use.
    }

    public Employee(String firstName, String lastName, LocalDate hiredDate, LocalTime startTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hiredDate = hiredDate;
        this.startTime = startTime;
    }

    public String id() {
        return id;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public long numberOfDependents() {
        return numberOfDependents;
    }

    public BigDecimal height() {
        return height;
    }

    public BigDecimal weight() {
        return weight;
    }

    public LocalDate hiredDate() {
        return hiredDate;
    }

    public LocalTime startTime() {
        return startTime;
    }

    public boolean isRegular() {
        return isRegular;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return new EqualsBuilder()
                .append(id, employee.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", numberOfDependents='" + numberOfDependents + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", hiredDate='" + hiredDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", isRegular='" + isRegular + '\'' +
                ", audit='" + audit + '\'' +
                '}';
    }
}
