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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
    }

    @PutMapping(path = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> studentToEdit = studentService.findById(id);
        if(studentToEdit.isPresent())
        {
            studentToEdit.get().setFirstName(student.getFirstName());
            studentToEdit.get().setLastName(student.getLastName());
            return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(studentToEdit.get()));
        } else {
            throw new StudentNotFoundException();
        }

    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Boolean> deleteStudent(@PathVariable Long id) {
        Optional<Student> studentToEdit = studentService.findById(id);
        Map<String, Boolean> response = new HashMap<>();
        if(studentToEdit.isPresent())
        {
            studentService.delete(studentToEdit.get());
            response.put("deleted", Boolean.TRUE);
            return response;
        } else {
            throw new StudentNotFoundException();
        }

    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> findByStudentId(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        return student.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> findAll() {
        return  ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
    }

    @GetMapping(path = "/getStudentByClass/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> findStudentByClass(@PathVariable Long id) {
        Optional<Classes> student = classService.findById(id);
        if(student.isPresent())
        {
            return ResponseEntity.status(HttpStatus.OK).body(studentService.findStudentByClassId(student.get()));
        } else {
            throw new ClassesNotFoundException();
        }
    }

    @GetMapping(path = "/getStudentByFirstName/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> findStudentByFirstName(@PathVariable String firstName) {
        List<Student> listStudent = studentService.findStudenByFirstName(firstName);
        if(listStudent.size() > 0)
        {
            return ResponseEntity.status(HttpStatus.OK).body(listStudent);
        } else {
            throw new StudentNotFoundException();
        }
    }

    @GetMapping(path = "/getStudentByLastName/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> findStudentByLasttName(@PathVariable String lastName) {
        List<Student> listStudent = studentService.findStudentByLastName(lastName);
        if(listStudent.size() > 0)
        {
            return ResponseEntity.status(HttpStatus.OK).body(listStudent);
        } else {
            throw new StudentNotFoundException();
        }
    }

}
