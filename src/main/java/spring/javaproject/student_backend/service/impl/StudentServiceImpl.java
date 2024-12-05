package spring.javaproject.student_backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.javaproject.student_backend.dto.StudentDto;
import spring.javaproject.student_backend.entity.Student;
import spring.javaproject.student_backend.exception.ResourceNotFoundException;
import spring.javaproject.student_backend.mapper.StudentMapper;
import spring.javaproject.student_backend.repository.StudentRepository;
import spring.javaproject.student_backend.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
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
                        new ResourceNotFoundException("Student with given roll: " + studentId + "doesn't exist"));
        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(StudentMapper::mapToStudentDto)
                .collect(Collectors.toList());

    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudent) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student with roll " + studentId + "doesn't exist"));

        student.setName(updatedStudent.getName());
        student.setAge(updatedStudent.getAge());
        student.setDept(updatedStudent.getDept());
        student.setCgpa(updatedStudent.getCgpa());
        student.setEmail(updatedStudent.getEmail());

        Student updatedStudentObj = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updatedStudentObj);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student with roll " + studentId + "doesn't exist"));

        studentRepository.deleteById(studentId);
    }

    @Override
    public Page<StudentDto> getStudentsByDepartment(String department, Pageable pageable) {
        return studentRepository.findByDepartment(department, pageable)
                .map(student -> new StudentDto(
                        student.getRoll(),
                        student.getName(),
                        student.getAge(),
                        student.getDept(),
                        student.getCgpa(),
                        student.getEmail()
                ));
    }
}