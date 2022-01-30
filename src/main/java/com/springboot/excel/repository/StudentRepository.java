package com.springboot.excel.repository;

import com.springboot.excel.model.Student;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Data
public class StudentRepository {
    List<Student> students;
}
