package com.poit.dao.wrapper;

import com.poit.repository.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsWrapper {
    private List<Student> students;

    public StudentsWrapper() {
        this.students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public StudentsWrapper(List<Student> students) {
        this.students = students;
    }

    public void add(Student d) {
        students.add(d);
    }
}
