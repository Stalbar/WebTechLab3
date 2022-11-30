package com.poit.repository.impl;

import com.poit.criteria.Criteria;
import com.poit.dao.StudentsDAO;
import com.poit.repository.entity.Student;

import java.util.List;

public class StudentsRepositoryImpl implements com.poit.repository.StudentsRepository {
    private final StudentsDAO studentsDAO;

    public StudentsRepositoryImpl(StudentsDAO studentsDAO) {
        this.studentsDAO = studentsDAO;
    }

    @Override
    public List<Student> findAll() {
        try {
            return studentsDAO.find(new Criteria());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student findByCardNum(String cardNum) {
        var criteria = new Criteria();
        criteria.add("cardNum", cardNum);
        try {
            return studentsDAO.find(criteria).get(0);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        try {
            studentsDAO.save(student);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student student) {
        try {
            studentsDAO.update(student);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
