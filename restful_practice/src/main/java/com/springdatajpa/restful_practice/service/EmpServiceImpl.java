package com.springdatajpa.restful_practice.service;

import java.util.ArrayList;
import java.util.List;

import com.springdatajpa.restful_practice.exception.ResourceNotFoundException;
import com.springdatajpa.restful_practice.model.Emp;
import com.springdatajpa.restful_practice.repository.EmpRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpRepository empRepository;

    @Override
    public List<Emp> findAll() {
        List<Emp> emps = new ArrayList<>();
        empRepository.findAll().forEach(e -> emps.add(e));
        return emps;
    }
    
    @Override
    public Emp findById(int empno) {
        // TODO Auto-generated method stub
        Emp emp = empRepository.findById(empno).orElseThrow(() -> new ResourceNotFoundException("Emp","empno",empno));
        return emp;
    }
    @Override
    public void deleteById(int empno) {
        // TODO Auto-generated method stub
        empRepository.deleteById(empno);
    }
    
    @Override
    public Emp save(Emp emp) {
        // TODO Auto-generated method stub
        return empRepository.save(emp);
    }

    @Override
    public List<Emp> findBySalBetween(int sal1, int sal2) {
        // TODO Auto-generated method stub
        List<Emp> emps = empRepository.findBySalBetween(sal1, sal2);
        System.out.println(emps.size() + ">>>>>>>>>>>>>>>" + sal1 + ", " + sal2);
        if(emps.size() > 0)
            return emps;
        else
            return null;
    }

    @Override
    public void updateById(int empno, Emp emp) {
        // TODO Auto-generated method stub
        Emp e = empRepository.findById(empno).orElseThrow(() -> new ResourceNotFoundException("Emp", "empno", empno));
        e.setEname(emp.getEname());
        e.setSal(emp.getSal());

        empRepository.save(emp);
    }


}