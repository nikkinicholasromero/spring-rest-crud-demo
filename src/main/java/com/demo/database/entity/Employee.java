package com.demo.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Where(clause = "deleted_at is null")
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

    public Employee(
            String id,
            String firstName,
            String lastName,
            long numberOfDependents,
            BigDecimal height,
            BigDecimal weight,
            LocalDate hiredDate,
            LocalTime startTime,
            boolean isRegular,
            String createdBy) {
        this.id = id;
        this.firstName = StringUtils.trimToEmpty(firstName);
        this.lastName = StringUtils.trimToEmpty(lastName);
        this.numberOfDependents = numberOfDependents;
        this.height = height;
        this.weight = weight;
        this.hiredDate = hiredDate;
        this.startTime = startTime;
        this.isRegular = isRegular;
        this.audit = new Audit(LocalDateTime.now(), createdBy);
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

    public void updateName(String firstName, String lastName, String deletedBy) {
        this.firstName = StringUtils.trimToEmpty(firstName);
        this.lastName = StringUtils.trimToEmpty(lastName);
        this.audit.update(deletedBy);
    }

    public void updateNumberOfDependents(long numberOfDependents, String deletedBy) {
        this.numberOfDependents = numberOfDependents;
        this.audit.update(deletedBy);
    }

    public void updateBodyInformation(BigDecimal height, BigDecimal weight, String deletedBy) {
        this.height = height;
        this.weight = weight;
        this.audit.update(deletedBy);
    }

    public void updateEmploymentInformation(LocalDate hiredDate, LocalTime startTime, boolean isRegular, String deletedBy) {
        this.hiredDate = hiredDate;
        this.startTime = startTime;
        this.isRegular = isRegular;
        this.audit.update(deletedBy);
    }

    public void delete(String deletedBy) {
        this.audit.delete(deletedBy);
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
