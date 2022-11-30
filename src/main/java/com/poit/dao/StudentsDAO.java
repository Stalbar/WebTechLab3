package com.poit.dao;

import com.poit.criteria.Criteria;
import com.poit.repository.entity.Student;

import java.util.List;

public interface StudentsDAO {
    List<Student> find(Criteria criteria) throws RuntimeException;

    void save(Student student) throws RuntimeException;

    void update(Student student) throws RuntimeException;
}
