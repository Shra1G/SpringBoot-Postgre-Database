package spring.javaproject.student_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.javaproject.student_backend.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
