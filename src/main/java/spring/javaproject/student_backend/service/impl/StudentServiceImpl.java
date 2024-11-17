package spring.javaproject.student_backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.javaproject.student_backend.dto.StudentDto;
import spring.javaproject.student_backend.entity.Student;
import spring.javaproject.student_backend.exception.ResourceNotFoundException;
import spring.javaproject.student_backend.mapper.StudentMapper;
import spring.javaproject.student_backend.repository.StudentRepository;
import spring.javaproject.student_backend.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with given ID: " + studentId + "doesn't exist"));
        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map((student) ->
                StudentMapper.mapToStudentDto(student))
                .collect(Collectors.toList());

    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudent) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student with id " + studentId + "doesn't exist"));

        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());

        Student updatedStudentObj = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updatedStudentObj);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student with id " + studentId + "doesn't exist"));

        studentRepository.deleteById(studentId);
    }
}