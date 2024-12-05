package spring.javaproject.student_backend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.javaproject.student_backend.dto.StudentDto;
import spring.javaproject.student_backend.entity.Student;

@Component
public class StudentMapper {
    @Autowired
    public static StudentDto mapToStudentDto(Student student){
        return new StudentDto(
                student.getRoll(),
                student.getName(),
                student.getAge(),
                student.getDept(),
                student.getCgpa(),
                student.getEmail()
        );
    }
    @Autowired
    public static Student mapToStudent(StudentDto studentDto){
        return new Student(
                studentDto.getRoll(),
                studentDto.getName(),
                studentDto.getAge(),
                studentDto.getDept(),
                studentDto.getCgpa(),
                studentDto.getEmail()
        );
    }
}