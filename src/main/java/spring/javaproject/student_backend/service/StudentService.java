package spring.javaproject.student_backend.service;

import spring.javaproject.student_backend.dto.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);

    StudentDto getStudentById(Long studentId);

    List<StudentDto> getAllStudents();

    StudentDto updateStudent(Long studentId, StudentDto updatedStudent);

    void deleteStudent(Long studentId);

    Page<StudentDto> getStudentsByDepartment(String department, Pageable pageable);

}