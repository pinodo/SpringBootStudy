package com.example.jpa;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.jpa.ex06_bidirection_exercise.Department;
import com.example.jpa.ex06_bidirection_exercise.Employee;
import com.example.jpa.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Ex06BiDirectionExerciseTests {

  // 엔티티 매니저 (영속성 컨텍스트 관리자)
  private EntityManager em;

  // 엔티티 트랜잭션
  private EntityTransaction tx;

  // 테스트 시작 전 엔티티 매니저를 만들기 위해 팩토리(공장)부터 지어둠
  @BeforeAll
  static void setUpBeforeClass() {
    JpaUtil.initFactory();
  }

  // 각 테스트 시작 전 엔티티 매니저를 생성
  @BeforeEach
  void setUp() {
    em = JpaUtil.getEntityManager();
    tx = em.getTransaction(); // JPA의 모든 데이터 변경은 트랜잭션 내부에서 실행되어야 함
    tx.begin();
  }

  // 각 테스트 종료 후 엔티티 메니저를 닫아줌
  @AfterEach
  void tearDown() {
    if (tx != null && tx.isActive()) {
      tx.rollback();
    }
    if (em != null && em.isOpen()) {
      em.close();
    }
  }

  // 전체 테스트 종료 후 엔티티 매니저 팩토리를 닫아줌
  @AfterAll
  static void tearDownAfterClass() {
    JpaUtil.closeFactory();
  }

  // 이제부터 테스트 진행
  @Test
  @DisplayName("양방향 저장 및 조회 테스트")
  void biDirectionExerciseTests() {

    Department dept1 = new Department("개발부");
    Department dept2 = new Department("영업부");

    Employee emp1 = new Employee("kim", 1, 5000);
    Employee emp2 = new Employee("lee", 1, 6000);
    Employee emp3 = new Employee("jung", 2, 5000);
    Employee emp4 = new Employee("choi", 2, 6000);

    dept1.addEmployee(emp1);
    dept1.addEmployee(emp2);
    dept2.addEmployee(emp3);
    dept2.addEmployee(emp4);

    em.persist(dept1);
    em.persist(dept2);

    em.flush();
    em.clear();

    Department findEmployee = em.find(Department.class, dept1.getId());
    System.out.println(findEmployee.getEmployees().get(1).getSalary());

    findEmployee.getEmployees().remove(1);
    System.out.println(findEmployee.getEmployees().get(0).getSalary());

    em.flush();
  }
}
