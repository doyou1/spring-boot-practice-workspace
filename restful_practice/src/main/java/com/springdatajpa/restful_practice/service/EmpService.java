package com.springdatajpa.restful_practice.service;

import java.util.List;

import com.springdatajpa.restful_practice.model.Emp;

public interface EmpService {
    List<Emp> findAll();
    Emp findById(int empno);
    void deleteById(int empno);
    Emp save(Emp emp);
    List<Emp> findBySalBetween(int sal1, int sal2);
    void updateById(int empno, Emp emp);
}
