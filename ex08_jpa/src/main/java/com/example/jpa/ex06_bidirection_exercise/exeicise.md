# 양방향 연관 관계 엔티티 구성해보기

## 테이블 구조

### departments (Department Entity)

| id | deptName |
|----|----------|
| 1  | 개발부    |
| 2  | 영업부    |

### employees (Employee Entity)

| id | name | dept_id | salary |
|----|------|---------|--------|
| 1  | kim  | 1       | 5000   |
| 2  | lee  | 1       | 6000   |
| 3  | jung | 2       | 5000   |
| 4  | choi | 2       | 6000   |

## 연관관계

- Department : Employee = **1 : N**
- `employees.dept_id` → `departments.id` (FK)