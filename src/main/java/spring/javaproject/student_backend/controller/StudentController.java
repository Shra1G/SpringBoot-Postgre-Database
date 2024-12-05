package spring.javaproject.student_backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.javaproject.student_backend.dto.StudentDto;
import spring.javaproject.student_backend.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    //Build Add Student REST API
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto savedStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    //Build GET Student REST API
    @GetMapping("{roll}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("roll") Long studentId){
        StudentDto studentDto = studentService.getStudentById(studentId);
        return ResponseEntity.ok(studentDto);
    }

    //Build Get All Students REST API
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    //Build Update Student REST API
    @PutMapping("{roll}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("roll") Long studentId,
                                                    @RequestBody StudentDto updatedStudent){
        StudentDto studentDto = studentService.updateStudent(studentId, updatedStudent);
        return ResponseEntity.ok(studentDto);
    }

    //Build Delete Student REST API
    @DeleteMapping("{roll}")
    public ResponseEntity<String> deleteStudent(@PathVariable("roll") Long studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Student deleted successfully!");
    }

    //GET /api/students/filter?department=MEC&page=0&size=6
    @GetMapping("/filter")
    public ResponseEntity<Page<StudentDto>> getStudentsByDepartment(
            @RequestParam String department,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<StudentDto> studentPage = studentService.getStudentsByDepartment(department, pageRequest);
        return ResponseEntity.ok(studentPage);
    }
}