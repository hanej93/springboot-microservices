package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
                1,
                "EJ",
                "Han"
        );
//        return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok()
                .header("customer-header", "ej")
                .body(student);
    }

    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "EJ", "Han"));
        students.add(new Student(2, "EJ2", "Han2"));
        students.add(new Student(3, "EJ3", "Han3"));
        students.add(new Student(4, "EJ4", "Han4"));

        return ResponseEntity.ok(students);
    }

    // Spring BOOT REST API with PathVariable
    // {id} - uri template variable
    // http://localhost:8080/students/1/EJ/Han
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API with RequestParam
    // http://locahost:8080/students?id=1&firstName=EJ&lastName=Han
    @GetMapping("query")
    public ResponseEntity<Student> studentRequest(@RequestParam(value = "id") int studentId,
                                  @RequestParam(value = "firstName", required = false) String firstName,
                                  @RequestParam(value = "lastName", required = false) String lastName) {
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API that handles HTTP POST Request - creating new resource
    // @PostMapping and @RequestBody
    @PostMapping("create")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println("student.getId() = " + student.getId());
        System.out.println("student.getFirstName() = " + student.getFirstName());
        System.out.println("student.getLastName() = " + student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring boot REST API that handles HTTP PUT Request - updating existing resource
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println("student.getFirstName() = " + student.getFirstName());
        System.out.println("student.getLastName() = " + student.getLastName());
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API that handles HTTP DELETE Request - deleting the existing resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println("studentId = " + studentId);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
