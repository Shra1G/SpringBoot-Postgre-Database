package spring.javaproject.student_backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long roll;
    private String name;
    private Long age;
    private String dept;
    private double cgpa;
    private String email;
}
