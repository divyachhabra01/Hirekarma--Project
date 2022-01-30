package com.springboot.excel.service;

import com.springboot.excel.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface ExcelService {
    void save(MultipartFile file) throws IOException;

    ByteArrayInputStream export();
}


