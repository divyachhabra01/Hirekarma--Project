package com.springboot.excel.service.impl;

import com.springboot.excel.helper.Helper;
import com.springboot.excel.model.Student;
import com.springboot.excel.repository.StudentRepository;
import com.springboot.excel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private StudentRepository studentRepository;

    public void save(MultipartFile file) throws IOException {
        List<Student> students = Helper.convertExcelToListOFStudents(file.getInputStream());
        studentRepository.setStudents(students);
    }

    public ByteArrayInputStream export() {
        List<Student> students = studentRepository.getStudents();
        ByteArrayInputStream in = Helper.convertListOFStudentsToExcel(students);
        return in;
    }
}
