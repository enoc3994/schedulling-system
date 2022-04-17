package com.schedullingsystem.service;

import com.schedullingsystem.entity.Classes;
import com.schedullingsystem.entity.Student;
import com.schedullingsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;



    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public List<Student> findStudentByClassId(Classes classes)
    {
        return studentRepository.findStudentByClassesListIn(Collections.singletonList(classes));
    }

    public List<Student> findStudenByFirstName(String firstname)
    {
        return studentRepository.findStudentByFirstNameIgnoreCase(firstname);
    }

    public List<Student> findStudentByLastName(String lastName)
    {
        return studentRepository.findStudentByLastNameIgnoreCase(lastName);
    }
}
