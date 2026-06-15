package com.example.jpa.ex06_bidirection_exercise;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "employee")
@Getter
@ToString
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Integer deptId;
  private Integer salary;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dept_id")
  private Department department;

  public Employee(String name, Integer deptId, Integer salary) {
    this.name = name;
    this.deptId = deptId;
    this.salary = salary;
  }

  public void setDepartment(Department department) {
    this.department = department;
    if (!department.getEmployees().contains(this)) {
      department.getEmployees().add(this);
    }
  }
}
