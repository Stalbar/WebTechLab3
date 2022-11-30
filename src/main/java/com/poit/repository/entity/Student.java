package com.poit.repository.entity;

import java.util.Objects;

public class Student {

    private String cardNum;
    private String faculty;
    private String specialty;
    private String name;
    private String surname;

    public Student(String cardNum, String faculty, String specialty, String name, String surname) {
        this.cardNum = cardNum;
        this.faculty = faculty;
        this.specialty = specialty;
        this.name = name;
        this.surname = surname;
    }

    public Student() {
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student student)) {
            return false;
        }

        if (!Objects.equals(cardNum, student.cardNum)) {
            return false;
        }
        if (!Objects.equals(faculty, student.faculty)) {
            return false;
        }
        if (!Objects.equals(specialty, student.specialty)) {
            return false;
        }
        if (!Objects.equals(name, student.name)) {
            return false;
        }
        return Objects.equals(surname, student.surname);
    }

    @Override
    public int hashCode() {
        int result = cardNum != null ? cardNum.hashCode() : 0;
        result = 31 * result + (faculty != null ? faculty.hashCode() : 0);
        result = 31 * result + (specialty != null ? specialty.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dossier{" +
            "cardNum='" + cardNum + '\'' +
            ", faculty='" + faculty + '\'' +
            ", specialty='" + specialty + '\'' +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            '}';
    }
}
