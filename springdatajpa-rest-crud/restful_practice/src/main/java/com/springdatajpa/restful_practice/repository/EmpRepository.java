package com.springdatajpa.restful_practice.repository;

import java.util.List;

import com.springdatajpa.restful_practice.model.Emp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Emp, Integer> {
    // 쿼리 메소드, 메소드 이름으로 자동으로 SELECT 쿼리 생성
	// JPA에서 자동으로 생성하는 쿼리는 다음과 같다.
	// select
	// emp0_.empno as empno1_0_,
	// emp0_.ename as ename2_0_,
	// emp0_.sal as sal3_0_
	// from
	// emp emp0_
	// where
	// emp0_.sal between ? and ?
    List<Emp> findBySalBetween(int sal1, int sal2);
}
