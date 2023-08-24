package com.clean.code.springboot.web.rest;

import com.clean.code.springboot.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentResource {
    @GetMapping("/students/all")
   public ResponseEntity getAllStudents(){
        Student student = new Student(1L, "Javlon", "Abdurasulov", "3-course");
        Student student1 = new Student(1L, "Aziz", "Usmonov", "2-course");
        Student student2 = new Student(1L, "Usmon", "Azimov", "1-course");
        Student student3 = new Student(1L, "Oybek", "Siddiqov", "4-course");

        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity getOneStudent(@PathVariable Long id){
        Student student = new Student(1L, "Javlon", "Abdurasulov", "3-course");
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students")
    public ResponseEntity getOne(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String lastName,
            @RequestParam String course

    ){
        Student student = new Student(id, name, lastName, course);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public ResponseEntity createStudent(@RequestBody Student student){
        return ResponseEntity.ok(student);
    }
    @PutMapping("/students/{id}")
    public ResponseEntity updateStudent(@PathVariable Long id, @RequestBody Student studentNew){
        Student student = new Student(1L, "Javlon", "Abdurasulov", "3-course");
        student.setId(id);
        student.setName(studentNew.getName());
        student.setLastName(studentNew.getLastName());
        student.setCourse(studentNew.getCourse());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.ok("Ma'lumotlar o'chirildi");
    }
}
