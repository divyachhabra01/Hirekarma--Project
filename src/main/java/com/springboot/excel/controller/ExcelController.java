package com.springboot.excel.controller;

import com.springboot.excel.helper.Helper;
import com.springboot.excel.model.Email;
import com.springboot.excel.service.EmailService;
import com.springboot.excel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "v1/excel")
public class ExcelController {
    @Autowired
    private ExcelService excelService;
    @Autowired
    private EmailService emailService;

// paste the url in the browser to download the excel file
    @GetMapping(value = "/download" )
   public  ResponseEntity export(){
       String fileName= "document.xlsx";
       InputStreamResource file = new InputStreamResource(excelService.export());
       return ResponseEntity.ok()
               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
               .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
               .body(file);
   }



    @PostMapping(value = "/upload")
    public ResponseEntity<?> upload(@RequestParam("file")  MultipartFile file){
   if(Helper.checkExcelFormat(file)) {
       try {
           excelService.save(file);
           return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully:"+ file.getOriginalFilename());
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload file: "+ file.getOriginalFilename());
       }
   }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload  an excel file !");
   }
   @PostMapping(value = "/mail")
   public String sendMail() throws Exception {
   emailService.populateAndSendEmail();
   return "SUCCESS";
   }
   }



