package com.schedullingsystem.controller;

import com.schedullingsystem.Exceptions.ClassesNotFoundException;
import com.schedullingsystem.Exceptions.StudentNotFoundException;
import com.schedullingsystem.entity.Classes;
import com.schedullingsystem.entity.Student;
import com.schedullingsystem.service.ClassService;
import com.schedullingsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private StudentService studentService;

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveStudent(@RequestBody Classes classes) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classService.save(classes));
    }

    @PutMapping(path = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editStudent(@PathVariable Long id, @RequestBody Classes classes) {
        Optional<Classes> classesToEdit = classService.findById(id);
        if(classesToEdit.isPresent())
        {
            classesToEdit.get().setTitle(classes.getTitle());
            classesToEdit.get().setDescription(classes.getDescription());
            return ResponseEntity.status(HttpStatus.CREATED).body(classService.save(classesToEdit.get()));
        } else {
            throw new ClassesNotFoundException();
        }

    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Boolean> deleteStudent(@PathVariable Long id) {
        Optional<Classes> classesToEdit = classService.findById(id);
        Map<String, Boolean> response = new HashMap<>();
        if(classesToEdit.isPresent())
        {
            classService.delete(classesToEdit.get());
            response.put("deleted", Boolean.TRUE);
            return response;
        } else {
            throw new ClassesNotFoundException();
        }

    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Classes> findByStudentId(@PathVariable Long id) {
        Optional<Classes> classes = classService.findById(id);
        return classes.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Classes>> findAll() {
        return  ResponseEntity.status(HttpStatus.OK).body(classService.findAll());
    }

    @GetMapping(path = "/getClassesByStudent/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Classes>> findClassesByStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if(student.isPresent())
        {
            return ResponseEntity.status(HttpStatus.OK).body(student.get().getClassesList());
        } else {
            throw new StudentNotFoundException();
        }
    }

}
