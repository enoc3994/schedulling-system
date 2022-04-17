package com.schedullingsystem.service;

import com.schedullingsystem.entity.Classes;
import com.schedullingsystem.entity.Student;
import com.schedullingsystem.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public Classes save(Classes classes) {
        return classRepository.save(classes);
    }

    public Optional<Classes> findById(Long id) {
        return classRepository.findById(id);
    }

    public List<Classes> findAll() {
        return classRepository.findAll();
    }

    public void delete(Classes classes) {
        classRepository.delete(classes);
    }

}
