package spring.javaproject.student_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roll;

    @Column(name = "Name")
    private String name;

    @Column(name = "Age")
    private Long age;

    @Column(name = "Department")
    private String dept;

    @Column(name = "CGPA", nullable = false)
    private double cgpa;

    @Column(name = "email_id", nullable = true, unique = true)
    private String email;
}
