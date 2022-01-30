package com.springboot.excel.helper;

import com.springboot.excel.model.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String[] Headers = {"Name", "Phone", "Email"};
    public static String Sheet = "student";

    // checking format of  file
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if (TYPE.equals(contentType)) {
            return true;
        } else {
            return false;
        }
    }

    // converting excel data to list of students
    public static List<Student> convertExcelToListOFStudents(InputStream inputStream) {
        List<Student> students = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rowNumber = 0;

            for (Row row : sheet) {
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cellId = 0;

                Student student = new Student();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cellId) {
                        case 0:
                            student.setName(cell.getStringCellValue());
                            break;

                        case 1:
                            DataFormatter formatter = new DataFormatter();
                            String text = formatter.formatCellValue(cell);
                            student.setPhone(text);
                            break;
                        case 2:
                            student.setEmail(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellId++;

                }

                students.add(student);

            }


        } catch (Exception e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
        return students;
    }

    public static ByteArrayInputStream convertListOFStudentsToExcel(List<Student> students) {

        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(Sheet);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < Headers.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(Headers[col]);
            }

            int rowIdx = 1;
            for (Student student : students) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(student.getName() == null ? "" : student.getName());
                row.createCell(1).setCellValue(student.getPhone() == null ? "" : student.getPhone());
                row.createCell(2).setCellValue(student.getEmail() == null ? "" : student.getEmail());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());


        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }


    }
}