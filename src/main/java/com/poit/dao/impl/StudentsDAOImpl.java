package com.poit.dao.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.poit.criteria.Criteria;
import com.poit.dao.StudentsDAO;
import com.poit.dao.wrapper.StudentsWrapper;
import com.poit.repository.entity.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAOImpl implements StudentsDAO {

    private static final String PATH
            = "D:\\User Files\\University\\WT\\WTLab3\\src\\resoursec\\Students.xml";

    @Override
    public List<Student> find(Criteria criteria) throws RuntimeException {
        try (var fileInputStream = new FileInputStream(PATH)) {

            var mapper = new XmlMapper();
            var xmlString = new String(fileInputStream.readAllBytes());
            var dossiers = mapper.readValue(xmlString, StudentsWrapper.class).getStudents();

            var criteriaMap = criteria.getCriteriaMap();
            var result = new ArrayList<Student>();

            if (criteriaMap.isEmpty()) {
                return dossiers;
            } else {
                criteriaMap.forEach((key, value) -> result.addAll(
                    dossiers.stream().filter(p -> {
                        try {
                            var field = p.getClass().getDeclaredField(key);
                            field.setAccessible(true);
                            return field.get(p).equals(value);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }).toList()
                ));
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) throws RuntimeException {
        try (var fileOutputStream = new FileOutputStream(PATH)) {
            var mapper = new XmlMapper();
            var url = new File(PATH).toURI().toURL();
            var dossierWrapper = mapper.readValue(url, StudentsWrapper.class);
            dossierWrapper.add(student);
            fileOutputStream.write(mapper.writeValueAsBytes(dossierWrapper));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Student student) throws RuntimeException {
        try (var fileInputStream = new FileInputStream(PATH);
            var fileOutputStream = new FileOutputStream(PATH)) {

            var mapper = new XmlMapper();
            var xmlString = new String(fileInputStream.readAllBytes());
            var dossiers = mapper.readValue(xmlString, StudentsWrapper.class).getStudents();

            dossiers.removeIf(d -> d.getCardNum().equals(student.getCardNum()));
            dossiers.add(student);

            fileOutputStream.write(mapper.writeValueAsBytes(new StudentsWrapper(dossiers)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
