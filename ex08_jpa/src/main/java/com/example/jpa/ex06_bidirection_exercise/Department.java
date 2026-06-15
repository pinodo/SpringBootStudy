package com.example.jpa.ex06_bidirection_exercise;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "department")
@Getter
@ToString
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String deptName;

  public Department(String deptName) {
    this.deptName = deptName;
  }

  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Employee> employees = new ArrayList<>();

  public void addEmployee(Employee employee) {
    this.employees.add(employee);
    if (employee.getDepartment() == null) 
    employee.setDepartment(this);
  }
}
