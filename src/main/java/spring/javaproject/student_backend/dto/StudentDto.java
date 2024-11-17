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
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
