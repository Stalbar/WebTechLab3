package com.poit.repository;

import com.poit.repository.entity.Student;
import java.util.List;

public interface StudentsRepository {

    List<Student> findAll();

    Student findByCardNum(String cardNum);

    void save(Student student);

    void update(Student student);
}
