package com.schedullingsystem.repository;

import com.schedullingsystem.entity.Classes;
import com.schedullingsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentByClassesListIn(List<Classes> id);

    List<Student> findStudentByFirstNameIgnoreCase(String firstName);

    List<Student> findStudentByLastNameIgnoreCase(String lastName);

}
